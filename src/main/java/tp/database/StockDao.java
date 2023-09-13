package tp.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tp.database.interfaces.StockInterface;
import tp.modelos.Stock;

public class StockDao implements StockInterface{
    public Stock buscarPorID(Integer id) {
		Stock resultado = new Stock();
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement("SELECT * FROM stock WHERE id_stock = ?");
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			if(rs.next()){
				resultado.setId(rs.getInt("id_stock"));
				resultado.setStock(rs.getInt("stock"));
				resultado.setProducto(rs.getInt("id_producto"));
                resultado.setSucursal(rs.getInt("id_sucursal"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(conn!=null) conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return resultado;
	}

	public void borrar(Integer id) {
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement("DELETE FROM stock WHERE id_stock = ?");
			pstm.setInt(1,id);

			pstm.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(pstm!=null) pstm.close();
				if(conn!=null) conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public List<Stock> buscarTodos() {
		List<Stock> resultado = new ArrayList<Stock>();
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement("SELECT * FROM stock");
			rs = pstm.executeQuery();
			while(rs.next()){
				Stock p = new Stock();
				p.setId(rs.getInt("id_stock"));
				p.setStock(rs.getInt("stock"));
				p.setProducto(rs.getInt("id_producto"));
                p.setSucursal(rs.getInt("id_sucursal"));

				resultado.add(p);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(conn!=null) conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println("Resultado " + resultado);
		return resultado;
	}

	public Stock guardar(Stock stock) {
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement("INSERT INTO stock (stock, id_producto, id_sucursal) VALUES (?, ?, ?)");
			pstm.setInt(1, stock.getStock());
            pstm.setInt(2, stock.getProducto());
            pstm.setInt(3, stock.getSucursal());

			pstm.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(pstm!=null) pstm.close();
				if(conn!=null) conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return stock;
	}

	public Stock actualizar(Stock stock) {
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement("UPDATE stock SET stock = ?, id_producto = ?, id_sucursal = ? WHERE id_stock = ?");
			pstm.setInt(1, stock.getStock());
            pstm.setInt(2, stock.getProducto());
            pstm.setInt(3, stock.getSucursal());
			pstm.setInt(4, stock.getId());

			pstm.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(pstm!=null) pstm.close();
				if(conn!=null) conn.close();				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return stock;
	}
	
	public Integer getStock(Integer idSuc, Integer idPro) {
		Integer resultado = 0;
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement("SELECT * FROM stock WHERE id_sucursal = ? AND id_producto = ?");
			pstm.setInt(1, idSuc);
			pstm.setInt(2, idPro);
			rs = pstm.executeQuery();
			if(rs.next()){
				resultado = rs.getInt("stock");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(conn!=null) conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return resultado;
	}
	
	public void crearOActualizarStock(Integer idSucursal, Integer idProducto, Integer nuevoStock) {
	    Connection conn = Conexion.crearConexion();
	    PreparedStatement pstm = null;
	    
	    try {
	        // Verificar si la relación ya existe en la base de datos
	        String consultaExistencia = "SELECT * FROM stock WHERE id_sucursal = ? AND id_producto = ?";
	        pstm = conn.prepareStatement(consultaExistencia);
	        pstm.setInt(1, idSucursal);
	        pstm.setInt(2, idProducto);
	        ResultSet rs = pstm.executeQuery();
	        
	        if (rs.next()) {
	            // Si la relación existe, actualiza el stock
	            Integer idStock = rs.getInt("id_stock");
	            String consultaActualizar = "UPDATE stock SET stock = ? WHERE id_stock = ?";
	            pstm = conn.prepareStatement(consultaActualizar);
	            pstm.setInt(1, nuevoStock);
	            pstm.setInt(2, idStock);
	            pstm.executeUpdate();
	        } else {
	            // Si la relación no existe, crea un nuevo registro
	            String consultaInsertar = "INSERT INTO stock (stock, id_producto, id_sucursal) VALUES (?, ?, ?)";
	            pstm = conn.prepareStatement(consultaInsertar);
	            pstm.setInt(1, nuevoStock);
	            pstm.setInt(2, idProducto);
	            pstm.setInt(3, idSucursal);
	            pstm.executeUpdate();
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } finally {
	        try {
	            if (pstm != null) pstm.close();
	            if (conn != null) conn.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}

}
