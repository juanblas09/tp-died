package tp.modelos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.stream.Collectors;

import tp.database.SucursalDao;
import tp.database.interfaces.SucursalInterface;
import tp.database.CaminoDao;
import tp.database.interfaces.CaminoInterface;

public class Graph {
	private List<Camino> edges;
	private List<Sucursal> vertexs;

	public Graph(){
		this.edges = new ArrayList<Camino>();
		this.vertexs = new ArrayList<Sucursal>();
	}
	
	public void addNodo(Sucursal nodo) {
		this.vertexs.add(nodo);
	}
	
	public void addVertice(Camino camino) {
		this.edges.add(camino);
	}
	
	public void construirGrafo() {
		SucursalInterface si = new SucursalDao();
		List<Sucursal> sucursales = si.buscarTodos();
		CaminoInterface ci = new CaminoDao();
		List<Camino> caminos = ci.buscarTodos();
		for(Sucursal s: sucursales) {
			addNodo(s);
		}
		for(Camino c: caminos) {
			addVertice(c);
		}
	}
	
	public List<Sucursal> getVertices(){
		return this.vertexs;
	}
}
