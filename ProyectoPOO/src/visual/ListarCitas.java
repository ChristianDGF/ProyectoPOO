package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cita;
import logico.Clinica;
import logico.Medico;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class ListarCitas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static DefaultTableModel CitasModel = new DefaultTableModel();
	private Cita selected = null;
	private static Object[] row;
	private static Medico miMedico = null;
	private static ArrayList<Cita> misCitas = new ArrayList<Cita>();
	private static ArrayList<Cita> misCitasShowed = new ArrayList<Cita>();
	private JButton realizarBtn;
	private JDateChooser dateChooser_1;
	private JDateChooser dateChooser;
	private JComboBox comboBox;

	public ListarCitas(Medico medico) {
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/icons8-calendar-40.png"));
		this.setIconImage(icon.getImage());
		miMedico = medico;
		setTitle("Citas");
		setBounds(100, 100, 929, 482);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 689, 376);
		contentPanel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				if (index >= 0) {
					selected = misCitasShowed.get(index);
					realizarBtn.setEnabled(true);
				} else {
					selected = null;
					realizarBtn.setEnabled(false);
				}
			}
		});
		String headers[] = { "Codigo", "Doctor", "Persona", "Cedula", "Fecha", "Estado" };
		CitasModel.setColumnIdentifiers(headers);
		table.setModel(CitasModel);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("Fecha Incial:");
		lblNewLabel.setBounds(709, 11, 73, 14);
		contentPanel.add(lblNewLabel);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(709, 36, 194, 20);
		contentPanel.add(dateChooser);

		JLabel lblNewLabel_1 = new JLabel("Fecha Final:");
		lblNewLabel_1.setBounds(709, 67, 73, 14);
		contentPanel.add(lblNewLabel_1);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(709, 92, 194, 20);
		contentPanel.add(dateChooser_1);

		JButton btnNewButton = new JButton("Filtrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterCitas();
			}
		});
		btnNewButton.setBounds(709, 330, 194, 23);
		contentPanel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Mostrar Todo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshCitas();
			}
		});
		btnNewButton_1.setBounds(709, 364, 194, 23);
		contentPanel.add(btnNewButton_1);

		JLabel lblNewLabel_2 = new JLabel("Estado:");
		lblNewLabel_2.setBounds(709, 123, 46, 14);
		contentPanel.add(lblNewLabel_2);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "<Seleccionar>", "Pendiente", "Realizada" }));
		comboBox.setBounds(709, 148, 194, 20);
		contentPanel.add(comboBox);

		JButton btnMisCitas = new JButton("mis Citas");
		btnMisCitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				misCitasHoy();
			}
		});
		btnMisCitas.setBounds(709, 296, 194, 23);
		contentPanel.add(btnMisCitas);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				realizarBtn = new JButton("Realizar");
				realizarBtn.setEnabled(false);
				realizarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selected.getEstado().equalsIgnoreCase("Pendiente")) {
							RegistrarConsulta regConsulta = new RegistrarConsulta(selected.getPersona(),
									selected.getMedico(), selected);
							regConsulta.setModal(true);
							regConsulta.setLocationRelativeTo(null);
							regConsulta.setVisible(true);
						}
					}
				});
				realizarBtn.setActionCommand("OK");
				buttonPane.add(realizarBtn);
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

		if (miMedico == null) {
			realizarBtn.setVisible(false);
		}
		loadCitas();
	}

	public void misCitasHoy() {
		CitasModel.setRowCount(0);
		row = new Object[table.getColumnCount()];
		misCitasShowed.clear();

		for (Cita cita : misCitas) {
			if (cita.getMedico().equals(miMedico) && cita.getEstado().equals("Pendiente")
					&& LocalDate.now().equals(cita.getFecha())) {
				misCitasShowed.add(cita);
				row[0] = cita.getCodigo();
				row[1] = cita.getMedico().getNombre() + " " + cita.getMedico().getApellido();
				row[2] = cita.getPersona().getNombre();
				row[3] = cita.getPersona().getCedula();
				row[4] = cita.getFecha();
				row[5] = cita.getEstado();
				CitasModel.addRow(row);
			}
		}

	}

	public void filterCitas() {
		if (dateChooser.getDate() != null && dateChooser_1.getDate() != null && comboBox.getSelectedIndex() != 0) {
			CitasModel.setRowCount(0);
			row = new Object[table.getColumnCount()];
			misCitasShowed.clear();
			for (Cita cita : misCitas) {
				if (dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
						.isBefore(cita.getFecha())
						&& dateChooser_1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
								.isAfter(cita.getFecha())
						&& cita.getEstado().equals(comboBox.getSelectedItem())) {
					misCitasShowed.add(cita);
					row[0] = cita.getCodigo();
					row[1] = cita.getMedico().getNombre() + " " + cita.getMedico().getApellido();
					row[2] = cita.getPersona().getNombre();
					row[3] = cita.getPersona().getCedula();
					row[4] = cita.getFecha();
					row[5] = cita.getEstado();
					CitasModel.addRow(row);
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Debe seleccionar todos los campos!");
		}
	}

	public void refreshCitas() {
		CitasModel.setRowCount(0);
		row = new Object[table.getColumnCount()];

		misCitasShowed.clear();
		for (Cita cita : misCitas) {
			misCitasShowed.add(cita);
			row[0] = cita.getCodigo();
			row[1] = cita.getMedico().getNombre() + " " + cita.getMedico().getApellido();
			row[2] = cita.getPersona().getNombre();
			row[3] = cita.getPersona().getCedula();
			row[4] = cita.getFecha();
			row[5] = cita.getEstado();
			CitasModel.addRow(row);
		}
	}

	public static void loadCitas() {
		CitasModel.setRowCount(0);
		row = new Object[table.getColumnCount()];

		if (miMedico != null) {
			for (Cita cita : Clinica.getInstance().getMisCitas()) {
				if (cita.getMedico().equals(miMedico) && cita.getEstado().equals("Pendiente")) {
					misCitasShowed.add(cita);
					misCitas.add(cita);
					row[0] = cita.getCodigo();
					row[1] = cita.getMedico().getNombre() + " " + cita.getMedico().getApellido();
					row[2] = cita.getPersona().getNombre();
					row[3] = cita.getPersona().getCedula();
					row[4] = cita.getFecha();
					row[5] = cita.getEstado();
					CitasModel.addRow(row);
				}
			}

		} else {

			for (Cita cita : Clinica.getInstance().getMisCitas()) {
				misCitasShowed.add(cita);
				misCitas.add(cita);
				row[0] = cita.getCodigo();
				row[1] = cita.getMedico().getNombre() + " " + cita.getMedico().getApellido();
				row[2] = cita.getPersona().getNombre();
				row[3] = cita.getPersona().getCedula();
				row[4] = cita.getFecha();
				row[5] = cita.getEstado();
				CitasModel.addRow(row);
			}
		}
	}
}
