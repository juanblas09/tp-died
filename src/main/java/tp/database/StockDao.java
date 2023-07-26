package tp.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tp.modelos.Stock;

public class StockDao {
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
}
