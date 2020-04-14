package ar.com.educacionit.app.ws.soap.dto;

public class CreateProductoDTO {

	private String descripcion;
	
	private Double precio;
	
	private String codigo;

	public CreateProductoDTO() {
		super();
	}

	public CreateProductoDTO(String descripcion, Double precio, String codigo) {
		super();
		this.descripcion = descripcion;
		this.precio = precio;
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "CreateProductoDTO [descripcion=" + descripcion + ", precio=" + precio + ", codigo=" + codigo + "]";
	}
}
