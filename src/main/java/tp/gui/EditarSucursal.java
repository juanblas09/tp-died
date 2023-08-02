package tp.gui;

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

public class EditarSucursal extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApertura;
	private JTextField textCierre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarSucursal frame = new EditarSucursal();
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
	public EditarSucursal() {
		setTitle("Editar Sucursal");
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
		
		textApertura = new JTextField();
		textApertura.setColumns(10);
		textApertura.setBounds(174, 75, 250, 20);
		contentPane.add(textApertura);
		
		textCierre = new JTextField();
		textCierre.setColumns(10);
		textCierre.setBounds(174, 106, 250, 20);
		contentPane.add(textCierre);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 47, 154, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblHorarioDeApertura = new JLabel("Horario de apertura (hh)");
		lblHorarioDeApertura.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHorarioDeApertura.setBounds(10, 78, 154, 14);
		contentPane.add(lblHorarioDeApertura);
		
		JLabel lblHorarioDeCierre = new JLabel("Horario de cierre (hh)");
		lblHorarioDeCierre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHorarioDeCierre.setBounds(10, 109, 154, 14);
		contentPane.add(lblHorarioDeCierre);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstado.setBounds(10, 140, 154, 14);
		contentPane.add(lblEstado);
		
		final JComboBox<EnumOperativa> comboEstado = new JComboBox<>();
		comboEstado.setModel(new DefaultComboBoxModel<>(EnumOperativa.values()));
		comboEstado.setBounds(174, 137, 250, 22);
		contentPane.add(comboEstado);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				SucursalInterface si = new SucursalDao();
				Sucursal s = new Sucursal(textNombre.getText(),Integer.parseInt(textApertura.getText()),Integer.parseInt(textCierre.getText()),(EnumOperativa)comboEstado.getSelectedItem());
				si.guardar(s);
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
		
		JComboBox<String> comboBox = new JComboBox();
		comboBox.setBounds(174, 11, 250, 22);
		contentPane.add(comboBox);
		
		JLabel lblSeleccionarSucursal = new JLabel("Seleccionar sucursal");
		lblSeleccionarSucursal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSeleccionarSucursal.setBounds(10, 15, 154, 14);
		contentPane.add(lblSeleccionarSucursal);
	}
}
