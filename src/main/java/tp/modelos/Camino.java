package tp.modelos;

import tp.database.SucursalDao;
import tp.database.interfaces.SucursalInterface;


public class Camino {
	private Integer id;
	private Integer sucursalOrigen;
	private Integer sucursalDestino;
	private Integer tiempoTransito;
	private Integer capacidadMaxima;
	private EnumOperativa operativa;
	
	public Camino() {
		
	}
	
	public Camino(Integer sucursalOrigen, Integer sucursalDestino, Integer tiempoTransito,
			Integer capacidadMaxima, EnumOperativa operativa) {
		super();
		this.sucursalOrigen = sucursalOrigen;
		this.sucursalDestino = sucursalDestino;
		this.tiempoTransito = tiempoTransito;
		this.capacidadMaxima = capacidadMaxima;
		this.operativa = operativa;
	}

	public Camino(Integer id, Integer sucursalOrigen, Integer sucursalDestino, Integer tiempoTransito,
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

	public Integer getSucursalOrigen() {
		return sucursalOrigen;
	}

	public void setSucursalOrigen(Integer sucursalOrigen) {
		this.sucursalOrigen = sucursalOrigen;
	}

	public Integer getSucursalDestino() {
		return sucursalDestino;
	}

	public void setSucursalDestino(Integer sucursalDestino) {
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
	
	@Override
	public String toString() {
		SucursalInterface si = new SucursalDao();		
		return si.buscarPorID(sucursalOrigen).getNombre() + " -> " + si.buscarPorID(sucursalDestino).getNombre();
	}

	
}
