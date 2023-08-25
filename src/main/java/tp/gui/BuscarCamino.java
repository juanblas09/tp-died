package tp.gui;

import java.util.ArrayList;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import tp.database.CaminoDao;
import tp.database.interfaces.CaminoInterface;
import tp.modelos.EnumOperativa;
import tp.modelos.Camino;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class BuscarCamino extends JFrame {

	private JPanel contentPane;
	CaminoInterface si = new CaminoDao();
	int idSeleccionada;
	private JTextField textBusqueda;
	private JTable table;
	List<Camino> caminoes = si.buscarTodos();
	
	String[] columnas = {"ID", "Recorrido", "Tiempo", "Capacidad", "Operativa"};
	DefaultTableModel model = new DefaultTableModel(columnas, 0);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarCamino frame = new BuscarCamino();
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
	public BuscarCamino() {
		
		
		setTitle("Buscar Camino");
		setIconImage(Toolkit.getDefaultToolkit().getImage(NuevaCamino.class.getResource("/tp/gui/img/search.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Búsqueda libre (ingrese cualquier atributo)");
		lblNewLabel.setBounds(10, 11, 564, 14);
		contentPane.add(lblNewLabel);
		
		textBusqueda = new JTextField();
		textBusqueda.setBounds(10, 36, 564, 20);
		contentPane.add(textBusqueda);
		textBusqueda.setColumns(10);
		textBusqueda.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				filtrarTabla();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				filtrarTabla();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				filtrarTabla();
			}
		});
		
		for(Camino s: caminoes) {
			Object[] fila = {
					s.getId(),
					s.toString(),
					s.getTiempoTransito(),
					s.getCapacidadMaxima(),
					s.getOperativa().toString()
			};
			model.addRow(fila);
		}
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setLocation(10, 67);
		scrollPane.setSize(564, 383);
		contentPane.add(scrollPane);
		
		
	}
	
	private void filtrarTabla() {
		String filtro = textBusqueda.getText().toLowerCase();
        List<Camino> caminoesFiltradas = new ArrayList<>();

        for (Camino camino : caminoes) {
            if ((String.valueOf(camino.getId())+camino.toString()+String.valueOf(camino.getTiempoTransito()+String.valueOf(camino.getCapacidadMaxima()+camino.getOperativa().toString()))).toLowerCase().contains(filtro)) {
                caminoesFiltradas.add(camino);
            }
        }

        // Limpiar el modelo de la tabla
        model.setRowCount(0);

        // Agregar las caminoes filtradas al modelo de la tabla
        for (Camino camino : caminoesFiltradas) {
            Object[] fila = {
            		camino.getId(),
                    camino.toString(),
                    camino.getTiempoTransito(),
                    camino.getCapacidadMaxima(),
                    camino.getOperativa()
            };
            model.addRow(fila);
        }
	}
}
