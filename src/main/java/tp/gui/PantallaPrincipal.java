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


public class PantallaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	
	GrafoGUI mapa = new GrafoGUI();
	int x = 50;
	int y = 50;
	int radio = 50;

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
	public PantallaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaPrincipal.class.getResource("/tp/gui/img/truck.png")));
		setTitle("Sistema Logistico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Nuevo");
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
		
		JMenu mnNewMenu_2 = new JMenu("Editar");
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
		
		JMenu mnNewMenu_3 = new JMenu("Eliminar");
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
		
		JMenu mnNewMenu_4 = new JMenu("Buscar");
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
		
		JMenu mnNewMenu_1 = new JMenu("Generar");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Orden Provision");
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
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
		
		//GrafoGUI mapa = new GrafoGUI();
		mapa.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mapa.setBackground(new Color(255, 255, 255));
		mapa.setBounds(10, 11, 524, 517);
		contentPane.add(mapa);
		mapa.setLayout(null);
		//int x = 50;
		//int y = 50;
		//int radio = 50;
		
		
		
		SucursalInterface si = new SucursalDao();
		List<Sucursal> sucursales = si.buscarTodos();
		
		for(Sucursal s: sucursales){
            mapa.addNodo(x, y, radio, s.getNombre());
            x += 50;
            y += 50;
        }
		
		CaminoInterface ci = new CaminoDao();
		List<Camino> caminos = ci.buscarTodos();

        for(Camino c: caminos){
            mapa.addArista(si.buscarPorID(c.getSucursalOrigen()).getNombre(),si.buscarPorID(c.getSucursalDestino()).getNombre());
        }
		
		

		
		/*
		 * 
		 * */
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Detalles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setToolTipText("");
		panel_3.setBounds(544, 39, 230, 489);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnNewButton = new JButton("Recargar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
            x += 50;
            y += 50;
        }
		
		CaminoInterface ci = new CaminoDao();
		List<Camino> caminos = ci.buscarTodos();

        for(Camino c: caminos){
            mapa.addArista(si.buscarPorID(c.getSucursalOrigen()).getNombre(),si.buscarPorID(c.getSucursalDestino()).getNombre());
        }
	}
}
