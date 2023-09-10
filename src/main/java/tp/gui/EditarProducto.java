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

import tp.database.ProductoDao;
import tp.database.interfaces.ProductoInterface;
import tp.modelos.EnumOperativa;
import tp.modelos.Producto;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textDescripcion;
	private JTextField textPrecio;
	ProductoInterface pi = new ProductoDao();
	int idSeleccionada;
	private JTextField textPeso;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarProducto frame = new EditarProducto();
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
	public EditarProducto() {

		setTitle("Editar Producto");
		setIconImage(Toolkit.getDefaultToolkit().getImage(NuevaSucursal.class.getResource("/tp/gui/img/edit.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textNombre = new JTextField();
		textNombre.setBounds(174, 44, 250, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textDescripcion = new JTextField();
		textDescripcion.setColumns(10);
		textDescripcion.setBounds(174, 75, 250, 20);
		contentPane.add(textDescripcion);
		
		textPrecio = new JTextField();
		textPrecio.setColumns(10);
		textPrecio.setBounds(174, 106, 250, 20);
		contentPane.add(textPrecio);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 47, 154, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblHorarioDeApertura = new JLabel("Descripcion");
		lblHorarioDeApertura.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHorarioDeApertura.setBounds(10, 78, 154, 14);
		contentPane.add(lblHorarioDeApertura);
		
		JLabel lblHorarioDeCierre = new JLabel("Precio Unitario");
		lblHorarioDeCierre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHorarioDeCierre.setBounds(10, 109, 154, 14);
		contentPane.add(lblHorarioDeCierre);
		
		JLabel lblEstado = new JLabel("Peso");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstado.setBounds(10, 140, 154, 14);
		contentPane.add(lblEstado);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Producto p = new Producto(idSeleccionada,textNombre.getText(),textDescripcion.getText(),Integer.parseInt(textPrecio.getText()),Double.parseDouble(textPeso.getText()));
				pi.actualizar(p);
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
		List<Producto> productos = pi.buscarTodos();
		final JComboBox comboBox = new JComboBox<>(productos.toArray(new Producto[0]));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto objetoSeleccionado = (Producto) comboBox.getSelectedItem();
                if (objetoSeleccionado != null) {
					textNombre.setText(objetoSeleccionado.getNombre());
					textDescripcion.setText(String.valueOf(objetoSeleccionado.getDescripcion()));
					textPrecio.setText(String.valueOf(objetoSeleccionado.getPrecioUnitario()));
					textPeso.setText(String.valueOf(objetoSeleccionado.getPeso()));
					idSeleccionada = objetoSeleccionado.getId();
                }
			}
		});
		comboBox.setBounds(174, 11, 250, 22);
		contentPane.add(comboBox);

		/*
		 * 
		 */
		
		JLabel lblSeleccionarSucursal = new JLabel("Seleccionar producto");
		lblSeleccionarSucursal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSeleccionarSucursal.setBounds(10, 15, 154, 14);
		contentPane.add(lblSeleccionarSucursal);
		
		textPeso = new JTextField();
		textPeso.setColumns(10);
		textPeso.setBounds(174, 137, 250, 20);
		contentPane.add(textPeso);
	}
}
