package visual;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Consulta;
import logico.Enfermedad;
import logico.Paciente;
import logico.Vacuna;

import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HistorialMedicoPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableHistorial;
	private JTable tablePadecimientos;
	private JTable tableAlergias;
	private static JTable tableVacunas;
	private static DefaultTableModel VacunaModel = new DefaultTableModel();
	private DefaultTableModel EnfermedadModel = new DefaultTableModel();
	private DefaultTableModel AlergiaModel = new DefaultTableModel();
	private DefaultTableModel ConsultaModel = new DefaultTableModel();
	private static Object[] row;
	private String[] headersV = { "Codigo", "Enfermedad", "Laboratorio" };
	private String[] headersE = { "Enfermedad" };
	private String[] headersA = { "Alergia" };
	private String[] headersC = { "Codigo", "Fecha", "Medico", "Enfermedad" };
	private static Paciente miPaciente = null;
	private JButton btnAbrirConsulta;
	private Consulta selectedConsulta = null;
	private ArrayList<Consulta> misConsultas = new ArrayList<Consulta>();

	public HistorialMedicoPaciente(Paciente paciente) {
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/icons8-medical-history-40.png"));
		this.setIconImage(icon.getImage());
		setTitle("Historial Medico");
		miPaciente = paciente;
		setResizable(false);
		setBounds(100, 100, 1361, 718);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panelPadecimientos = new JPanel();
		panelPadecimientos.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Padecimientos:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPadecimientos.setBounds(10, 11, 619, 321);
		contentPanel.add(panelPadecimientos);
		panelPadecimientos.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(10, 24, 599, 286);
		panelPadecimientos.add(scrollPane_1);

		tablePadecimientos = new JTable();
		EnfermedadModel.setColumnIdentifiers(headersE);
		tablePadecimientos.setModel(EnfermedadModel);
		scrollPane_1.setViewportView(tablePadecimientos);

		JPanel panelAlergias = new JPanel();
		panelAlergias.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Alergias:",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAlergias.setBounds(10, 343, 619, 321);
		contentPanel.add(panelAlergias);
		panelAlergias.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setBounds(10, 23, 599, 287);
		panelAlergias.add(scrollPane_2);

		tableAlergias = new JTable();
		AlergiaModel.setColumnIdentifiers(headersA);
		tableAlergias.setModel(AlergiaModel);
		scrollPane_2.setViewportView(tableAlergias);

		JPanel panelHistorial = new JPanel();
		panelHistorial.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Historial de Consultas:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelHistorial.setBounds(639, 11, 706, 321);
		contentPanel.add(panelHistorial);
		panelHistorial.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 21, 686, 255);
		panelHistorial.add(scrollPane);

		tableHistorial = new JTable();
		tableHistorial.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableHistorial.getSelectedRow();
				if (index >= 0) {
					btnAbrirConsulta.setEnabled(true);
					selectedConsulta = misConsultas.get(index);
				} else {
					btnAbrirConsulta.setEnabled(false);
					selectedConsulta = null;
				}
			}
		});
		ConsultaModel.setColumnIdentifiers(headersC);
		tableHistorial.setModel(ConsultaModel);
		scrollPane.setViewportView(tableHistorial);

		btnAbrirConsulta = new JButton("Abrir");
		btnAbrirConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowConsulta showCon = new ShowConsulta(selectedConsulta);
				showCon.setModal(true);
				showCon.setLocationRelativeTo(null);
				showCon.setVisible(true);
			}
		});
		btnAbrirConsulta.setEnabled(false);
		btnAbrirConsulta.setBounds(607, 287, 89, 23);
		panelHistorial.add(btnAbrirConsulta);

		JPanel PanelVacunas = new JPanel();
		PanelVacunas.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Vacunas:",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PanelVacunas.setBounds(639, 343, 706, 321);
		contentPanel.add(PanelVacunas);
		PanelVacunas.setLayout(null);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_3.setBounds(10, 23, 686, 249);
		PanelVacunas.add(scrollPane_3);

		tableVacunas = new JTable();
		VacunaModel.setColumnIdentifiers(headersV);
		tableVacunas.setModel(VacunaModel);
		scrollPane_3.setViewportView(tableVacunas);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarVacuna agregarVac = new AgregarVacuna(miPaciente.getMiHistorial());
				agregarVac.setModal(true);
				agregarVac.setLocationRelativeTo(null);
				agregarVac.setVisible(true);

			}
		});
		btnAgregar.setBounds(607, 283, 89, 23);
		PanelVacunas.add(btnAgregar);

		Thread actualizacionThread = new Thread(() -> {
			while (true) {
				SwingUtilities.invokeLater(this::reloadData);
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		actualizacionThread.start();

	}

	public void loadEnfermedades() {
		if (miPaciente != null) {
			EnfermedadModel.setRowCount(0);
			row = new Object[tablePadecimientos.getColumnCount()];
			for (Enfermedad aux : miPaciente.getMiHistorial().getMisPadecimientos()) {
				row[0] = aux.getNombre();
				EnfermedadModel.addRow(row);
			}
		}
	}

	public void loadAlergias() {
		if (miPaciente != null) {
			AlergiaModel.setRowCount(0);
			row = new Object[tableAlergias.getColumnCount()];
			for (String aux : miPaciente.getMiHistorial().getMisAlergias()) {
				row[0] = aux;
				AlergiaModel.addRow(row);
			}
		}
	}

	public void loadConsultas() {
		if (miPaciente != null) {
			ConsultaModel.setRowCount(0);
			row = new Object[tableHistorial.getColumnCount()];
			for (Consulta aux : miPaciente.getMiHistorial().getMisConsultas()) {
				misConsultas.add(aux);
				row[0] = aux.getCodigo();
				row[1] = aux.getCita().getFecha().toString();
				row[2] = aux.getCita().getMedico().getNombre();
				row[3] = aux.getEnfermedad().getNombre();
				ConsultaModel.addRow(row);
			}
		}
	}

	public static void loadVacunas() {
		if (miPaciente != null) {
			VacunaModel.setRowCount(0);
			row = new Object[tableVacunas.getColumnCount()];
			for (Vacuna aux : miPaciente.getMiHistorial().getMisVancunas()) {
				row[0] = aux.getCodigo();
				row[1] = aux.getEnfermedad();
				row[2] = aux.getLaboratorio();
				VacunaModel.addRow(row);
			}
		}
	}

	public void reloadData() {
		loadEnfermedades();
		loadAlergias();
		loadConsultas();
		loadVacunas();
	}
}
