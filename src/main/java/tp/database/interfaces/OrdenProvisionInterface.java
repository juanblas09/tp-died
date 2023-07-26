package tp.database.interfaces;

import java.util.List;

import tp.modelos.OrdenProvision;

public interface OrdenProvisionInterface {
    public OrdenProvision buscarPorID(Integer id);
	public void borrar(Integer id);
	public List<OrdenProvision> buscarTodos();
	public OrdenProvision guardar(OrdenProvision ordenProvision);
	public OrdenProvision actualizar(OrdenProvision ordenProvision);
}
