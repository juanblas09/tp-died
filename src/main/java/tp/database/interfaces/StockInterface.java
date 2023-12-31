package tp.database.interfaces;

import java.util.List;

import tp.modelos.Stock;

public interface StockInterface {
    public Stock buscarPorID(Integer id);
	public void borrar(Integer id);
	public List<Stock> buscarTodos();
	public Stock guardar(Stock stock);
	public Stock actualizar(Stock stock);
	public Integer getStock(Integer idSuc, Integer idPro);
	public void crearOActualizarStock(Integer idSucursal, Integer idProducto, Integer nuevoStock);
}
