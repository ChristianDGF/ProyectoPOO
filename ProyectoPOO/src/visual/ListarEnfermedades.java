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
import logico.Enfermedad;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class ListarEnfermedades extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox comboBox;
	private JTable table;
	private JButton btneliminar;
	private JButton btnmodificar;
	private Enfermedad mienfermedad = null;

	public ListarEnfermedades() {
		setResizable(false);
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/icons8-virus-50.png"));
		this.setIconImage(icon.getImage());
		setTitle("Enfermedades");
		setBounds(100, 100, 765, 584);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Listado de Enfermedades", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 723, 476);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Estado:");
		lblNewLabel.setBounds(12, 26, 56, 16);
		panel.add(lblNewLabel);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarEnfermedades();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Todos>", "Vigilancia", "Normal", "Investigacion"}));
		comboBox.setBounds(80, 23, 132, 22);
		panel.add(comboBox);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 54, 699, 409);
		panel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				if (index >= 0 && !Clinica.getLoginUser().getTipo().equalsIgnoreCase("basico")) {
					btnmodificar.setEnabled(true);
					if (Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador")) {
						btneliminar.setEnabled(true);
					}
					mienfermedad = Clinica.getInstance().getEnfermedadByCode(table.getValueAt(index, 0).toString());
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
				if (Clinica.getLoginUser().getTipo().equalsIgnoreCase("Privilegiado")) {
					btnmodificar.setText("Visualizar");
				}
				btnmodificar.setEnabled(false);
				btnmodificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador"))
						{
							RegistrarEnfermedad modificar = new RegistrarEnfermedad(mienfermedad,false);
							modificar.setModal(true);
							modificar.setVisible(true);
							cargarEnfermedades();
						}else {
							RegistrarEnfermedad modificar = new RegistrarEnfermedad(mienfermedad,true);
							modificar.setModal(true);
							modificar.setVisible(true);
							cargarEnfermedades();
						}

					}
				});

				btneliminar = new JButton("Eliminar");
				btneliminar.setEnabled(false);
				btneliminar.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        if (mienfermedad != null) {
				            int pacientesConEnfermedad = Clinica.getInstance().contarPacientesConEnfermedad(mienfermedad.getCodigo());
				            if (pacientesConEnfermedad == 0) {
				                int option = JOptionPane.showConfirmDialog(null,
				                        "Estas seguro(a) que desea eliminar la Enfermedad con el Codigo: " + mienfermedad.getCodigo(),
				                        "Confirmacion", JOptionPane.OK_CANCEL_OPTION);
				                if (option == JOptionPane.OK_OPTION) {
				                    Clinica.getInstance().EliminarEnfermedad(mienfermedad);
				                    btneliminar.setEnabled(false);
				                    btnmodificar.setEnabled(false);
				                    cargarEnfermedades();
				                }
				            } else {
				                JOptionPane.showMessageDialog(null,
				                        "No se puede eliminar la enfermedad porque está asignada a " + pacientesConEnfermedad + " paciente(s).",
				                        "Error de eliminación", JOptionPane.ERROR_MESSAGE);
				            }
				        }
				    }
				});
				buttonPane.add(btneliminar);
				if(!Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador"))
				{
					btneliminar.setVisible(false);
				}
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
		cargarEnfermedades();
	}

	private void cargarEnfermedades() {
		String estadoSeleccionado = comboBox.getSelectedItem().toString();

		ArrayList<Enfermedad> enfermedades = Clinica.getInstance().obtenerEnfermedadesPorEstado(estadoSeleccionado);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Código");
		model.addColumn("Nombre");
		model.addColumn("Tipo");
		model.addColumn("Estado");

		for (Enfermedad enfermedad : enfermedades) {
			Object[] row = new Object[5];
			row[0] = enfermedad.getCodigo();
			row[1] = enfermedad.getNombre();
			row[2] = enfermedad.getTipo();
			row[3] = enfermedad.getEstado();
			model.addRow(row);
		}

		table.setModel(model);
	}
}
