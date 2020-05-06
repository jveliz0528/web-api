package ar.com.educacionit.web.managedbeans.producto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import ar.com.eduacionit.app.domain.Producto;
import ar.com.eduacionit.app.domain.TipoProducto;
import ar.com.educacionit.services.ProductoService;
import ar.com.educacionit.services.exceptions.ServiceException;
import ar.com.educacionit.services.impl.ProductoServiceImpl;

@Named
@ViewScoped
public class ProductoBean implements Serializable{

	private static final long serialVersionUID = -8975956926465148459L;

	private ProductoService productoService = new ProductoServiceImpl();
	
	private Producto producto = new Producto();
	
	private Long tipoProducto;
	
	private String mensajeError;

	private List<Producto> productos;
	
	@PostConstruct
	public void loadProductos() {
		this.productos = findProductos();
	}
	
	public List<Producto> findProductos() {
		List<Producto> productos;
		try {
			productos = productoService.findProductos();
		} catch (ServiceException e) {
			e.printStackTrace();
			mensajeError = e.getMessage();
			productos = new ArrayList<Producto>();
		}
		return productos;
	}

	public String nuevoProducto() {
		return "nuevo-producto";
	}
	
	public String crearNuevoProducto() {
		try {
			TipoProducto tp = new TipoProducto();
			tp.setId(this.tipoProducto);
			this.producto.setTipoProducto(tp);
			this.productoService.createProducto(this.producto);
		} catch (ServiceException e) {
			this.mensajeError = e.getMessage();
			return "nuevo-producto";
		}
		return "listado-productos";
	}
	
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	//METODOS AGREGADOS PARA PRIMERFACES
	public void onRowSelect(SelectEvent<Producto> event) {
		this.producto = event.getObject();
    }
	
	public void onRowEdit(RowEditEvent<Producto> event) {
		FacesMessage msg;
		try {
			if(!event.getObject().getTipoProducto().getId().equals(this.tipoProducto)) {
				event.getObject().getTipoProducto().setId(this.tipoProducto);
			}
			this.productoService.updateProducto(event.getObject());
			msg = new FacesMessage("Producto editado ", event.getObject().getId().toString());
			this.productos = findProductos();
		} catch (ServiceException e) {
			msg = new FacesMessage(e.getMessage(), event.getObject().getId().toString());
		}
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent<Producto> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
	public void eliminarProducto() {
		
		FacesMessage msg;
		try {
			this.productoService.eliminarProducto(producto.getCodigo());
			msg = new FacesMessage("Producto eliminado ", producto.getId().toString());
			this.productos.remove(producto);
			this.producto = null;
		} catch (Exception e) {
			msg = new FacesMessage("Error eliminando producto", e.getMessage());
		}
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Long getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(Long tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	
}
