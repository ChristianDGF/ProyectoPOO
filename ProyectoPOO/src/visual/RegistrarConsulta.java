package visual;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cita;
import logico.Clinica;
import logico.Consulta;
import logico.Enfermedad;
import logico.Medico;
import logico.Paciente;
import logico.Persona;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class RegistrarConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTextField txtNombre;
	private static JTextField txtApellido;
	private static JTextField txtCedula;
	private static JTextField txtTelefono;
	private static JTextField txtSexo;
	private static JTextField txtTipoDeSangre;
	private static JTextField txtPeso;
	private static JTextField txtAltura;
	private static JTextField txtEdad;
	private JTable TableHistorialConsultas;
	private static JTextField txtEnfermedadNombre;
	private JTextField txtCodigoCita;
	private JTextField txtFechaCita;
	private Medico miMedico = null;
	private Cita miCita = null;
	private DefaultTableModel misConsultasModel;
	private Object[] row;
	private static Paciente miPaciente = null;
	private JButton btnAbrirConsulta;
	private ArrayList<Consulta> misConsultas = new ArrayList<Consulta>();
	private static Enfermedad miEnfermedad = null;
	private Consulta selected = null;
	private static JTextField txtEnfermedadTipo;
	private static JTextField txtEnfermedadEstado;
	private static JButton btnEliminar;
	private JTextArea txtSintomas;
	private JTextArea txtDiagnosticos;
	private JRadioButton rdbtnRHMP;

	public RegistrarConsulta(Persona persona, Medico doctor, Cita cita) {
		if (persona != null) {
			checkPaciente(cita, persona);
		}

		miMedico = doctor;
		miCita = cita;
		setTitle("Consulta");
		setResizable(false);
		setBounds(100, 100, 1097, 727);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Informacion general:",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 482, 307);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(10, 32, 54, 14);
		panel.add(lblNewLabel);

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(66, 29, 406, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Apellido:");
		lblNewLabel_1.setBounds(10, 60, 54, 14);
		panel.add(lblNewLabel_1);

		txtApellido = new JTextField();
		txtApellido.setEditable(false);
		txtApellido.setBounds(66, 57, 406, 20);
		panel.add(txtApellido);
		txtApellido.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Cedula:");
		lblNewLabel_2.setBounds(10, 101, 46, 14);
		panel.add(lblNewLabel_2);

		txtCedula = new JTextField();
		txtCedula.setEditable(false);
		txtCedula.setBounds(66, 98, 162, 20);
		panel.add(txtCedula);
		txtCedula.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Telefono:");
		lblNewLabel_3.setBounds(246, 101, 54, 14);
		panel.add(lblNewLabel_3);

		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setBounds(310, 98, 162, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Sexo:");
		lblNewLabel_4.setBounds(10, 132, 46, 14);
		panel.add(lblNewLabel_4);

		txtSexo = new JTextField();
		txtSexo.setEditable(false);
		txtSexo.setBounds(66, 129, 162, 20);
		panel.add(txtSexo);
		txtSexo.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Tipo de Sangre:");
		lblNewLabel_5.setBounds(246, 132, 95, 14);
		panel.add(lblNewLabel_5);

		txtTipoDeSangre = new JTextField();
		txtTipoDeSangre.setEditable(false);
		txtTipoDeSangre.setBounds(341, 129, 131, 20);
		panel.add(txtTipoDeSangre);
		txtTipoDeSangre.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("(lb) Peso:");
		lblNewLabel_6.setBounds(10, 164, 54, 14);
		panel.add(lblNewLabel_6);

		txtPeso = new JTextField();
		txtPeso.setEditable(false);
		txtPeso.setBounds(76, 160, 152, 20);
		panel.add(txtPeso);
		txtPeso.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("(Pie) Altura:");
		lblNewLabel_7.setBounds(246, 164, 83, 14);
		panel.add(lblNewLabel_7);

		txtAltura = new JTextField();
		txtAltura.setEditable(false);
		txtAltura.setBounds(320, 161, 152, 20);
		panel.add(txtAltura);
		txtAltura.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Edad:");
		lblNewLabel_8.setBounds(10, 194, 46, 14);
		panel.add(lblNewLabel_8);

		txtEdad = new JTextField();
		txtEdad.setEditable(false);
		txtEdad.setBounds(66, 191, 162, 20);
		panel.add(txtEdad);
		txtEdad.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_4.setBounds(10, 230, 462, 66);
		panel.add(panel_4);
		panel_4.setLayout(null);

		JButton btnPadecimientos = new JButton("Actualizar");
		btnPadecimientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarPaciente regPac = new RegistrarPaciente(miPaciente,false);
				regPac.setModal(true);
				regPac.setLocationRelativeTo(null);
				regPac.setVisible(true);
			}
		});
		btnPadecimientos.setBounds(90, 21, 101, 23);
		panel_4.add(btnPadecimientos);

		JButton btnNewButton = new JButton("Historial");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HistorialMedicoPaciente historial = new HistorialMedicoPaciente(miPaciente);
				historial.setModal(true);
				historial.setLocationRelativeTo(null);
				historial.setVisible(true);
			}
		});
		btnNewButton.setBounds(281, 21, 89, 23);
		panel_4.add(btnNewButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Historial de Consultas:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 329, 482, 348);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane ScrollPaneHistorialConsultas = new JScrollPane();
		ScrollPaneHistorialConsultas.setBounds(10, 22, 462, 284);
		panel_1.add(ScrollPaneHistorialConsultas);

		TableHistorialConsultas = new JTable();
		TableHistorialConsultas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = TableHistorialConsultas.getSelectedRow();
				if (index >= 0) {
					btnAbrirConsulta.setEnabled(true);
					selected = misConsultas.get(index);
				} else {
					btnAbrirConsulta.setEnabled(false);
					selected = null;
				}
			}
		});
		misConsultasModel = new DefaultTableModel();
		String headers[] = { "Codigo", "Fecha", "Enfermedad", "Estado" };
		misConsultasModel.setColumnIdentifiers(headers);
		TableHistorialConsultas.setModel(misConsultasModel);
		ScrollPaneHistorialConsultas.setViewportView(TableHistorialConsultas);

		btnAbrirConsulta = new JButton("Abrir");
		btnAbrirConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowConsulta showCon = new ShowConsulta(selected);
				showCon.setModal(true);
				showCon.setLocationRelativeTo(null);
				showCon.setVisible(true);
			}
		});
		btnAbrirConsulta.setEnabled(false);
		btnAbrirConsulta.setBounds(383, 314, 89, 23);
		panel_1.add(btnAbrirConsulta);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING,
						TitledBorder.TOP, null, new Color(0, 0, 0)),
				"Informacion de Consulta", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(502, 122, 561, 553);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);

		txtSintomas = new JTextArea();
		txtSintomas.setBounds(10, 48, 541, 118);
		panel_2.add(txtSintomas);

		JLabel lblNewLabel_9 = new JLabel("Sintomas:");
		lblNewLabel_9.setBounds(10, 23, 67, 14);
		panel_2.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Diagnostico:");
		lblNewLabel_10.setBounds(10, 177, 84, 14);
		panel_2.add(lblNewLabel_10);

		txtDiagnosticos = new JTextArea();
		txtDiagnosticos.setBounds(10, 202, 541, 118);
		panel_2.add(txtDiagnosticos);

		JLabel lblNewLabel_11 = new JLabel("Enfermedad:");
		lblNewLabel_11.setBounds(12, 345, 84, 14);
		panel_2.add(lblNewLabel_11);

		txtEnfermedadNombre = new JTextField();
		txtEnfermedadNombre.setEditable(false);
		txtEnfermedadNombre.setBounds(12, 370, 370, 20);
		panel_2.add(txtEnfermedadNombre);
		txtEnfermedadNombre.setColumns(10);

		JButton txtBuscarEnfermedad = new JButton("Seleccionar");
		txtBuscarEnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchEnfermedad searchEnf = new SearchEnfermedad();
				searchEnf.setModal(true);
				searchEnf.setLocationRelativeTo(null);
				searchEnf.setVisible(true);
			}
		});
		txtBuscarEnfermedad.setBounds(404, 370, 121, 23);
		panel_2.add(txtBuscarEnfermedad);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.setBounds(10, 471, 541, 71);
		panel_2.add(panel_5);
		panel_5.setLayout(null);

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(426, 13, 89, 47);
		panel_5.add(btnCerrar);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkFields()) {
					regConsulta();
				}
			}
		});
		btnRegistrar.setBounds(317, 13, 89, 47);
		panel_5.add(btnRegistrar);

		rdbtnRHMP = new JRadioButton("Registrar en el historial medico del paciente");
		rdbtnRHMP.setBounds(17, 25, 294, 23);
		panel_5.add(rdbtnRHMP);

		JLabel lblNewLabel_14 = new JLabel("Tipo:");
		lblNewLabel_14.setBounds(12, 401, 46, 14);
		panel_2.add(lblNewLabel_14);

		txtEnfermedadTipo = new JTextField();
		txtEnfermedadTipo.setEditable(false);
		txtEnfermedadTipo.setBounds(10, 426, 176, 20);
		panel_2.add(txtEnfermedadTipo);
		txtEnfermedadTipo.setColumns(10);

		JLabel lblNewLabel_15 = new JLabel("Estado:");
		lblNewLabel_15.setBounds(215, 402, 46, 14);
		panel_2.add(lblNewLabel_15);

		txtEnfermedadEstado = new JTextField();
		txtEnfermedadEstado.setEditable(false);
		txtEnfermedadEstado.setBounds(215, 426, 167, 20);
		panel_2.add(txtEnfermedadEstado);
		txtEnfermedadEstado.setColumns(10);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miEnfermedad = null;
				btnEliminar.setEnabled(false);
				JOptionPane.showMessageDialog(null, "La enfermedad ha sido deseleccionada!");
			}
		});
		btnEliminar.setBounds(404, 425, 121, 23);
		panel_2.add(btnEliminar);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING,
						TitledBorder.TOP, null, new Color(0, 0, 0)),
				"Cita", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(502, 11, 561, 100);
		contentPanel.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_12 = new JLabel("Codigo:");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_12.setBounds(23, 43, 54, 14);
		panel_3.add(lblNewLabel_12);

		txtCodigoCita = new JTextField();
		txtCodigoCita.setEditable(false);
		txtCodigoCita.setBounds(108, 40, 161, 20);
		panel_3.add(txtCodigoCita);
		txtCodigoCita.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("Fecha:");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_13.setBounds(296, 43, 46, 14);
		panel_3.add(lblNewLabel_13);

		txtFechaCita = new JTextField();
		txtFechaCita.setEditable(false);
		txtFechaCita.setBounds(369, 40, 161, 20);
		panel_3.add(txtFechaCita);
		txtFechaCita.setColumns(10);

		loadPaciente();
		loadPacienteCosultas();
		loadCitaInfo();
	}

	public void regConsulta() {
		Consulta consulta = new Consulta("CONSUTA-" + Consulta.codigoConsulta, miCita, txtDiagnosticos.getText(),
				txtSintomas.getText(), miEnfermedad, "Realizada");

		if ((miEnfermedad != null && miEnfermedad.getEstado().equalsIgnoreCase("Vigilancia"))
				|| rdbtnRHMP.isSelected()) {
			miPaciente.getMiHistorial().getMisConsultas().add(consulta);
		}

		Clinica.getInstance().AgregarConsulta(consulta);
		miMedico.getMisconsultas().add(consulta);
		miCita.setEstado("Realizada");
		if(miEnfermedad != null)
		{
			miPaciente.getMiHistorial().getMisPadecimientos().add(miEnfermedad);
		}else {
			clearPacienteEnfermedades();
		}
		JOptionPane.showMessageDialog(null, "La consulta ha sido registrada!");
		dispose();

	}
	
	public void clearPacienteEnfermedades()
	{
		ArrayList<Enfermedad> enfermedad = new ArrayList<Enfermedad>();
		for(Enfermedad aux: miPaciente.getMiHistorial().getMisPadecimientos())
		{
			if(aux.getTipo() != "Crónicas")
			{
				enfermedad.add(aux);
			}
		}
		for(Enfermedad aux: enfermedad)
		{
			miPaciente.getMiHistorial().getMisPadecimientos().remove(aux);
		}
	}

	public static void loadPaciente() {
		if (miPaciente != null) {
			txtNombre.setText(miPaciente.getNombre());
			txtApellido.setText(miPaciente.getApellido());
			txtCedula.setText(miPaciente.getCedula());
			txtTelefono.setText(miPaciente.getTelefono());
			txtSexo.setText(miPaciente.getGenero());
			txtTipoDeSangre.setText(miPaciente.getTipoSangre());
			txtAltura.setText(String.valueOf(miPaciente.getAltura()));
			txtPeso.setText(String.valueOf(miPaciente.getPeso()));
			txtEdad.setText(String.valueOf(miPaciente.getEdad()));
		}
	}

	public void loadPacienteCosultas() {

		if (miMedico != null && miMedico.getMisconsultas() != null) {
			consultaMedicoPaciente();
			misConsultasModel.setRowCount(0);
			row = new Object[TableHistorialConsultas.getColumnCount()];
			for (Consulta aux : misConsultas) {
				row[0] = aux.getCodigo();
				row[1] = aux.getCita().getFecha().toString();
				row[2] = aux.getEnfermedad().getNombre();
				row[3] = aux.getEstado();
				misConsultasModel.addRow(row);
			}
		}
	}

	public void loadCitaInfo() {
		if (miCita != null) {
			txtCodigoCita.setText(miCita.getCodigo());
			txtFechaCita.setText(miCita.getFecha().toString());
		}
	}

	public void consultaMedicoPaciente() {
		if (miMedico != null && miMedico.getMisconsultas() != null) {
			for (Consulta aux : miMedico.getMisconsultas()) {
				if (aux.getCita().getPersona().equals(miPaciente)) {
					misConsultas.add(aux);
				}
			}
		}
	}

	public static void getEnfermedad(Enfermedad enfermedad) {
		miEnfermedad = enfermedad;
		txtEnfermedadNombre.setText(miEnfermedad.getNombre());
		txtEnfermedadTipo.setText(miEnfermedad.getTipo());
		txtEnfermedadEstado.setText(miEnfermedad.getEstado());
		btnEliminar.setEnabled(true);
	}

	public boolean checkFields() {
		String data = txtDiagnosticos.getText().trim();
		String data2 = txtSintomas.getText().trim();

		if (data2.equals("") || data.equals("")) {
			JOptionPane.showMessageDialog(null, "Debe rellenar todos los datos!");
			return false;
		}

		return true;
	}

	public void checkPaciente(Cita cita, Persona persona) {
		if (persona instanceof Paciente) {
			miPaciente = (Paciente) persona;
		} else {
			Paciente newPaciente = new Paciente(persona.getNombre(), persona.getApellido(), persona.getDireccion(),
					persona.getFechaNacimiento(), persona.getGenero(), persona.getCedula(), persona.getTelefono(),
					persona.getCorreoelectronico(), "Sano", 0, 0, "", 0);
			Clinica.getInstance().AgregarPaciente(newPaciente);
			cita.setPersona(newPaciente);
			miPaciente = newPaciente;
		}
	}
}
