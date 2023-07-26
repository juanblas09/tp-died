package tp.modelos;

public class OrdenProvisionItem {
	private Integer id;
	private Integer orden;
	private Integer cantidad;
	private Integer producto;
	
	public OrdenProvisionItem() {
		
	}

	public OrdenProvisionItem(Integer orden, Integer cantidad, Integer producto) {
		super();
		this.orden = orden;
		this.cantidad = cantidad;
		this.producto = producto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getProducto() {
		return producto;
	}

	public void setProducto(Integer producto) {
		this.producto = producto;
	}
	
	
}
