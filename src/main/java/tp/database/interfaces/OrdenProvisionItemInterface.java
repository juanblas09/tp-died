package tp.database.interfaces;

import java.util.List;

import tp.modelos.OrdenProvisionItem;

public interface OrdenProvisionItemInterface {
    public OrdenProvisionItem buscarPorID(Integer id);
	public void borrar(Integer id);
	public List<OrdenProvisionItem> buscarTodos();
	public List<OrdenProvisionItem> buscarTodosPorOrden(Integer id);
	public OrdenProvisionItem guardar(OrdenProvisionItem ordenProvisionItem);
	public OrdenProvisionItem actualizar(OrdenProvisionItem ordenProvisionItem);
}
