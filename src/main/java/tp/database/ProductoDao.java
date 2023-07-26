package tp.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tp.database.interfaces.ProductoInterface;
import tp.modelos.Producto;

public class ProductoDao implements ProductoInterface{
    
	public Producto buscarPorID(Integer id) {
		// TODO: Aparentemente no funca bien
		Producto resultado = new Producto();
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement("SELECT * FROM producto WHERE id_producto = ?");
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			if(rs.next()){
				resultado.setId(rs.getInt("id_producto"));
				resultado.setNombre(rs.getString("nombre"));
				resultado.setDescripcion(rs.getString("descripcion"));
                resultado.setPrecioUnitario(rs.getInt("precioUnitario"));
                resultado.setPeso(rs.getDouble("peso"));
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

	public void borrar(Integer id) {
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement("DELETE FROM producto WHERE id_producto = ?");
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

	public List<Producto> buscarTodos() {
		List<Producto> resultado = new ArrayList<Producto>();
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement("SELECT * FROM producto");
			rs = pstm.executeQuery();
			while(rs.next()){
				Producto p = new Producto();
				p.setId(rs.getInt("id_producto"));
				p.setNombre(rs.getString("nombre"));
				p.setDescripcion(rs.getString("descripcion"));
				p.setPrecioUnitario(rs.getInt("precioUnitario"));
                p.setPeso(rs.getDouble("peso"));

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

	public Producto guardar(Producto producto) {
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement("INSERT INTO producto (nombre, descripcion, precioUnitario, peso) VALUES (?, ?, ?, ?)");
			pstm.setString(1,producto.getNombre());
			pstm.setString(2, producto.getDescripcion());
			pstm.setInt(3, producto.getPrecioUnitario());
			pstm.setDouble(4, producto.getPeso());

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
		return producto;
	}

	public Producto actualizar(Producto producto) {
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement("UPDATE producto SET nombre = ?, descripcion = ?, precioUnitario = ?, peso = ? WHERE id_producto = ?");
			pstm.setString(1, producto.getNombre());
			pstm.setString(2, producto.getDescripcion());
			pstm.setInt(3, producto.getPrecioUnitario());
            pstm.setDouble(4, producto.getPeso());
			pstm.setInt(5, producto.getId());

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
		return producto;
	}
}
