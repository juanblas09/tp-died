package tp.modelos;

public class Sucursal {
	private Integer id;
	private String nombre;
	private Integer horarioApertura;
	private Integer horarioCierre;
	private EnumOperativa operativa;
	
	public Sucursal() {
		super();
	}
	
	public Sucursal(String nombre, Integer horarioApertura, Integer horarioCierre,
			EnumOperativa operativa) {
		super();
		this.nombre = nombre;
		this.horarioApertura = horarioApertura;
		this.horarioCierre = horarioCierre;
		this.operativa = operativa;
	}

	public Sucursal(Integer id, String nombre, Integer horarioApertura, Integer horarioCierre,
			EnumOperativa operativa) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.horarioApertura = horarioApertura;
		this.horarioCierre = horarioCierre;
		this.operativa = operativa;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getHorarioApertura() {
		return horarioApertura;
	}
	
	public void setHorarioApertura(Integer horarioApertura) {
		this.horarioApertura = horarioApertura;
	}
	
	public Integer getHorarioCierre() {
		return horarioCierre;
	}
	
	public void setHorarioCierre(Integer horarioCierre) {
		this.horarioCierre = horarioCierre;
	}
	
	public EnumOperativa getOperativa() {
		return operativa;
	}
	
	public void setOperativa(EnumOperativa operativa) {
		this.operativa = operativa;
	}

	@Override
    public String toString() {
        return nombre;
    }
	
	public void alta() {
		
	}
	
	public void baja() {
		
	}
	
	public void modificacion() {
		
	}
	
	public void buscar() {
		
	}
}