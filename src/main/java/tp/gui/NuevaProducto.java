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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NuevaProducto extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textDescripcion;
	private JTextField textPrecio;
	private JTextField textPeso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevaProducto frame = new NuevaProducto();
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
	public NuevaProducto() {
		setTitle("Nuevo Producto");
		setIconImage(Toolkit.getDefaultToolkit().getImage(NuevaProducto.class.getResource("/tp/gui/img/add.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textNombre = new JTextField();
		textNombre.setBounds(174, 11, 250, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textDescripcion = new JTextField();
		textDescripcion.setColumns(10);
		textDescripcion.setBounds(174, 42, 250, 20);
		contentPane.add(textDescripcion);
		
		textPrecio = new JTextField();
		textPrecio.setColumns(10);
		textPrecio.setBounds(174, 73, 250, 20);
		contentPane.add(textPrecio);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 14, 154, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblHorarioDeApertura = new JLabel("Descripcion");
		lblHorarioDeApertura.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHorarioDeApertura.setBounds(10, 45, 154, 14);
		contentPane.add(lblHorarioDeApertura);
		
		JLabel lblHorarioDeCierre = new JLabel("Precio Unitario");
		lblHorarioDeCierre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHorarioDeCierre.setBounds(10, 76, 154, 14);
		contentPane.add(lblHorarioDeCierre);
		
		JLabel lblEstado = new JLabel("Peso");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstado.setBounds(10, 107, 154, 14);
		contentPane.add(lblEstado);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ProductoInterface pi = new ProductoDao();
				Producto p = new Producto(textNombre.getText(),textDescripcion.getText(),Integer.parseInt(textPrecio.getText()),Double.parseDouble(textPeso.getText()));
				pi.guardar(p);
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
		
		textPeso = new JTextField();
		textPeso.setColumns(10);
		textPeso.setBounds(174, 104, 250, 20);
		contentPane.add(textPeso);
	}
}
