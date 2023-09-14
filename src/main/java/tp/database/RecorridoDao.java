package tp.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import tp.database.interfaces.RecorridoInterface;

public class RecorridoDao implements RecorridoInterface {

	public void guardar(Integer id_orden, String recorrido) {
		Connection conn = Conexion.crearConexion();
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement("INSERT INTO recorrido (id_orden, recorrido) VALUES (?, ?)");
			pstm.setInt(1, id_orden);
			pstm.setString(2, recorrido);

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

}
