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

public class EditarStock extends JFrame {

	private JPanel contentPane;
	
	SucursalInterface si = new SucursalDao();
	List<Sucursal> sucursales = si.buscarTodos();
	ProductoInterface pi = new ProductoDao();
	List<Producto> productos = pi.buscarTodos();
	StockInterface sti = new StockDao();
	
	private JTextField textStock;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarStock frame = new EditarStock();
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
	public EditarStock() {
		setTitle("Editar Stock");
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditarStock.class.getResource("/tp/gui/img/edit.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sucursal");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 73, 154, 14);
		contentPane.add(lblNewLabel);
		
		JComboBox comboSucursal = new JComboBox<>(sucursales.toArray(new Sucursal[0]));
		comboSucursal.setBounds(174, 69, 250, 22);
		contentPane.add(comboSucursal);

		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(10, 227, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblSeleccionarCamino = new JLabel("Seleccionar Producto");
		lblSeleccionarCamino.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSeleccionarCamino.setBounds(10, 15, 154, 14);
		contentPane.add(lblSeleccionarCamino);
		
		JComboBox<Producto> comboProducto = new JComboBox<Producto>(productos.toArray(new Producto[0]));
		comboProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Sucursal selectedSucursal = (Sucursal) comboSucursal.getSelectedItem();
				    Producto selectedProducto = (Producto) comboProducto.getSelectedItem();

				    if (selectedSucursal != null && selectedProducto != null) {
				        // Verifica que los elementos no sean nulos antes de obtener sus IDs
				        Integer sucursalId = selectedSucursal.getId();
				        Integer productoId = selectedProducto.getId();

				        // Obtén el stock y establece el valor en textStock
				        Integer stock = sti.getStock(sucursalId, productoId);
				        textStock.setText(stock.toString());
				    } else {
				        // Maneja el caso en que los elementos sean nulos
				        textStock.setText("N/A"); // O muestra un mensaje de error
				    }
			}
		});
		comboSucursal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Sucursal selectedSucursal = (Sucursal) comboSucursal.getSelectedItem();
			    Producto selectedProducto = (Producto) comboProducto.getSelectedItem();

			    if (selectedSucursal != null && selectedProducto != null) {
			        // Verifica que los elementos no sean nulos antes de obtener sus IDs
			        Integer sucursalId = selectedSucursal.getId();
			        Integer productoId = selectedProducto.getId();

			        // Obtén el stock y establece el valor en textStock
			        Integer stock = sti.getStock(sucursalId, productoId);
			        textStock.setText(stock.toString());
			    } else {
			        // Maneja el caso en que los elementos sean nulos
			        textStock.setText("N/A"); // O muestra un mensaje de error
			    }
			}
		});
		comboProducto.setBounds(174, 11, 250, 22);
		contentPane.add(comboProducto);

		JButton btnNewButton = new JButton("Actualizar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				sti.crearOActualizarStock(((Sucursal)comboSucursal.getSelectedItem()).getId(), ((Producto)comboProducto.getSelectedItem()).getId(), Integer.parseInt(textStock.getText()));
				dispose();
			}
		});
		btnNewButton.setBounds(335, 227, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStock.setBounds(10, 135, 154, 14);
		contentPane.add(lblStock);
		
		textStock = new JTextField();
		textStock.setBounds(174, 132, 250, 20);
		contentPane.add(textStock);
		textStock.setColumns(10);
		textStock.setText((sti.getStock(((Sucursal)comboSucursal.getSelectedItem()).getId(), ((Producto)comboProducto.getSelectedItem()).getId()).toString()));
	}
}
