package tp.gui;

import java.util.List;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import tp.database.SucursalDao;
import tp.database.interfaces.SucursalInterface;
import tp.modelos.EnumOperativa;
import tp.modelos.Sucursal;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EliminarSucursal extends JFrame {

	private JPanel contentPane;
	SucursalInterface si = new SucursalDao();
	int idSeleccionada;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EliminarSucursal frame = new EliminarSucursal();
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
	public EliminarSucursal() {

		setTitle("Eliminar Sucursal");
		setIconImage(Toolkit.getDefaultToolkit().getImage(EliminarSucursal.class.getResource("/tp/gui/img/delete.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				SucursalInterface si = new SucursalDao();
				si.borrar(idSeleccionada);
				dispose();
			}
		});
		btnNewButton.setBounds(335, 227, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(10, 227, 89, 23);
		contentPane.add(btnNewButton_1);
		
		/*
		 * SELECCION DE SUCURSAL
		 */
		List<Sucursal> sucursales = si.buscarTodos();
		final JComboBox comboBox = new JComboBox<>(sucursales.toArray(new Sucursal[0]));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sucursal objetoSeleccionado = (Sucursal) comboBox.getSelectedItem();
                if (objetoSeleccionado != null) {
					idSeleccionada = objetoSeleccionado.getId();
                }
			}
		});
		comboBox.setBounds(174, 92, 250, 22);
		contentPane.add(comboBox);

		/*
		 * 
		 */
		
		JLabel lblSeleccionarSucursal = new JLabel("Seleccionar sucursal");
		lblSeleccionarSucursal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSeleccionarSucursal.setBounds(10, 96, 154, 14);
		contentPane.add(lblSeleccionarSucursal);
	}
}
