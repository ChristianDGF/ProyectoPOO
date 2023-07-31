package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Medico;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Toolkit;

public class ListarMedicos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JScrollPane scrollPane;
	private JComboBox cmbespecialidad;
	private Medico mimedico;

	public ListarMedicos() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/icons8-employees-24.png"));
		this.setIconImage(icon.getImage());
		setTitle("Medicos");
		setBounds(100, 100, 713, 559);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Listar Medicos",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblespecialidad = new JLabel("Especialidad:");
		lblespecialidad.setBounds(12, 24, 84, 16);
		panel.add(lblespecialidad);

		cmbespecialidad = new JComboBox();
		cmbespecialidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarMedicosPorEspecialidad();
			}
		});
		cmbespecialidad.setModel(new DefaultComboBoxModel(new String[] { "<Todas>", "Cardiolog\u00EDa",
				"Neurolog\u00EDa", "Nefrolog\u00EDa", "Pediatr\u00EDa", "Reumatolog\u00EDa",
				"Cirug\u00EDa pedi\u00E1trica", "Cirug\u00EDa ortop\u00E9dica y traumatolog\u00EDa",
				"Cirug\u00EDa tor\u00E1cica", "Neurocirug\u00EDa", "An\u00E1lisis cl\u00EDnicos",
				"Anatom\u00EDa patol\u00F3gica", "Bioqu\u00EDmica cl\u00EDnica", "Farmacolog\u00EDa cl\u00EDnica",
				"Inmunolog\u00EDa", "Medicina nuclear", "Microbiolog\u00EDa y parasitolog\u00EDa",
				"Neurofisiolog\u00EDa cl\u00EDnica", "Radiodiagn\u00F3stico" }));
		cmbespecialidad.setBounds(101, 21, 182, 22);
		panel.add(cmbespecialidad);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 55, 665, 411);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		listarMedicosPorEspecialidad();
	}

	private void listarMedicosPorEspecialidad() {
		String especialidadSeleccionada = cmbespecialidad.getSelectedItem().toString();

		ArrayList<Medico> medicos = Clinica.getInstance().obtenerMedicosPorEspecialidad(especialidadSeleccionada);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Nombre");
		model.addColumn("Apellido");
		model.addColumn("Teléfono");
		model.addColumn("Especialidad");
		model.addColumn("Exequatur");

		for (Medico medico : medicos) {
			Object[] row = new Object[5];
			row[0] = medico.getNombre();
			row[1] = medico.getApellido();
			row[2] = medico.getTelefono();
			row[3] = medico.getEspecialidad();
			row[4] = medico.getExequatur();
			model.addRow(row);
		}

		table.setModel(model);
	}
}
