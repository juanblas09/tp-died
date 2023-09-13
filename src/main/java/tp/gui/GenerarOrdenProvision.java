package tp.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import tp.database.ProductoDao;
import tp.database.SucursalDao;
import tp.database.interfaces.ProductoInterface;
import tp.database.interfaces.SucursalInterface;
import tp.database.OrdenProvisionDao;
import tp.database.interfaces.OrdenProvisionInterface;
import tp.database.OrdenProvisionItemDao;
import tp.database.interfaces.OrdenProvisionItemInterface;
import tp.modelos.OrdenProvision;
import tp.modelos.OrdenProvisionItem;
import tp.modelos.Producto;
import tp.modelos.Sucursal;
import tp.modelos.EnumEstado;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Window.Type;

public class GenerarOrdenProvision extends JFrame {

	private JPanel contentPane;
	
	Date fechaActual = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String fechaActualStr = dateFormat.format(fechaActual);
    
	ProductoInterface pi = new ProductoDao();
	List<Producto> productos = pi.buscarTodos();
	
	SucursalInterface si = new SucursalDao();
	List<Sucursal> sucursales = si.buscarTodos();
	
	OrdenProvisionInterface opi = new OrdenProvisionDao();
	
	OrdenProvisionItemInterface opii = new OrdenProvisionItemDao();
	
	
	private JTextField textField;
	private JTextField textCantidad;
	private JTable table;
    private DefaultTableModel tableModel;
    private List<OrdenProvisionItem> items = new ArrayList<OrdenProvisionItem>();
    private Integer idOrden = opi.crearOrdenProvisionVacia();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerarOrdenProvision frame = new GenerarOrdenProvision();
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
	public GenerarOrdenProvision() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GenerarOrdenProvision.class.getResource("/tp/gui/img/truck.png")));
		setTitle("Generar Orden de Provision");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos de la orden", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 664, 93);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fecha");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(45, 31, 89, 14);
		panel.add(lblNewLabel);
		
		JLabel lblSucursalDestino = new JLabel("Sucursal Destino");
		lblSucursalDestino.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSucursalDestino.setBounds(10, 56, 125, 14);
		panel.add(lblSucursalDestino);
		
		JComboBox<Sucursal> comboDestino = new JComboBox<Sucursal>(sucursales.toArray(new Sucursal[0]));
		comboDestino.setBounds(145, 52, 180, 22);
		panel.add(comboDestino);
		
		textField = new JTextField();
		textField.setBounds(438, 53, 216, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblTiempoMximoh = new JLabel("Tiempo máximo (h)");
		lblTiempoMximoh.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTiempoMximoh.setBounds(326, 56, 102, 14);
		panel.add(lblTiempoMximoh);
		
		JLabel lblDate = new JLabel("Fecha");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDate.setText(fechaActualStr);
		lblDate.setBounds(144, 31, 89, 14);
		panel.add(lblDate);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Datos de la orden", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 115, 664, 300);
		contentPane.add(panel_1);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setHorizontalAlignment(SwingConstants.TRAILING);
		lblProducto.setBounds(10, 26, 54, 14);
		panel_1.add(lblProducto);
		
		JComboBox<Producto> comboProducto = new JComboBox<Producto>(productos.toArray(new Producto[0]));
		comboProducto.setBounds(74, 22, 200, 22);
		panel_1.add(comboProducto);
		
		textCantidad = new JTextField();
		textCantidad.setColumns(10);
		textCantidad.setBounds(348, 22, 200, 20);
		panel_1.add(textCantidad);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCantidad.setBounds(284, 26, 54, 14);
		panel_1.add(lblCantidad);
		
		JButton btnAdd = new JButton("Agregar");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Obtener el producto seleccionado y la cantidad
                Producto productoSeleccionado = (Producto) comboProducto.getSelectedItem();
                String cantidad = textCantidad.getText();

                // Verificar si la cantidad es un número válido
                try {
                    int cantidadInt = Integer.parseInt(cantidad);

                    // Agregar los datos a la tabla
                    Object[] rowData = {productoSeleccionado.getNombre(), cantidadInt};
                    tableModel.addRow(rowData);

                    // Limpiar los campos después de agregar
                    comboProducto.setSelectedIndex(0);
                    items.add(new OrdenProvisionItem(idOrden, Integer.parseInt(textCantidad.getText()), productoSeleccionado.getId()));
                    System.out.println(items);
                    textCantidad.setText("");
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresa una cantidad válida.");
                }
			}
		});
		btnAdd.setBounds(558, 22, 89, 23);
		panel_1.add(btnAdd);
		
		// Crear el modelo de datos para la tabla
        String[] columnNames = {"Producto", "Cantidad"};
        tableModel = new DefaultTableModel(columnNames, 0);

        // Crear la tabla con el modelo
        table = new JTable(tableModel);
        table.setBounds(10, 60, 644, 230);

        // Agregar la tabla a un JScrollPane para manejar el desplazamiento si es necesario
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 60, 644, 230);
        panel_1.add(scrollPane);
        
        JButton btnNewButton = new JButton("Crear");
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		OrdenProvision op = new OrdenProvision(idOrden,fechaActual,((Sucursal)comboDestino.getSelectedItem()).getId(),Integer.parseInt(textField.getText()), EnumEstado.PENDIENTE);
        		opi.actualizar(op);
        		for(OrdenProvisionItem o: items) {
        			opii.guardar(o);
        		}
        		dispose();
        	}
        });
        btnNewButton.setIcon(new ImageIcon(GenerarOrdenProvision.class.getResource("/tp/gui/img/add.png")));
        btnNewButton.setBounds(524, 426, 150, 24);
        contentPane.add(btnNewButton);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		opi.borrar(idOrden);
        		dispose();
        	}
        });
        btnCancelar.setIcon(new ImageIcon(GenerarOrdenProvision.class.getResource("/tp/gui/img/delete.png")));
        btnCancelar.setBounds(364, 427, 150, 24);
        contentPane.add(btnCancelar);

	}
}
