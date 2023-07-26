package tp.database.interfaces;

import java.util.List;

import tp.modelos.Camino;

public interface CaminoInterface {
    public Camino buscarPorID(Integer id);
	public void borrar(Integer id);
	public List<Camino> buscarTodos();
	public Camino guardar(Camino camino);
	public Camino actualizar(Camino camino);
}
