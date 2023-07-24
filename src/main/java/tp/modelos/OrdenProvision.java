package tp.modelos;

import java.time.Instant;

public class OrdenProvision {
	private Integer id;
	private Instant fecha;
	private Sucursal sucursalDestino;
	private Integer plazoMaximo;
	private EnumEstado estado;
	
	public OrdenProvision() {	
	}

	public OrdenProvision(Integer id, Instant fecha, Sucursal sucursalDestino, Integer plazoMaximo, EnumEstado estado) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.sucursalDestino = sucursalDestino;
		this.plazoMaximo = plazoMaximo;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Instant getFecha() {
		return fecha;
	}

	public void setFecha(Instant fecha) {
		this.fecha = fecha;
	}

	public Sucursal getSucursalDestino() {
		return sucursalDestino;
	}

	public void setSucursalDestino(Sucursal sucursalDestino) {
		this.sucursalDestino = sucursalDestino;
	}

	public Integer getPlazoMaximo() {
		return plazoMaximo;
	}

	public void setPlazoMaximo(Integer plazoMaximo) {
		this.plazoMaximo = plazoMaximo;
	}

	public EnumEstado getEstado() {
		return estado;
	}

	public void setEstado(EnumEstado estado) {
		this.estado = estado;
	}
	
	
	
}
