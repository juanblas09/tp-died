package tp.modelos;

public class Stock {
	private Integer id;
	private Sucursal sucursal;
	private Producto producto;
	private Integer stock;
	
	public Stock() {
		
	}

	public Stock(Sucursal sucursal, Producto producto, Integer stock) {
		super();
		this.sucursal = sucursal;
		this.producto = producto;
		this.stock = stock;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
}
