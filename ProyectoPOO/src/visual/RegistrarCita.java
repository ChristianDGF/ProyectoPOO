package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import logico.Cita;
import logico.Clinica;
import logico.Medico;
import logico.Paciente;
import logico.Persona;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.Format;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.raven.swing.TimePicker;

public class RegistrarCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtApellido;
	private static JTextField txtNconsultorio;
	private static JTextField txtEspecialidad;
	private static JTextField txtNombreMedico;
	private static JTextField txtApellidoMedico;
	private static Medico miMedico = null;
	private Paciente miPaciente = null;
	private JButton btnBuscarPaciente;
	private JFormattedTextField txtTelefono;
	private JComboBox comboBoxGenero;
	private JFormattedTextField txtCedula;
	private JDateChooser dateChooser;
	private JTextField txtHora;
	private static JTextField txtExequatur;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

	public RegistrarCita() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/icons8-calendar-40.png"));
		this.setIconImage(icon.getImage());
		setTitle("Agendar Cita");
		setBounds(100, 100, 918, 525);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);

			JPanel panelcita = new JPanel();
			panelcita.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
					"Informacion Cita:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelcita.setBounds(594, 11, 290, 417);
			panel.add(panelcita);
			panelcita.setLayout(null);

			JLabel lblFecha = new JLabel("Fecha:");
			lblFecha.setBounds(22, 28, 56, 16);
			panelcita.add(lblFecha);

			dateChooser = new JDateChooser();
			dateChooser.setDateFormatString("yyyy-MM-dd");
			JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
			editor.setEditable(false);
			dateChooser.setBounds(78, 26, 192, 20);
			panelcita.add(dateChooser);

			TimePicker timePicker = new TimePicker();
			timePicker.set24hourMode(false);
			timePicker.setForeground(Color.GRAY);
			timePicker.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			timePicker.setBounds(22, 95, 248, 309);
			panelcita.add(timePicker);

			JLabel lblNewLabel = new JLabel("Hora:");
			lblNewLabel.setBounds(22, 55, 46, 14);
			panelcita.add(lblNewLabel);

			txtHora = new JTextField();
			txtHora.setEditable(false);
			timePicker.setDisplayText(txtHora);
			txtHora.setBounds(78, 52, 192, 20);
			panelcita.add(txtHora);
			txtHora.setColumns(10);

			Format shortTime = DateFormat.getTimeInstance(DateFormat.SHORT);

			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Paciente",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(12, 11, 574, 185);
			panel.add(panel_1);
			panel_1.setLayout(null);

			JPanel panelpaciente = new JPanel();
			panelpaciente.setBounds(10, 53, 551, 117);
			panel_1.add(panelpaciente);
			panelpaciente.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Datos:",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelpaciente.setLayout(null);

			JLabel lblnombre = new JLabel("Nombre:");
			lblnombre.setBounds(21, 36, 56, 16);
			panelpaciente.add(lblnombre);

			txtNombre = new JTextField();
			txtNombre.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!Character.isAlphabetic(c) && c != ' ') {
						e.consume();
					}
				}
			});
			txtNombre.setEditable(false);
			txtNombre.setColumns(10);
			txtNombre.setBounds(89, 33, 178, 22);
			panelpaciente.add(txtNombre);

			JLabel lblapellido = new JLabel("Apellido:");
			lblapellido.setBounds(290, 36, 56, 16);
			panelpaciente.add(lblapellido);

			txtApellido = new JTextField();
			txtApellido.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!Character.isAlphabetic(c) && c != ' ') {
						e.consume();
					}
				}
			});
			txtApellido.setEditable(false);
			txtApellido.setColumns(10);
			txtApellido.setBounds(346, 33, 178, 22);
			panelpaciente.add(txtApellido);

			JLabel lbltelefono = new JLabel("Telefono:");
			lbltelefono.setBounds(21, 69, 56, 16);
			panelpaciente.add(lbltelefono);

			JLabel lblgenero = new JLabel("Genero:");
			lblgenero.setBounds(290, 69, 56, 16);
			panelpaciente.add(lblgenero);

			comboBoxGenero = new JComboBox();
			comboBoxGenero.setEnabled(false);
			comboBoxGenero
					.setModel(new DefaultComboBoxModel(new String[] { "<Seleccionar>", "Masculino", "Femenino" }));
			comboBoxGenero.setBounds(346, 66, 178, 22);
			panelpaciente.add(comboBoxGenero);

			txtTelefono = new JFormattedTextField(createFormatter("###-###-####"));
			txtTelefono.setEditable(false);
			txtTelefono.setBounds(89, 67, 178, 20);
			panelpaciente.add(txtTelefono);

			JLabel lblcedula = new JLabel("Cedula:");
			lblcedula.setBounds(80, 23, 56, 16);
			panel_1.add(lblcedula);

			btnBuscarPaciente = new JButton("Buscar");
			btnBuscarPaciente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					miPaciente = Clinica.getInstance().BuscarPacienteByCedula(txtCedula.getText());
					if (miPaciente != null) {
						JOptionPane.showMessageDialog(null, "El Paciente fue encontrado!");
						clearPacienteInfo();
						disableInputs();
						txtNombre.setText(miPaciente.getNombre());
						txtApellido.setText(miPaciente.getApellido());
						txtTelefono.setText(miPaciente.getTelefono());
						comboBoxGenero.setSelectedItem(miPaciente.getGenero());
					} else {
						JOptionPane.showMessageDialog(null, "El Paciente no fue encontrado!");
						clearPacienteInfo();
						enableInputs();
					}
				}
			});
			btnBuscarPaciente.setBounds(369, 19, 160, 25);
			panel_1.add(btnBuscarPaciente);

			txtCedula = new JFormattedTextField(createFormatter("###-#######-#"));
			txtCedula.setBounds(146, 21, 178, 20);
			panel_1.add(txtCedula);

			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Medico",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_2.setBounds(12, 207, 572, 221);
			panel.add(panel_2);
			panel_2.setLayout(null);

			JButton btnNewButton = new JButton("Buscar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SearchMedico sMedico = new SearchMedico();
					sMedico.setModal(true);
					sMedico.setLocationRelativeTo(null);
					sMedico.setVisible(true);
				}
			});
			btnNewButton.setBounds(212, 18, 160, 23);
			panel_2.add(btnNewButton);

			JPanel panel_3 = new JPanel();
			panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Datos:",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_3.setBounds(10, 52, 552, 158);
			panel_2.add(panel_3);
			panel_3.setLayout(null);

			JLabel lblNewLabel_1 = new JLabel("N. Consultorio:");
			lblNewLabel_1.setBounds(10, 29, 92, 14);
			panel_3.add(lblNewLabel_1);

			txtNconsultorio = new JTextField();
			txtNconsultorio.setEditable(false);
			txtNconsultorio.setBounds(115, 26, 158, 20);
			panel_3.add(txtNconsultorio);
			txtNconsultorio.setColumns(10);

			JLabel lblNewLabel_2 = new JLabel("Especialidad:");
			lblNewLabel_2.setBounds(286, 29, 78, 14);
			panel_3.add(lblNewLabel_2);

			txtEspecialidad = new JTextField();
			txtEspecialidad.setEditable(false);
			txtEspecialidad.setBounds(377, 26, 158, 20);
			panel_3.add(txtEspecialidad);
			txtEspecialidad.setColumns(10);

			JLabel lblNewLabel_3 = new JLabel("Nombre:");
			lblNewLabel_3.setBounds(10, 72, 61, 14);
			panel_3.add(lblNewLabel_3);

			txtNombreMedico = new JTextField();
			txtNombreMedico.setEditable(false);
			txtNombreMedico.setBounds(115, 69, 158, 20);
			panel_3.add(txtNombreMedico);
			txtNombreMedico.setColumns(10);

			txtApellidoMedico = new JTextField();
			txtApellidoMedico.setEditable(false);
			txtApellidoMedico.setBounds(377, 69, 158, 20);
			panel_3.add(txtApellidoMedico);
			txtApellidoMedico.setColumns(10);

			JLabel lblNewLabel_4 = new JLabel("Apellido:");
			lblNewLabel_4.setBounds(286, 72, 61, 14);
			panel_3.add(lblNewLabel_4);

			JLabel lblNewLabel_5 = new JLabel("Exequatur:");
			lblNewLabel_5.setBounds(10, 115, 61, 14);
			panel_3.add(lblNewLabel_5);

			txtExequatur = new JTextField();
			txtExequatur.setEditable(false);
			txtExequatur.setBounds(115, 112, 158, 20);
			panel_3.add(txtExequatur);
			txtExequatur.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnregistrar = new JButton("Registrar");
				btnregistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (checkFields()) {
							if (validateDateHour()) {
								if (Clinica.getInstance().checkCedula(txtCedula.getText())) {
									if (JOptionPane.showConfirmDialog(null, "Agendar CITA-N." + Cita.codigoCita) == 0) {
										if (miPaciente != null) {
											Cita cita = new Cita(
													dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault())
															.toLocalDate(),
													miPaciente, miMedico,
													LocalTime.parse(txtHora.getText(), formatter));
											Clinica.getInstance().AgregarCita(cita);
											JOptionPane.showMessageDialog(null, "Cita registrada correctamente",
													"Registro", JOptionPane.INFORMATION_MESSAGE);
											clearPacienteInfo();
											clearMedicoInfo();
										} else {
											registrar();
										}
									}
								} else {
									JOptionPane.showMessageDialog(null, "La cedula ya esta registrada!");
								}
							}
						}
					}
				});
				btnregistrar.setActionCommand("OK");
				buttonPane.add(btnregistrar);
				getRootPane().setDefaultButton(btnregistrar);
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
	}

	protected void registrar() {
		Persona newPersona = new Persona(txtNombre.getText(), txtApellido.getText(), "", "",
				comboBoxGenero.getSelectedItem().toString(), txtCedula.getText(), txtTelefono.getText(), "");
		Cita cita = new Cita(dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), newPersona,
				miMedico, LocalTime.parse(txtHora.getText(), formatter));
		Clinica.getInstance().AgregarCita(cita);
		JOptionPane.showMessageDialog(null, "Cita registrada correctamente", "Registro",
				JOptionPane.INFORMATION_MESSAGE);
		clearPacienteInfo();
		clearMedicoInfo();
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

	public boolean checkFields() {

		if (miPaciente != null && miMedico != null && dateChooser.getDate() != null) {

			if (chekHourAndDate()) {
				return true;
			} else {
				return false;
			}
		}

		if (miPaciente == null && miMedico != null && dateChooser.getDate() != null && !txtNombre.getText().equals("")
				&& !txtTelefono.getText().equals("") && !txtApellido.getText().equals("")
				&& comboBoxGenero.getSelectedIndex() != 0 && !txtHora.getText().equals("")) {
			if (chekHourAndDate()) {
				return true;
			} else {
				return false;
			}
		}

		JOptionPane.showMessageDialog(null, "Debe llenar todos los campos!", "Error", JOptionPane.INFORMATION_MESSAGE);
		return false;

	}

	public boolean chekHourAndDate() {
		
		 if (LocalDate.now().equals(dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) && LocalTime.now().isAfter(LocalTime.parse(txtHora.getText(), formatter))) {
		    JOptionPane.showMessageDialog(null, "Hora invalida!", "Error", JOptionPane.INFORMATION_MESSAGE); 
		    return false; 
		 }
		 
		if (LocalDate.now().isAfter(dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
			JOptionPane.showMessageDialog(null, "Fecha invalida!", "Error", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		return true;
	}

	public boolean validateDateHour() {
		for (Cita aux : Clinica.getInstance().getMisCitas()) {
			if (aux.getMedico().equals(miMedico) && aux.getFecha()
					.equals(dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
				if (aux.getHora().equals(LocalTime.parse(txtHora.getText(), formatter))) {
					JOptionPane.showMessageDialog(null, "Hay una Cita agendada a la hora seleccionada!", "Error",
							JOptionPane.INFORMATION_MESSAGE);
					return false;
				}

				Duration duration = Duration.between(aux.getHora(), LocalTime.parse(txtHora.getText(), formatter));
				long minutesDifference = duration.toMinutes();

				if (minutesDifference < 20) {
					JOptionPane.showMessageDialog(null, "Hay una Cita agendada a la hora seleccionada!", "Error",
							JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
			}
		}

		return true;
	}

	public void enableInputs() {
		txtNombre.setEditable(true);
		txtTelefono.setEditable(true);
		txtApellido.setEditable(true);
		comboBoxGenero.setEnabled(true);
	}

	public void disableInputs() {
		txtNombre.setEditable(false);
		txtTelefono.setEditable(false);
		txtApellido.setEditable(false);
		comboBoxGenero.setEnabled(false);
	}

	public void clearPacienteInfo() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtTelefono.setText("");
		comboBoxGenero.setSelectedIndex(0);
	}

	public void clearMedicoInfo() {
		txtNconsultorio.setText("");
		txtEspecialidad.setText("");
		txtNombreMedico.setText("");
		txtApellidoMedico.setText("");
	}

	public static void getMedico(Medico medico) {
		miMedico = medico;
		txtNconsultorio.setText(miMedico.getNumeroconsultorio());
		txtEspecialidad.setText(miMedico.getEspecialidad());
		txtNombreMedico.setText(miMedico.getNombre());
		txtApellidoMedico.setText(miMedico.getApellido());
		txtExequatur.setText(miMedico.getExequatur());
	}
}
