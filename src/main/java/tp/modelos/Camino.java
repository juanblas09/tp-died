package tp.modelos;

public class Camino {
	private Integer id;
	private Sucursal sucursalOrigen;
	private Sucursal sucursalDestino;
	private Integer tiempoTransito;
	private Integer capacidadMaxima;
	private EnumOperativa operativa;
	private Integer queOnda;
	
	public Camino() {
		
	}

	public Camino(Integer id, Sucursal sucursalOrigen, Sucursal sucursalDestino, Integer tiempoTransito,
			Integer capacidadMaxima, EnumOperativa operativa) {
		super();
		this.id = id;
		this.sucursalOrigen = sucursalOrigen;
		this.sucursalDestino = sucursalDestino;
		this.tiempoTransito = tiempoTransito;
		this.capacidadMaxima = capacidadMaxima;
		this.operativa = operativa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Sucursal getSucursalOrigen() {
		return sucursalOrigen;
	}

	public void setSucursalOrigen(Sucursal sucursalOrigen) {
		this.sucursalOrigen = sucursalOrigen;
	}

	public Sucursal getSucursalDestino() {
		return sucursalDestino;
	}

	public void setSucursalDestino(Sucursal sucursalDestino) {
		this.sucursalDestino = sucursalDestino;
	}

	public Integer getTiempoTransito() {
		return tiempoTransito;
	}

	public void setTiempoTransito(Integer tiempoTransito) {
		this.tiempoTransito = tiempoTransito;
	}

	public Integer getCapacidadMaxima() {
		return capacidadMaxima;
	}

	public void setCapacidadMaxima(Integer capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}

	public EnumOperativa getOperativa() {
		return operativa;
	}

	public void setOperativa(EnumOperativa operativa) {
		this.operativa = operativa;
	}

	
}
