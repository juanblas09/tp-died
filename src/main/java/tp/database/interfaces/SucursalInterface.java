package tp.database.interfaces;

import java.util.List;

import tp.modelos.Sucursal;

public interface SucursalInterface {
	public Sucursal buscarPorID(Integer id);
	public void borrar(Integer id);
	public List<Sucursal> buscarTodos();
	public Sucursal guardar(Sucursal sucursal);
	public Sucursal actualizar(Sucursal sucursal);
}
