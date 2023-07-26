package tp.database.interfaces;

import java.util.List;

import tp.modelos.Producto;

public interface ProductoInterface {
	public Producto buscarPorID(Integer id);
	public void borrar(Integer id);
	public List<Producto> buscarTodos();
	public Producto guardar(Producto producto);
	public Producto actualizar(Producto producto);
}

