package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Enfermedad;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class SearchEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableEnfermedades;
	private DefaultTableModel enfermedadModel;
	private Object[] row;
	private ArrayList<Enfermedad> misEnfermedades = new ArrayList<Enfermedad>();
	private Enfermedad selected = null;
	private JComboBox comboBoxT;
	private JComboBox comboBoxE;
	private JButton seleccionarBtn;

	public SearchEnfermedad() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\Icons for project\\icons8-virus-50.png"));
		setResizable(false);
		setTitle("Buscar Enfermedad:");
		setBounds(100, 100, 895, 494);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Enfermedades:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 859, 396);
		contentPanel.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 608, 363);
		panel.add(scrollPane);
		{
			tableEnfermedades = new JTable();
			tableEnfermedades.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index = tableEnfermedades.getSelectedRow();
					if (index >= 0) {
						selected = misEnfermedades.get(index);
						seleccionarBtn.setEnabled(true);
					} else {
						selected = null;
						seleccionarBtn.setEnabled(false);
					}
				}
			});
			enfermedadModel = new DefaultTableModel();
			String headers[] = { "Codigo", "Nombre", "Estado", "Tipo" };
			enfermedadModel.setColumnIdentifiers(headers);
			tableEnfermedades.setModel(enfermedadModel);
			scrollPane.setViewportView(tableEnfermedades);
		}
		{
			JLabel lblNewLabel = new JLabel("Tipo:");
			lblNewLabel.setBounds(628, 23, 46, 14);
			panel.add(lblNewLabel);
		}

		comboBoxT = new JComboBox();
		comboBoxT.setModel(
				new DefaultComboBoxModel(new String[] { "<Seleccionar>", "Agudas", "Subagudas", "Cr\u00F3nicas" }));
		comboBoxT.setBounds(628, 48, 221, 20);
		panel.add(comboBoxT);

		JLabel lblNewLabel_1 = new JLabel("Estado:");
		lblNewLabel_1.setBounds(628, 79, 46, 14);
		panel.add(lblNewLabel_1);

		comboBoxE = new JComboBox();
		comboBoxE.setModel(new DefaultComboBoxModel(new String[] { "<Seleccionar>", "Vigilancia", "Registrada" }));
		comboBoxE.setBounds(628, 104, 221, 20);
		panel.add(comboBoxE);

		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkFields()) {
					loadEnfermedades();
				}
			}
		});
		btnFiltrar.setBounds(628, 321, 102, 48);
		panel.add(btnFiltrar);

		JButton btnTodos = new JButton("Todos");
		btnTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadAllEnfermedades();
			}
		});
		btnTodos.setBounds(747, 321, 102, 48);
		panel.add(btnTodos);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				seleccionarBtn = new JButton("Seleccionar");
				seleccionarBtn.setEnabled(false);
				seleccionarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selected != null) {
							JOptionPane.showMessageDialog(null, "La enfermedad ha sido seleccionada correctamente!");
							RegistrarConsulta.getEnfermedad(selected);
							dispose();
						}
					}
				});
				seleccionarBtn.setActionCommand("OK");
				buttonPane.add(seleccionarBtn);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadAllEnfermedades();
	}

	private boolean checkFields() {
		if (comboBoxT.getSelectedIndex() == 0 || comboBoxE.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar todos los Filtros!");
			return false;
		}
		return true;

	}

	public void loadEnfermedades() {
		enfermedadModel.setRowCount(0);
		misEnfermedades.clear();
		row = new Object[tableEnfermedades.getColumnCount()];

		if (comboBoxT.getSelectedIndex() == 0 || comboBoxE.getSelectedIndex() == 0) {
			for (Enfermedad enfermedad : Clinica.getInstance().getMisEnfermedades()) {
				if (enfermedad.getTipo().equals(comboBoxT.getSelectedItem())
						&& enfermedad.getEstado().equals(comboBoxE.getSelectedItem())) {
					misEnfermedades.add(enfermedad);
					row[0] = enfermedad.getCodigo();
					row[1] = enfermedad.getNombre();
					row[2] = enfermedad.getEstado();
					row[3] = enfermedad.getTipo();
					enfermedadModel.addRow(row);
				}
			}
		}
	}

	public void loadAllEnfermedades() {
		enfermedadModel.setRowCount(0);
		misEnfermedades.clear();
		row = new Object[tableEnfermedades.getColumnCount()];
		for (Enfermedad enfermedad : Clinica.getInstance().getMisEnfermedades()) {
			misEnfermedades.add(enfermedad);
			row[0] = enfermedad.getCodigo();
			row[1] = enfermedad.getNombre();
			row[2] = enfermedad.getEstado();
			row[3] = enfermedad.getTipo();
			enfermedadModel.addRow(row);

		}
	}
}
