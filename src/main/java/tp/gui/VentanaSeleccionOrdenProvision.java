package tp.gui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import tp.modelos.OrdenProvision;

import tp.database.*;
import tp.database.interfaces.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaSeleccionOrdenProvision extends JFrame {
    private JTable tablaOrdenes;
    private JButton btnSeleccionar;
    private List<OrdenProvision> ordenesPendientes; // Lista de órdenes pendientes recuperadas desde la base de datos
    
    OrdenProvisionInterface opi = new OrdenProvisionDao();
    
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaSeleccionOrdenProvision frame = new VentanaSeleccionOrdenProvision();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    public VentanaSeleccionOrdenProvision() {
        setTitle("Seleccionar Orden de Provisión Pendiente");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Llamamos a la función para cargar órdenes pendientes desde tu OrdenProvisionDao
        ordenesPendientes = opi.buscarTodosPendientes(); // Asume que tienes esta función en tu dao

        // Crear un arreglo bidimensional de objetos para los datos de la tabla
        Object[][] data = new Object[ordenesPendientes.size()][];
        for (int i = 0; i < ordenesPendientes.size(); i++) {
            OrdenProvision orden = ordenesPendientes.get(i);
            data[i] = new Object[]{
                    orden.getId(),
                    orden.getFecha(),
                    orden.getSucursalDestino().toString(),
                    // Agrega más columnas según tus propiedades de OrdenProvision
            };
        }

        // Nombres de las columnas
        String[] columnNames = {
                "ID",
                "Fecha",
                "Destino",
                // Agrega más nombres de columnas aquí
        };

        // Crear un modelo de tabla no editable
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaOrdenes = new JTable(tableModel);
        tablaOrdenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permitir seleccionar solo una fila
        JScrollPane scrollPane = new JScrollPane(tablaOrdenes);

        btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaOrdenes.getSelectedRow();
                if (selectedRow != -1) {
                    // Obtiene el ID de la orden seleccionada
                    int idOrdenSeleccionada = (int) tablaOrdenes.getValueAt(selectedRow, 0);

                    // Puedes hacer lo que necesites con el ID de la orden seleccionada
                    System.out.println("ID de la orden seleccionada: " + idOrdenSeleccionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una orden.");
                }
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnSeleccionar, BorderLayout.SOUTH);
        add(panel);

        setLocationRelativeTo(null); // Centra la ventana en la pantalla
    }
}