package tp.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tp.modelos.Camino;
import tp.modelos.EnumEstado;
import tp.modelos.EnumOperativa;
import tp.modelos.Graph;
import tp.modelos.OrdenProvision;
import tp.modelos.OrdenProvisionItem;
import tp.modelos.Sucursal;
import tp.modelos.Vertex;
import tp.database.*;
import tp.database.interfaces.*;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.TitledBorder;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class AsignarRecorrido extends JFrame {

	private JPanel contentPane;
	
	OrdenProvisionInterface opi = new OrdenProvisionDao();
	SucursalInterface si = new SucursalDao();
	CaminoInterface ci = new CaminoDao();
	OrdenProvisionItemInterface opii = new OrdenProvisionItemDao();
	StockInterface sti = new StockDao();

	private List<OrdenProvision> ordenesPendientes = opi.buscarTodosPendientes();
	private List<Sucursal> sucursales = si.buscarTodos();
	private List<Camino> caminos = ci.buscarTodos();
	private List<Sucursal> sucursalesConStock; 
	
	Graph<String> graph = new Graph<String>();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AsignarRecorrido frame = new AsignarRecorrido();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	JComboBox<OrdenProvision> comboBox = new JComboBox<OrdenProvision>(ordenesPendientes.toArray(new OrdenProvision[0]));
	JList<Sucursal> listSucursalesConStock = new JList<>();
	JList<String> list = new JList<>();
	private final JPanel panel_2 = new JPanel();
	

	public AsignarRecorrido() {
		for(Sucursal s: sucursales) {
			if(s.getOperativa().equals(EnumOperativa.OPERATIVA)) {
				graph.addNodo(s.getNombre());
			}
		}
		for(Camino c: caminos) {
			if(c.getOperativa().equals(EnumOperativa.OPERATIVA)) {
				Sucursal origen = si.buscarPorID(c.getSucursalOrigen());
				Sucursal destino = si.buscarPorID(c.getSucursalDestino());
				graph.conectar(origen.getNombre(), destino.getNombre(), c.getTiempoTransito());
			}
		}
		sucursalesConStock();
		setIconImage(Toolkit.getDefaultToolkit().getImage(AsignarRecorrido.class.getResource("/tp/gui/img/truck.png")));
		setTitle("Asignar Recorrido");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Seleccionar Orden", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 564, 116);
		contentPane.add(panel);
		panel.setLayout(null);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sucursalesConStock();
			}
		});
		comboBox.setBounds(10, 21, 544, 22);
		panel.add(comboBox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Sucursales con stock", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 137, 150, 224);
		contentPane.add(panel_1);
		
		listSucursalesConStock.setBounds(10, 21, 130, 192);
		panel_1.add(listSucursalesConStock);
		panel_2.setBorder(new TitledBorder(null, "Rutas posibles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(170, 137, 404, 224);
		
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		
		list.setBounds(10, 24, 384, 189);
		panel_2.add(list);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrdenProvision selected = (OrdenProvision)comboBox.getSelectedItem();
				RecorridoInterface ri = new RecorridoDao();
				ri.guardar(selected.getId(), list.getSelectedValue());
				selected.setEstado(EnumEstado.EN_PROCESO);
				opi.actualizar(selected);
				dispose();
			}
		});
		btnNewButton.setBounds(485, 377, 89, 23);
		
		contentPane.add(btnNewButton);
	}
	
	private List<List<Vertex<String>>> pathsFinales = new ArrayList<>();
	private final JButton btnNewButton = new JButton("Asignar");
	
	public void sucursalesConStock() {
		OrdenProvision orden = (OrdenProvision)comboBox.getSelectedItem();
		DefaultListModel<Sucursal> model = new DefaultListModel<>();
		sucursalesConStock = new ArrayList<>();
		List<OrdenProvisionItem> items = opii.buscarTodosPorOrden(orden.getId());
		for(Sucursal s: sucursales) {
			Boolean aux = true;
			for(OrdenProvisionItem i: items) {
				if(sti.getStock(s.getId(), i.getProducto())<i.getCantidad()) {
					aux=false;
				}
			}
			if(aux) {
				model.addElement(s);
				sucursalesConStock.add(s);
			}
		}
		listSucursalesConStock.setModel(model);
		
		Sucursal destino = si.buscarPorID(orden.getSucursalDestino());
		/*for(Sucursal s: sucursalesConStock) {
			List<List<Vertex<String>>> paths = graph.findAllPaths(s.getNombre(), destino.getNombre());
			for (List<Vertex<String>> path : paths) {
			    System.out.println("Path: " + path);
			}
		}*/
		pathsFinales.clear(); // Limpiar la lista antes de agregar nuevos paths
        for (Sucursal s : sucursalesConStock) {
            List<List<Vertex<String>>> paths = graph.findAllPaths(s.getNombre(), destino.getNombre());
            pathsFinales.addAll(paths);
        }
        
        // Mostrar los paths finales en la JList o en cualquier otro componente visual
        DefaultListModel<String> pathsModel = new DefaultListModel<>();
        for (List<Vertex<String>> path : pathsFinales) {
            StringBuilder pathString = new StringBuilder();
            for (Vertex<String> vertex : path) {
                pathString.append(vertex.getValue()).append(" -> ");
            }
            // Eliminar la flecha "->" adicional del final
            pathString.setLength(pathString.length() - 4);
            pathsModel.addElement(pathString.toString());
        }
        // Asignar el modelo a la JList (reemplaza "listPaths" con el nombre real de tu componente visual)
        list.setModel(pathsModel);
	}
}
