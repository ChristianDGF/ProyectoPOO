package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import logico.Clinica;
import logico.Medico;
import logico.Paciente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListarPacientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtcodigopaciente;
	private JTable table;
	private JButton btnNewButton;
	private JButton btnmodificar;
	private Paciente mipaciente;

	public ListarPacientes() {
		setResizable(false);
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/icons8-employees-24.png"));
		this.setIconImage(icon.getImage());
		setBounds(100, 100, 809, 589);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Listado de Pacientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 767, 481);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cedula:");
		lblNewLabel.setBounds(12, 23, 56, 16);
		panel.add(lblNewLabel);

		txtcodigopaciente = new JFormattedTextField(createFormatter("###-#######-#"));
		txtcodigopaciente.setBounds(62, 20, 168, 22);
		panel.add(txtcodigopaciente);
		txtcodigopaciente.setColumns(10);

		btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mipaciente = Clinica.getInstance().BuscarPacienteByCedula(txtcodigopaciente.getText());
				if (mipaciente != null) {
					JOptionPane.showMessageDialog(null, "El Paciente fue encontrado!");
					btnmodificar.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "El Paciente no fue encontrado!");
				}

			}
		});
		btnNewButton.setBounds(243, 19, 97, 25);
		panel.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 52, 743, 416);
		panel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				if (index >= 0) {
					btnmodificar.setEnabled(true);
					mipaciente = Clinica.getInstance().getPacienteByCedula(table.getValueAt(index, 0).toString());
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
				if (Clinica.getLoginUser().getTipo().equalsIgnoreCase("Privilegiado") || Clinica.getLoginUser().getTipo().equalsIgnoreCase("Basico")) {
					btnmodificar.setText("Visualizar");
				}
				btnmodificar.setEnabled(false);
				btnmodificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(Clinica.getLoginUser().getEmpleado() instanceof Medico || Clinica.getLoginUser().getTipo().equalsIgnoreCase("Basico"))
						{
							RegistrarPaciente modificar = new RegistrarPaciente(mipaciente,true);
							modificar.setModal(true);
							modificar.setVisible(true);
							llenarTablaConPacientes();
						}else {
							RegistrarPaciente modificar = new RegistrarPaciente(mipaciente,false);
							modificar.setModal(true);
							modificar.setVisible(true);
							llenarTablaConPacientes();
						}
					}
				});
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
		llenarTablaConPacientes();
	}

	protected MaskFormatter createFormatter(String s) {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(s);
		} catch (java.text.ParseException exc) {
			System.err.println("formatter is bad: " + exc.getMessage());
			System.exit(-1);
		}
		return formatter;
	}

	public void llenarTablaConPacientes() {
		ArrayList<Paciente> pacientes = Clinica.getInstance().getMisPacientes();

		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Cedula");
		tableModel.addColumn("Codigo");
		tableModel.addColumn("Nombre");
		tableModel.addColumn("Edad");
		tableModel.addColumn("Género");

		for (Paciente paciente : pacientes) {
			Object[] rowData = new Object[5];
			rowData[0] = paciente.getCedula();
			rowData[1] = paciente.getCodigo();
			rowData[2] = paciente.getNombre();
			rowData[3] = paciente.getEdad();
			rowData[4] = paciente.getGenero();
			tableModel.addRow(rowData);
		}

		table.setModel(tableModel);
	}

}
