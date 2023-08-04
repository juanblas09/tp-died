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

public class EditarCamino extends JFrame {

	private JPanel contentPane;
	private JTextField textTiempo;
	private JTextField textCapacidad;
	
	SucursalInterface si = new SucursalDao();
	List<Sucursal> sucursales = si.buscarTodos();
	CaminoInterface ci = new CaminoDao();
	List<Camino> caminos = ci.buscarTodos();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarCamino frame = new EditarCamino();
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
	public EditarCamino() {
		setTitle("Editar Camino");
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditarCamino.class.getResource("/tp/gui/img/edit.png")));
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
		textTiempo.setBounds(174, 132, 250, 20);
		contentPane.add(textTiempo);
		
		JLabel lblNewLabel = new JLabel("Sucursal Origen");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 73, 154, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblHorarioDeApertura = new JLabel("Sucursal Destino");
		lblHorarioDeApertura.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHorarioDeApertura.setBounds(10, 104, 154, 14);
		contentPane.add(lblHorarioDeApertura);
		
		JLabel lblHorarioDeCierre = new JLabel("Tiempo tránsito");
		lblHorarioDeCierre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHorarioDeCierre.setBounds(10, 135, 154, 14);
		contentPane.add(lblHorarioDeCierre);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstado.setBounds(10, 197, 154, 14);
		contentPane.add(lblEstado);
		
		final JComboBox<EnumOperativa> comboEstado = new JComboBox<>();
		comboEstado.setModel(new DefaultComboBoxModel<>(EnumOperativa.values()));
		comboEstado.setBounds(174, 194, 250, 22);
		contentPane.add(comboEstado);
		
		
		textCapacidad = new JTextField();
		textCapacidad.setColumns(10);
		textCapacidad.setBounds(174, 163, 250, 20);
		contentPane.add(textCapacidad);
		
		JLabel lblCapacidadMxima = new JLabel("Capacidad máxima");
		lblCapacidadMxima.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCapacidadMxima.setBounds(10, 166, 154, 14);
		contentPane.add(lblCapacidadMxima);
		
		JComboBox comboDestino = new JComboBox<>(sucursales.toArray(new Sucursal[0]));
		comboDestino.setBounds(174, 100, 250, 22);
		contentPane.add(comboDestino);
		
		JComboBox comboOrigen = new JComboBox<>(sucursales.toArray(new Sucursal[0]));
		comboOrigen.setBounds(174, 69, 250, 22);
		contentPane.add(comboOrigen);

		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(10, 227, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblSeleccionarCamino = new JLabel("Seleccionar Camino");
		lblSeleccionarCamino.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSeleccionarCamino.setBounds(10, 15, 154, 14);
		contentPane.add(lblSeleccionarCamino);
		
		JComboBox<Camino> comboCamino = new JComboBox<Camino>(caminos.toArray(new Camino[0]));
		comboCamino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Camino objetoSeleccionado = (Camino) comboCamino.getSelectedItem();
                if (objetoSeleccionado != null) {
					textTiempo.setText(String.valueOf(objetoSeleccionado.getTiempoTransito()));
					textCapacidad.setText(String.valueOf(objetoSeleccionado.getCapacidadMaxima()));
                }
			}
		});
		comboCamino.setBounds(174, 11, 250, 22);
		contentPane.add(comboCamino);

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Camino c = new Camino(
						(((Camino) comboCamino.getSelectedItem()).getId()),
						((Sucursal) comboOrigen.getSelectedItem()).getId(),
						((Sucursal) comboDestino.getSelectedItem()).getId(),
						Integer.parseInt(textTiempo.getText()),
						Integer.parseInt(textCapacidad.getText()),
						(EnumOperativa)comboEstado.getSelectedItem());
				ci.actualizar(c);
				dispose();
			}
		});
		btnNewButton.setBounds(335, 227, 89, 23);
		contentPane.add(btnNewButton);
	}
}
