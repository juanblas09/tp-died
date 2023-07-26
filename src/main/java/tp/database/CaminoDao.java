package tp.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tp.modelos.EnumOperativa;
import tp.modelos.Camino;

public class CaminoDao {
    public Camino buscarPorID(Integer id) {
		Camino resultado = new Camino();
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement("SELECT * FROM camino WHERE id_camino = ?");
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			if(rs.next()){
				resultado.setId(rs.getInt("id_camino"));
				resultado.setSucursalOrigen(rs.getInt("sucursalOrigen"));
                resultado.setSucursalDestino(rs.getInt("sucursalDestino"));
                resultado.setTiempoTransito(rs.getInt("tiempoTransito"));
                resultado.setCapacidadMaxima(rs.getInt("capacidadMaxima"));
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
		return resultado;
	}

	public void borrar(Integer id) {
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement("DELETE FROM camino WHERE id_camino = ?");
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

	public List<Camino> buscarTodos() {
		List<Camino> resultado = new ArrayList<Camino>();
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement("SELECT * FROM camino");
			rs = pstm.executeQuery();
			while(rs.next()){
				Camino c = new Camino();
				c.setId(rs.getInt("id_camino"));
				c.setSucursalOrigen(rs.getInt("sucursalOrigen"));
                c.setSucursalDestino(rs.getInt("sucursalDestino"));
                c.setTiempoTransito(rs.getInt("tiempoTransito"));
                c.setCapacidadMaxima(rs.getInt("capacidadMaxima"));
				if(rs.getString("operativa").equals("OPERATIVA")){
					c.setOperativa(EnumOperativa.OPERATIVA);
				}else if(rs.getString("operativa").equals("NO_OPERATIVA")){
					c.setOperativa(EnumOperativa.NO_OPERATIVA);
				}

				resultado.add(c);
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

	public Camino guardar(Camino camino) {
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement("INSERT INTO camino (sucursalOrigen, sucursalDestino, tiempoTransito, capacidadMaxima, operativa) VALUES (?, ?, ?, ?, ?)");
			pstm.setInt(1,camino.getSucursalOrigen());
			pstm.setInt(2, camino.getSucursalDestino());
			pstm.setInt(3, camino.getTiempoTransito());
            pstm.setInt(4, camino.getCapacidadMaxima());
			if(camino.getOperativa().equals(EnumOperativa.OPERATIVA)){
				pstm.setString(5, "OPERATIVA");
			}else if(camino.getOperativa().equals(EnumOperativa.NO_OPERATIVA)){
				pstm.setString(5, "NO_OPERATIVA");
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
		return camino;
	}

	public Camino actualizar(Camino camino) {
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement("UPDATE camino SET sucursalOrigen = ?, sucursalDestino = ?, tiempoTransito = ?, capacidadMaxima = ?, operativa = ? WHERE id_camino = ?");
			pstm.setInt(1,camino.getSucursalOrigen());
			pstm.setInt(2, camino.getSucursalDestino());
			pstm.setInt(3, camino.getTiempoTransito());
            pstm.setInt(4, camino.getCapacidadMaxima());
			if(camino.getOperativa().equals(EnumOperativa.OPERATIVA)){
				pstm.setString(5, "OPERATIVA");
			}else if(camino.getOperativa().equals(EnumOperativa.NO_OPERATIVA)){
				pstm.setString(5, "NO_OPERATIVA");
			}
			pstm.setInt(6, camino.getId());

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
		return camino;
	}
}
