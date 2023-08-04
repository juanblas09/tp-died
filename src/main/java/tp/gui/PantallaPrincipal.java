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


public class PantallaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(405, 11, 369, 189);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 385, 189);
		contentPane.add(panel);
		panel.setLayout(null);
		
		/*
		 * Desarrollo de grafo
		 * */
		
		GrafoGUI mapa = new GrafoGUI();
		mapa.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mapa.setBackground(new Color(255, 255, 255));
		mapa.setBounds(10, 211, 524, 317);
		contentPane.add(mapa);
		mapa.setLayout(null);
		int x = 50;
		int y = 50;
		int radio = 50;
		
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
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setToolTipText("");
		panel_3.setBounds(544, 211, 230, 317);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
	}
}
