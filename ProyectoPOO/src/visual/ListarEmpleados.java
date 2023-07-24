package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Empleado;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListarEmpleados extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JComboBox cmbcargo;
	private JScrollPane scrollPane;
	private Empleado miempleado = null;
	private JButton btnmodificar;
	private JButton btneliminar;
	

	public ListarEmpleados() {
		setBounds(100, 100, 756, 542);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Listado de Empleados:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 714, 434);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblcargo = new JLabel("Cargo:");
		lblcargo.setBounds(12, 25, 56, 16);
		panel.add(lblcargo);
		
		cmbcargo = new JComboBox();
		cmbcargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			        cargarEmpleadosPorCargo();
			}
		});
		cmbcargo.setModel(new DefaultComboBoxModel(new String[] {"<Todos>", "Medico", "Secretaria", "Vedel"}));
		cmbcargo.setBounds(65, 22, 171, 22);
		panel.add(cmbcargo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 54, 680, 367);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				if (index >= 0) {
					btnmodificar.setEnabled(true);
					btneliminar.setEnabled(true);
					miempleado = Clinica.getInstance().getEmpleadoByCode(table.getValueAt(index, 0).toString());
				}
				
			}
		});
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnmodificar = new JButton("Modificar");
				btnmodificar.setEnabled(false);
				btnmodificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						RegistrarEmpleado modificar = new RegistrarEmpleado(miempleado);
						modificar.setModal(true);
						modificar.setVisible(true);
						cargarEmpleadosPorCargo();
					}
				});
				
				btneliminar = new JButton("Eliminar");
				btneliminar.setEnabled(false);
				btneliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (miempleado != null) {
							int option = JOptionPane
									.showConfirmDialog(null,
											"Estas seguro(a) que desea eliminar el empleado con el codigo: "
													+ miempleado.getCodigo(),
													"Confirmacion", JOptionPane.OK_CANCEL_OPTION);
							if (option == JOptionPane.OK_OPTION) {
								Clinica.getInstance().EliminarEmpleado(miempleado);
								btneliminar.setEnabled(false);
								btnmodificar.setEnabled(false);
								cargarEmpleadosPorCargo();
							}
						}
					}
				});
				buttonPane.add(btneliminar);
				btnmodificar.setActionCommand("OK");
				buttonPane.add(btnmodificar);
				getRootPane().setDefaultButton(btnmodificar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		cargarEmpleadosPorCargo();
	}

	private void cargarEmpleadosPorCargo() {
		String cargoSeleccionado = cmbcargo.getSelectedItem().toString();
		ArrayList<Empleado>listaporcargo =  Clinica.getInstance().getEmpleadosPorCargo(cargoSeleccionado);

	    DefaultTableModel model = new DefaultTableModel();
	    model.addColumn("Codigo");
	    model.addColumn("Cedula");
	    model.addColumn("Nombre");
	    model.addColumn("Apellido");
	    model.addColumn("Cargo");

	    for (Empleado empleado : listaporcargo) {
	        model.addRow(new Object[] {
	        	empleado.getCodigo(),
	            empleado.getCedula(),
	            empleado.getNombre(),
	            empleado.getApellido(),
	            empleado.getCargo()
	        });
	    }
	    table.setModel(model);
	}


}
