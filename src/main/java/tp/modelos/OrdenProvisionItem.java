package tp.modelos;

public class OrdenProvisionItem {
	private Integer id;
	private OrdenProvision orden;
	private Integer cantidad;
	private Producto producto;
	
	public OrdenProvisionItem() {
		
	}

	public OrdenProvisionItem(Integer id, OrdenProvision orden, Integer cantidad, Producto producto) {
		super();
		this.id = id;
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

	public OrdenProvision getOrden() {
		return orden;
	}

	public void setOrden(OrdenProvision orden) {
		this.orden = orden;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
}
