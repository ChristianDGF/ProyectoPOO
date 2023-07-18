package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Empleado;
import logico.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistrarUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtusuario;
	private JTextField txtpassword;
	private JTextField txtempleado;
	private JTable table;
	private JComboBox cmbcargo;
	private Empleado selected = null;
	private JComboBox cmbtipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarUsuario dialog = new RegistrarUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarUsuario() {
		setBounds(100, 100, 592, 436);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblusuario = new JLabel("Usuario:");
				lblusuario.setBounds(24, 13, 56, 16);
				panel.add(lblusuario);
			}
			{
				txtusuario = new JTextField();
				txtusuario.setBounds(104, 10, 166, 22);
				panel.add(txtusuario);
				txtusuario.setColumns(10);
			}
			{
				JLabel lblpass = new JLabel("Contrase\u00F1a:");
				lblpass.setBounds(12, 45, 75, 16);
				panel.add(lblpass);
			}
			{
				txtpassword = new JTextField();
				txtpassword.setColumns(10);
				txtpassword.setBounds(104, 42, 166, 22);
				panel.add(txtpassword);
			}
			
			cmbtipo = new JComboBox();
			cmbtipo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccionar>", "Basico", "Privilegiado", "Administrador"}));
			cmbtipo.setBounds(374, 10, 166, 22);
			panel.add(cmbtipo);
			
			JLabel lblTipo = new JLabel("Acceso:");
			lblTipo.setBounds(294, 13, 56, 16);
			panel.add(lblTipo);
			
			JLabel lblEmpleado = new JLabel("Empleado:");
			lblEmpleado.setBounds(294, 45, 69, 16);
			panel.add(lblEmpleado);
			
			txtempleado = new JTextField();
			txtempleado.setEditable(false);
			txtempleado.setBounds(374, 42, 166, 22);
			panel.add(txtempleado);
			txtempleado.setColumns(10);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "Lista de Empleados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(12, 88, 540, 243);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Cargo:");
			lblNewLabel.setBounds(12, 23, 56, 16);
			panel_1.add(lblNewLabel);
			
			cmbcargo = new JComboBox();
			cmbcargo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cargarEmpleadosPorCargo();
				}
			});
			cmbcargo.setModel(new DefaultComboBoxModel(new String[] {"<Todos>", "Medico", "Secretaria", "Vedel"}));
			cmbcargo.setBounds(58, 20, 144, 22);
			panel_1.add(cmbcargo);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(22, 52, 506, 180);
			panel_1.add(scrollPane);
			
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index = table.getSelectedRow();
					selected = Clinica.getInstance().getEmpleadoByCode(table.getValueAt(index, 0).toString());
					
				}
			});
			scrollPane.setViewportView(table);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnregistrar = new JButton("Registrar");
				
				btnregistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(selected == null)
						{
							JOptionPane.showMessageDialog(null, "Debe seleccionar un empleado", "Error",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						else
						{
							User aux = null;
							
							aux = new User( cmbtipo.getSelectedItem().toString(),txtusuario.getText(), txtpassword.getText(), selected);
							Clinica.getInstance().AgregarUser(aux);
							JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Registro",
									JOptionPane.INFORMATION_MESSAGE);
							clean();
						}
					}
				});
				btnregistrar.setActionCommand("OK");
				buttonPane.add(btnregistrar);
			}
			{
				JButton btncancelar = new JButton("Cancelar");
				btncancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btncancelar.setActionCommand("Cancel");
				buttonPane.add(btncancelar);
			}
		}
		cargarEmpleadosPorCargo();
	}
	
	private void clean() {
		selected = null;
		txtusuario.setText("");
		txtpassword.setText("");
		cmbtipo.setSelectedIndex(0);
		
	}

	private void cargarEmpleadosPorCargo() {
		String cargoSeleccionado = cmbcargo.getSelectedItem().toString();
	    ArrayList<Empleado> empleados = Clinica.getInstance().getEmpleadosPorCargo(cargoSeleccionado);

	    DefaultTableModel model = new DefaultTableModel();
	    model.addColumn("Codigo");
	    model.addColumn("Cedula");
	    model.addColumn("Nombre");
	    model.addColumn("Apellido");
	    model.addColumn("Cargo");

	    for (Empleado empleado : empleados) {
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
