package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Medico;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Toolkit;

public class SearchMedico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableMedicos;
	private JComboBox comboBoxE;
	private JComboBox comboBoxD;
	private JRadioButton rdbtnM;
	private JRadioButton rdbtnF;
	private DefaultTableModel medicoModel;
	private Object[] row;
	private ArrayList<Medico> misMedicos = new ArrayList<Medico>();
	private Medico selected = null;
	private JButton seleccionarBtn;

	public SearchMedico() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\Icons for project\\icons8-doctor-50.png"));
		setResizable(false);
		setTitle("Buscar Medico");
		setBounds(100, 100, 895, 494);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Medicos:",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 11, 859, 396);
			contentPanel.add(panel);
			panel.setLayout(null);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 22, 608, 363);
			panel.add(scrollPane);

			tableMedicos = new JTable();
			tableMedicos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index = tableMedicos.getSelectedRow();
					if (index >= 0) {
						selected = misMedicos.get(index);
						seleccionarBtn.setEnabled(true);
					} else {
						selected = null;
						seleccionarBtn.setEnabled(false);
					}
				}
			});
			medicoModel = new DefaultTableModel();
			String headers[] = { "Exequatur", "Nombre", "Apellido", "N. Consultorio" };
			medicoModel.setColumnIdentifiers(headers);
			tableMedicos.setModel(medicoModel);
			scrollPane.setViewportView(tableMedicos);

			JLabel lblNewLabel = new JLabel("Especialidad:");
			lblNewLabel.setBounds(628, 29, 88, 14);
			panel.add(lblNewLabel);

			comboBoxE = new JComboBox();
			comboBoxE.setModel(new DefaultComboBoxModel(new String[] { "<Seleccionar>", "Cardiolog\u00EDa",
					"Neurolog\u00EDa", "Nefrolog\u00EDa", "Pediatr\u00EDa", "Reumatolog\u00EDa",
					"Cirug\u00EDa pedi\u00E1trica", "Cirug\u00EDa ortop\u00E9dica y traumatolog\u00EDa",
					"Cirug\u00EDa tor\u00E1cica", "Neurocirug\u00EDa", "An\u00E1lisis cl\u00EDnicos",
					"Anatom\u00EDa patol\u00F3gica", "Bioqu\u00EDmica cl\u00EDnica", "Farmacolog\u00EDa cl\u00EDnica",
					"Inmunolog\u00EDa", "Medicina nuclear", "Microbiolog\u00EDa y parasitolog\u00EDa",
					"Neurofisiolog\u00EDa cl\u00EDnica", "Radiodiagn\u00F3stico" }));
			comboBoxE.setBounds(628, 72, 221, 20);
			panel.add(comboBoxE);

			JLabel lblNewLabel_1 = new JLabel("Sexo:");
			lblNewLabel_1.setBounds(628, 121, 46, 14);
			panel.add(lblNewLabel_1);

			JLabel lblNewLabel_2 = new JLabel("Departamento:");
			lblNewLabel_2.setBounds(628, 216, 88, 14);
			panel.add(lblNewLabel_2);

			comboBoxD = new JComboBox();
			comboBoxD.setModel(new DefaultComboBoxModel(
					new String[] { "<Seleccionar>", "Administracion", "Pediatria", "Cirugia", "Laboratorio" }));
			comboBoxD.setBounds(628, 259, 221, 20);
			panel.add(comboBoxD);

			JButton btnNewButton = new JButton("Filtrar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (checkFields()) {
						loadMedicos();
					}
				}
			});
			btnNewButton.setBounds(628, 308, 104, 54);
			panel.add(btnNewButton);

			rdbtnM = new JRadioButton("Masculino");
			rdbtnM.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnF.setSelected(false);
					rdbtnM.setSelected(true);
				}
			});
			rdbtnM.setBounds(628, 164, 88, 23);
			panel.add(rdbtnM);

			rdbtnF = new JRadioButton("Femenino");
			rdbtnF.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnF.setSelected(true);
					rdbtnM.setSelected(false);
				}
			});
			rdbtnF.setBounds(737, 164, 88, 23);
			panel.add(rdbtnF);

			JButton btnNewButton_1 = new JButton("Todos");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadAllMedico();
				}
			});
			btnNewButton_1.setBounds(742, 308, 104, 54);
			panel.add(btnNewButton_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				seleccionarBtn = new JButton("Seleccionar");
				seleccionarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selected != null) {
							JOptionPane.showMessageDialog(null, "El medico ha sido seleccionada correctamente!");
							RegistrarCita.getMedico(selected);
							dispose();
						}
					}
				});
				seleccionarBtn.setEnabled(false);
				seleccionarBtn.setActionCommand("OK");
				buttonPane.add(seleccionarBtn);
			}
			{
				JButton cancelarBtn = new JButton("Cancelar");
				cancelarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
		loadAllMedico();
	}

	private boolean checkFields() {
		if (comboBoxD.getSelectedIndex() == 0 || comboBoxE.getSelectedIndex() == 0
				|| (!rdbtnM.isSelected() && !rdbtnF.isSelected())) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar todos los Filtros!");
			return false;
		}
		return true;

	}

	public void loadMedicos() {
		medicoModel.setRowCount(0);
		misMedicos.clear();
		row = new Object[tableMedicos.getColumnCount()];

		if (rdbtnM.isSelected()) {
			for (Medico medico : Clinica.getInstance().getMisMedicos()) {
				if (medico.getEspecialidad().equals(comboBoxE.getSelectedItem())
						&& medico.getDepartamento().equals(comboBoxE.getSelectedItem())
						&& medico.getGenero().equalsIgnoreCase("Masculino")) {
					misMedicos.add(medico);
					row[0] = medico.getExequatur();
					row[1] = medico.getNombre();
					row[2] = medico.getApellido();
					row[3] = medico.getNumeroconsultorio();
					medicoModel.addRow(row);
				}
			}
		}
		if (rdbtnF.isSelected()) {
			for (Medico medico : Clinica.getInstance().getMisMedicos()) {
				if (medico.getEspecialidad().equals(comboBoxE.getSelectedItem())
						&& medico.getDepartamento().equals(comboBoxE.getSelectedItem())
						&& medico.getGenero().equalsIgnoreCase("Femenino")) {
					misMedicos.add(medico);
					row[0] = medico.getExequatur();
					row[1] = medico.getNombre();
					row[2] = medico.getApellido();
					row[3] = medico.getNumeroconsultorio();
					medicoModel.addRow(row);
				}
			}
		}
	}

	public void loadAllMedico() {
		medicoModel.setRowCount(0);
		misMedicos.clear();
		row = new Object[tableMedicos.getColumnCount()];
		for (Medico medico : Clinica.getInstance().getMisMedicos()) {
			misMedicos.add(medico);
			row[0] = medico.getExequatur();
			row[1] = medico.getNombre();
			row[2] = medico.getApellido();
			row[3] = medico.getNumeroconsultorio();
			medicoModel.addRow(row);

		}
	}
}
