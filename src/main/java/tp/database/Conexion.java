package tp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Conexion {
	private static final String url = "jdbc:postgresql://localhost:5432/tp-died";
	private static final String user = "postgres";
	private static final String pass = "jbtschopp";
	
	public static Connection crearConexion() {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url,user,pass);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex){
			ex.printStackTrace();
		}
		return conn;
	}

	
}
