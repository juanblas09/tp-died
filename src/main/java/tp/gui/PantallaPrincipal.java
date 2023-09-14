package tp.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import tp.database.SucursalDao;
import tp.database.interfaces.SucursalInterface;
import tp.database.CaminoDao;
import tp.database.interfaces.CaminoInterface;
import tp.modelos.Sucursal;
import tp.modelos.Camino;

import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Toolkit;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;


public class PantallaPrincipal extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal frame = new PantallaPrincipal();
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
	private JPanel contentPane;
	private JTextField textField;
	GrafoGUI mapa = new GrafoGUI();
	int x = 30;
	int y = 30;
	int radio = 40;
	public PantallaPrincipal() {
		FlatDraculaIJTheme.setup();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaPrincipal.class.getResource("/tp/gui/img/truck.png")));
		setTitle("Sistema Logistico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Nuevo");
		mnNewMenu.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/tp/gui/img/add.png")));
		menuBar.add(mnNewMenu);
		
		JMenuItem nuevaSucursalMenuItem = new JMenuItem("Sucursal");
		nuevaSucursalMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				NuevaSucursal ns = new NuevaSucursal();
				ns.setVisible(true);
			}
		});
		mnNewMenu.add(nuevaSucursalMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Camino");
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				NuevaCamino nc = new NuevaCamino();
				nc.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Producto");
		mntmNewMenuItem_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				NuevaProducto np = new NuevaProducto();
				np.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_10);
		
		JMenu mnNewMenu_2 = new JMenu("Editar");
		mnNewMenu_2.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/tp/gui/img/edit.png")));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Sucursal");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				EditarSucursal es = new EditarSucursal();
				es.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Camino");
		mntmNewMenuItem_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				EditarCamino ec = new EditarCamino();
				ec.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_12 = new JMenuItem("Producto");
		mnNewMenu_2.add(mntmNewMenuItem_12);
		
		JMenuItem mntmNewMenuItem_14 = new JMenuItem("Stock");
		mntmNewMenuItem_14.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				EditarStock es = new EditarStock();
				es.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_14);
		mntmNewMenuItem_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				EditarProducto ep = new EditarProducto();
				ep.setVisible(true);
			}
		});
		
		JMenu mnNewMenu_3 = new JMenu("Eliminar");
		mnNewMenu_3.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/tp/gui/img/delete.png")));
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Sucursal");
		mntmNewMenuItem_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				EliminarSucursal es = new EliminarSucursal();
				es.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Camino");
		mntmNewMenuItem_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				EliminarCamino ec = new EliminarCamino();
				ec.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("Producto");
		mnNewMenu_3.add(mntmNewMenuItem_11);
		mntmNewMenuItem_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				EliminarProducto ep = new EliminarProducto();
				ep.setVisible(true);
			}
		});
		
		JMenu mnNewMenu_4 = new JMenu("Buscar");
		mnNewMenu_4.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/tp/gui/img/search.png")));
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Sucursal");
		mntmNewMenuItem_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				BuscarSucursal bs = new BuscarSucursal();
				bs.setVisible(true);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Camino");
		mntmNewMenuItem_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				BuscarCamino bc = new BuscarCamino();
				bc.setVisible(true);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_9);
		
		JMenuItem mntmNewMenuItem_13 = new JMenuItem("Producto");
		mntmNewMenuItem_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				BuscarProducto bp = new BuscarProducto();
				bp.setVisible(true);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_13);
		
		JMenu mnNewMenu_1 = new JMenu("Otros");
		mnNewMenu_1.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/tp/gui/img/truck.png")));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Generar Orden Provision");
		mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GenerarOrdenProvision gop = new GenerarOrdenProvision();
				gop.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_15 = new JMenuItem("Consultar Ordenes Pendientes");
		mntmNewMenuItem_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				AsignarRecorrido ar = new AsignarRecorrido();
				ar.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_15);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Page Rank");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Flujo Maximo");
		mnNewMenu_1.add(mntmNewMenuItem_4);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*
		 * Desarrollo de grafo
		 * */
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Detalles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setToolTipText("");
		panel_3.setBounds(544, 39, 230, 457);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblSucursal = new JLabel("Sucursal:");
		lblSucursal.setBounds(10, 24, 210, 14);
		panel_3.add(lblSucursal);

		mapa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(mapa.getSelectedCircle()!=null) {
					lblSucursal.setText("Sucursal: " + mapa.getSelectedCircle().getLabel());
				} else {
					lblSucursal.setText("Sucursal: ");
				}
			}
		});
		
		//GrafoGUI mapa = new GrafoGUI();
		mapa.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mapa.setBackground(new Color(255, 255, 255));
		mapa.setBounds(10, 11, 524, 485);
		contentPane.add(mapa);
		mapa.setLayout(null);
		//int x = 50;
		//int y = 50;
		//int radio = 50;
		
		
		
		SucursalInterface si = new SucursalDao();
		List<Sucursal> sucursales = si.buscarTodos();
		
		for(Sucursal s: sucursales){
            mapa.addNodo(x, y, radio, s.getNombre());
            x += 30;
            y += 30;
        }
		
		CaminoInterface ci = new CaminoDao();
		List<Camino> caminos = ci.buscarTodos();

        for(Camino c: caminos){
            mapa.addArista(si.buscarPorID(c.getSucursalOrigen()).getNombre(),si.buscarPorID(c.getSucursalDestino()).getNombre());
        }
		
		

		
		/*
		 * 
		 * */
		
		
		
		JButton btnNewButton = new JButton("Recargar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mapa.limpiarCanvas();
				x = 30;
				y = 30;
				dibujarNodos();
			}
		});
		btnNewButton.setBounds(544, 11, 89, 23);
		contentPane.add(btnNewButton);
	}
	
	public void dibujarNodos() {
		SucursalInterface si = new SucursalDao();
		List<Sucursal> sucursales = si.buscarTodos();
		
		for(Sucursal s: sucursales){
            mapa.addNodo(x, y, radio, s.getNombre());
            x += 40;
            y += 40;
            
        }
		
		CaminoInterface ci = new CaminoDao();
		List<Camino> caminos = ci.buscarTodos();

        for(Camino c: caminos){
            mapa.addArista(si.buscarPorID(c.getSucursalOrigen()).getNombre(),si.buscarPorID(c.getSucursalDestino()).getNombre());
        }
	}
}
