package ar.com.educacionit.web.managedbeans.producto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ar.com.educacionit.app.domain.TipoProducto;
import ar.com.educacionit.services.ProductoService;
import ar.com.educacionit.services.exceptions.ServiceException;
import ar.com.educacionit.services.impl.ProductoServiceImpl;

@Named
@ViewScoped
public class TipoProductoBean implements Serializable{

	private static final long serialVersionUID = -8975956926465148459L;

	private ProductoService productoService = new ProductoServiceImpl();
	
	private List<TipoProducto> tipoProductos;
	
	@PostConstruct
	public void loadTipoProductos() {
		try {
			this.tipoProductos = productoService.findTipoProductos();
		} catch (ServiceException e) {
			e.printStackTrace();
			tipoProductos = new ArrayList<>();
		}
	}
	
	public TipoProducto[] getTipoProductos() {
		return this.tipoProductos.stream()
				.collect(Collectors.toSet()).toArray(new TipoProducto[] {});
	}

}
