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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarEmpleados extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JComboBox cmbcargo;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarEmpleados dialog = new ListarEmpleados();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
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
				 String cargoSeleccionado = cmbcargo.getSelectedItem().toString();
			        cargarEmpleadosPorCargo(cargoSeleccionado);
			}
		});
		cmbcargo.setModel(new DefaultComboBoxModel(new String[] {"<Todos>", "Medico", "Secretaria", "Vedel"}));
		cmbcargo.setBounds(65, 22, 171, 22);
		panel.add(cmbcargo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 54, 680, 367);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void cargarEmpleadosPorCargo(String cargo) {
	    ArrayList<Empleado> empleados = Clinica.getInstance().getEmpleadosPorCargo(cargo);

	    DefaultTableModel model = new DefaultTableModel();
	    model.addColumn("Cedula");
	    model.addColumn("Nombre");
	    model.addColumn("Apellido");
	    model.addColumn("Cargo");

	    for (Empleado empleado : empleados) {
	        model.addRow(new Object[] {
	            empleado.getCedula(),
	            empleado.getNombre(),
	            empleado.getApellido(),
	            empleado.getCargo()
	        });
	    }
	    table.setModel(model);
	}


}
