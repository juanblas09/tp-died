package tp.modelos;

public class Stock {
	private Integer id;
	private Integer sucursal;
	private Integer producto;
	private Integer stock;
	
	public Stock() {
		
	}

	public Stock(Integer sucursal, Integer producto, Integer stock) {
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

	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

	public Integer getProducto() {
		return producto;
	}

	public void setProducto(Integer producto) {
		this.producto = producto;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
}
