package tp.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tp.modelos.EnumEstado;
import tp.modelos.OrdenProvision;

public class OrdenProvisionDao {
    public OrdenProvision buscarPorID(Integer id) {
		OrdenProvision resultado = new OrdenProvision();
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement("SELECT * FROM ordenProvision WHERE id_orden = ?");
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			if(rs.next()){
				resultado.setId(rs.getInt("id_orden"));
				resultado.setFecha(rs.getDate("fecha"));
				resultado.setSucursalDestino(rs.getInt("sucursalDestino"));
                resultado.setPlazoMaximo(rs.getInt("plazoMaximo"));
                if(rs.getString("estado").equals("PENDIENTE")){
					resultado.setEstado(EnumEstado.PENDIENTE);
				}else if(rs.getString("estado").equals("EN_PROCESO")){
					resultado.setEstado(EnumEstado.EN_PROCESO);
				}else if(rs.getString("estado").equals("LISTA")){
					resultado.setEstado(EnumEstado.LISTA);
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
			pstm = conn.prepareStatement("DELETE FROM ordenProvision WHERE id_orden = ?");
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

	public List<OrdenProvision> buscarTodos() {
		List<OrdenProvision> resultado = new ArrayList<OrdenProvision>();
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement("SELECT * FROM ordenProvision");
			rs = pstm.executeQuery();
			while(rs.next()){
				OrdenProvision p = new OrdenProvision();
				p.setId(rs.getInt("id_orden"));
				p.setFecha(rs.getDate("fecha"));
				p.setSucursalDestino(rs.getInt("sucursalDestino"));
                p.setPlazoMaximo(rs.getInt("plazoMaximo"));
                if(rs.getString("estado").equals("PENDIENTE")){
					p.setEstado(EnumEstado.PENDIENTE);
				}else if(rs.getString("estado").equals("EN_PROCESO")){
					p.setEstado(EnumEstado.EN_PROCESO);
				}else if(rs.getString("estado").equals("LISTA")){
					p.setEstado(EnumEstado.LISTA);
				}

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

	public OrdenProvision guardar(OrdenProvision orden) {
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement("INSERT INTO ordenProvision (fecha, sucursalDestino, plazoMaximo, estado) VALUES (?, ?, ?, ?)");
			pstm.setDate(1,orden.getFecha());
			pstm.setInt(2, orden.getSucursalDestino());
			pstm.setInt(3, orden.getPlazoMaximo());
			if(orden.getEstado().equals(EnumEstado.PENDIENTE)){
                pstm.setString(4, "PENDIENTE");
            }else if(orden.getEstado().equals(EnumEstado.EN_PROCESO)){
                pstm.setString(4, "EN_PROCESO");
            }else if(orden.getEstado().equals(EnumEstado.LISTA)){
                pstm.setString(4, "LISTA");
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
		return orden;
	}

	public OrdenProvision actualizar(OrdenProvision orden) {
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement("UPDATE ordenProvision SET fecha = ?, sucursalDestino = ?, plazoMaximo = ?, estado = ? WHERE id_orden = ?");
			pstm.setDate(1,orden.getFecha());
			pstm.setInt(2, orden.getSucursalDestino());
			pstm.setInt(3, orden.getPlazoMaximo());
			if(orden.getEstado().equals(EnumEstado.PENDIENTE)){
                pstm.setString(4, "PENDIENTE");
            }else if(orden.getEstado().equals(EnumEstado.EN_PROCESO)){
                pstm.setString(4, "EN_PROCESO");
            }else if(orden.getEstado().equals(EnumEstado.LISTA)){
                pstm.setString(4, "LISTA");
            }
			pstm.setInt(5, orden.getId());

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
