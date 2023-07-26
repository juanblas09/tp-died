package tp.modelos;

import java.sql.Date;

public class OrdenProvision {
	private Integer id;
	private Date fecha;
	private Integer sucursalDestino;
	private Integer plazoMaximo;
	private EnumEstado estado;
	
	public OrdenProvision() {	
	}

	public OrdenProvision(Date fecha, Integer sucursalDestino, Integer plazoMaximo, EnumEstado estado) {
		super();
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getSucursalDestino() {
		return sucursalDestino;
	}

	public void setSucursalDestino(Integer sucursalDestino) {
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
