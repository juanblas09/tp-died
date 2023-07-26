package tp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import tp.database.interfaces.SucursalInterface;
import tp.modelos.EnumOperativa;
import tp.modelos.Sucursal;

import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws SQLException {
		SucursalInterface suc = new SucursalDao();
		Sucursal sucursal = new Sucursal(4, "Monte Vera", 6, 12, EnumOperativa.NO_OPERATIVA);
		//suc.guardar(sucursal);
		//suc.borrar(4);
		//System.out.println(suc.buscarPorID(4).getNombre());
		 /*
		List<Sucursal> sucs = suc.buscarTodos();
		
		for(Sucursal s: sucs) {
			System.out.println(s.getNombre());
		}*/
		//suc.actualizar(sucursal);
	}
}
