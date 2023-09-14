package tp.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tp.database.interfaces.OrdenProvisionItemInterface;
import tp.modelos.OrdenProvisionItem;

public class OrdenProvisionItemDao implements OrdenProvisionItemInterface {
    public OrdenProvisionItem buscarPorID(Integer id) {
		OrdenProvisionItem resultado = new OrdenProvisionItem();
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement("SELECT * FROM ordenProvisionItem WHERE id_orden = ?");
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			if(rs.next()){
				resultado.setId(rs.getInt("id_orden"));
				resultado.setOrden(rs.getInt("orden"));
				resultado.setCantidad(rs.getInt("cantidad"));
                resultado.setProducto(rs.getInt("producto"));
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
			pstm = conn.prepareStatement("DELETE FROM ordenProvisionItem WHERE id_orden = ?");
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

	public List<OrdenProvisionItem> buscarTodos() {
		List<OrdenProvisionItem> resultado = new ArrayList<OrdenProvisionItem>();
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement("SELECT * FROM ordenProvisionItem");
			rs = pstm.executeQuery();
			while(rs.next()){
				OrdenProvisionItem p = new OrdenProvisionItem();
				p.setId(rs.getInt("id_orden"));
				p.setOrden(rs.getInt("orden"));
				p.setCantidad(rs.getInt("cantidad"));
                p.setProducto(rs.getInt("producto"));

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
	
	public List<OrdenProvisionItem> buscarTodosPorOrden(Integer id) {
		List<OrdenProvisionItem> resultado = new ArrayList<OrdenProvisionItem>();
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement("SELECT * FROM ordenProvisionItem WHERE orden = ?");
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			while(rs.next()){
				OrdenProvisionItem p = new OrdenProvisionItem();
				p.setId(rs.getInt("id_orden"));
				p.setOrden(rs.getInt("orden"));
				p.setCantidad(rs.getInt("cantidad"));
                p.setProducto(rs.getInt("producto"));

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

	public OrdenProvisionItem guardar(OrdenProvisionItem orden) {
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement("INSERT INTO ordenProvisionItem (orden, cantidad, producto) VALUES (?, ?, ?)");
			pstm.setInt(1, orden.getOrden());
            pstm.setInt(2, orden.getCantidad());
            pstm.setInt(3, orden.getProducto());

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
		return orden;
	}

	public OrdenProvisionItem actualizar(OrdenProvisionItem orden) {
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement("UPDATE ordenProvisionItem SET orden = ?, cantidad = ?, producto = ? WHERE id_orden = ?");
			pstm.setInt(1, orden.getOrden());
            pstm.setInt(2, orden.getCantidad());
            pstm.setInt(3, orden.getProducto());
			pstm.setInt(4, orden.getId());

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
		return orden;
	}
}
