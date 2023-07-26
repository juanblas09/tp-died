package tp.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tp.database.interfaces.SucursalInterface;
import tp.modelos.EnumOperativa;
import tp.modelos.Sucursal;

public class SucursalDao implements SucursalInterface{

	public Sucursal buscarPorID(Integer id) {
		// TODO: Aparentemente no funca bien
		Sucursal resultado = new Sucursal();
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement("SELECT * FROM sucursal WHERE id_sucursal = ?");
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			if(rs.next()){
				resultado.setId(rs.getInt("id_sucursal"));
				resultado.setNombre(rs.getString("nombre"));
				resultado.setHorarioApertura(rs.getInt("horarioapertura"));
				resultado.setHorarioCierre(rs.getInt("horariocierre"));
				if(rs.getString("operativa").equals("OPERATIVA")){
					resultado.setOperativa(EnumOperativa.OPERATIVA);
				}else if(rs.getString("operativa").equals("NO_OPERATIVA")){
					resultado.setOperativa(EnumOperativa.NO_OPERATIVA);
				}
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
			pstm = conn.prepareStatement("DELETE FROM sucursal WHERE id_sucursal = ?");
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

	public List<Sucursal> buscarTodos() {
		List<Sucursal> resultado = new ArrayList<Sucursal>();
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement("SELECT * FROM sucursal");
			rs = pstm.executeQuery();
			while(rs.next()){
				Sucursal s = new Sucursal();
				s.setId(rs.getInt("id_sucursal"));
				s.setNombre(rs.getString("nombre"));
				s.setHorarioApertura(rs.getInt("horarioapertura"));
				s.setHorarioCierre(rs.getInt("horariocierre"));
				if(rs.getString("operativa").equals("OPERATIVA")){
					s.setOperativa(EnumOperativa.OPERATIVA);
				}else if(rs.getString("operativa").equals("NO_OPERATIVA")){
					s.setOperativa(EnumOperativa.NO_OPERATIVA);
				}

				resultado.add(s);
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

	public Sucursal guardar(Sucursal sucursal) {
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement("INSERT INTO sucursal (nombre, horarioapertura, horariocierre, operativa) VALUES (?, ?, ?, ?)");
			pstm.setString(1,sucursal.getNombre());
			pstm.setInt(2, sucursal.getHorarioApertura());
			pstm.setInt(3, sucursal.getHorarioCierre());
			if(sucursal.getOperativa().equals(EnumOperativa.OPERATIVA)){
				pstm.setString(4, "OPERATIVA");
			}else if(sucursal.getOperativa().equals(EnumOperativa.NO_OPERATIVA)){
				pstm.setString(4, "NO_OPERATIVA");
			}

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
		return sucursal;
	}

	public Sucursal actualizar(Sucursal sucursal) {
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement("UPDATE sucursal SET nombre = ?, horarioApertura = ?, horariocierre = ?, operativa = ? WHERE id_sucursal = ?");
			pstm.setString(1, sucursal.getNombre());
			pstm.setInt(2, sucursal.getHorarioApertura());
			pstm.setInt(3, sucursal.getHorarioCierre());
			if(sucursal.getOperativa().equals(EnumOperativa.OPERATIVA)){
				pstm.setString(4, "OPERATIVA");
			}else if(sucursal.getOperativa().equals(EnumOperativa.NO_OPERATIVA)){
				pstm.setString(4, "NO_OPERATIVA");
			}
			pstm.setInt(5, sucursal.getId());

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
		return sucursal;
	}

}
