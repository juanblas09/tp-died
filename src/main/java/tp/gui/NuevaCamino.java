package tp.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import tp.modelos.*;
import tp.database.*;
import tp.database.interfaces.*;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class NuevaCamino extends JFrame {

	private JPanel contentPane;
	private JTextField textTiempo;
	private JTextField textCapacidad;
	
	SucursalInterface si = new SucursalDao();
	List<Sucursal> sucursales = si.buscarTodos();
	int idOrigenSeleccionada,idDestinoSeleccionada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevaCamino frame = new NuevaCamino();
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
	public NuevaCamino() {
		setTitle("Nuevo Camino");
		setIconImage(Toolkit.getDefaultToolkit().getImage(NuevaCamino.class.getResource("/tp/gui/img/add.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textTiempo = new JTextField();
		textTiempo.setColumns(10);
		textTiempo.setBounds(174, 73, 250, 20);
		contentPane.add(textTiempo);
		
		JLabel lblNewLabel = new JLabel("Sucursal Origen");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 14, 154, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblHorarioDeApertura = new JLabel("Sucursal Destino");
		lblHorarioDeApertura.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHorarioDeApertura.setBounds(10, 45, 154, 14);
		contentPane.add(lblHorarioDeApertura);
		
		JLabel lblHorarioDeCierre = new JLabel("Tiempo tránsito");
		lblHorarioDeCierre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHorarioDeCierre.setBounds(10, 76, 154, 14);
		contentPane.add(lblHorarioDeCierre);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstado.setBounds(10, 138, 154, 14);
		contentPane.add(lblEstado);
		
		final JComboBox<EnumOperativa> comboEstado = new JComboBox<>();
		comboEstado.setModel(new DefaultComboBoxModel<>(EnumOperativa.values()));
		comboEstado.setBounds(174, 135, 250, 22);
		contentPane.add(comboEstado);
		
		
		textCapacidad = new JTextField();
		textCapacidad.setColumns(10);
		textCapacidad.setBounds(174, 104, 250, 20);
		contentPane.add(textCapacidad);
		
		JLabel lblCapacidadMxima = new JLabel("Capacidad máxima");
		lblCapacidadMxima.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCapacidadMxima.setBounds(10, 107, 154, 14);
		contentPane.add(lblCapacidadMxima);
		
		JComboBox comboDestino = new JComboBox<>(sucursales.toArray(new Sucursal[0]));
		comboDestino.setBounds(174, 41, 250, 22);
		contentPane.add(comboDestino);
		
		JComboBox comboOrigen = new JComboBox<>(sucursales.toArray(new Sucursal[0]));
		comboOrigen.setBounds(174, 10, 250, 22);
		contentPane.add(comboOrigen);

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				CaminoInterface ci = new CaminoDao();
				Camino c = new Camino(
						((Sucursal) comboOrigen.getSelectedItem()).getId(),
						((Sucursal) comboDestino.getSelectedItem()).getId(),
						Integer.parseInt(textTiempo.getText()),
						Integer.parseInt(textCapacidad.getText()),
						(EnumOperativa)comboEstado.getSelectedItem());
				ci.guardar(c);
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
	}
}
