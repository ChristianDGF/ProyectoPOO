package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import logico.Clinica;
import logico.Empleado;
import logico.Medico;

import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.awt.event.ItemEvent;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegistrarEmpleado extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtnombre;
	private JTextField txtapellido;
	private JComboBox cmbcargo;
	private JComboBox cmbsexo;
	private JTextField txtconsultorio;
	private JPanel DoctorPanel;
	private JFormattedTextField txtcedula;
	private JFormattedTextField txttelefono;
	private JFormattedTextField txtcorreo;
	private JTextArea txtdireccion;
	private JComboBox cmbespecialidad;
	private JFormattedTextField txtexequatur;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
	private JComboBox cmbdepartamento;
	private JDateChooser dateChooser;
	private Empleado empleado = null;

	public RegistrarEmpleado(Empleado miempleado) {
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/icons8-employees-24.png"));
		this.setIconImage(icon.getImage());
		empleado = miempleado;
		if (empleado == null) {
			setTitle("Registrar Empleado");
		} else {
			setTitle("Modificar Empleado");
		}
		setResizable(false);
		setBounds(100, 100, 716, 607);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.background"));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Informacion General:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 673, 367);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblcedula = new JLabel("Cedula:");
		lblcedula.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblcedula.setBounds(26, 34, 53, 14);
		panel.add(lblcedula);

		txtcedula = new JFormattedTextField(createFormatter("###-#######-#"));
		txtcedula.setBounds(118, 31, 185, 20);
		panel.add(txtcedula);

		JLabel lblfechanacim = new JLabel("Fecha Nacimiento:");
		lblfechanacim.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblfechanacim.setBounds(333, 34, 110, 14);
		panel.add(lblfechanacim);

		JLabel lblnombre = new JLabel("Nombre:");
		lblnombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblnombre.setBounds(26, 82, 53, 14);
		panel.add(lblnombre);

		txtnombre = new JTextField();
		txtnombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isAlphabetic(c) && c != ' ') {
					e.consume();
				}
			}
		});
		txtnombre.setBounds(118, 79, 185, 20);
		panel.add(txtnombre);
		txtnombre.setColumns(10);

		JLabel lblapellido = new JLabel("Apellido:");
		lblapellido.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblapellido.setBounds(333, 82, 65, 14);
		panel.add(lblapellido);

		txtapellido = new JTextField();
		txtapellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isAlphabetic(c) && c != ' ') {
					e.consume();
				}
			}
		});
		txtapellido.setBounds(463, 79, 185, 20);
		panel.add(txtapellido);
		txtapellido.setColumns(10);

		JLabel lbltelefono = new JLabel("Telefono:");
		lbltelefono.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbltelefono.setBounds(26, 130, 53, 14);
		panel.add(lbltelefono);

		txttelefono = new JFormattedTextField(createFormatter("###-###-####"));
		txttelefono.setBounds(118, 127, 185, 20);
		panel.add(txttelefono);

		JLabel lblcargo = new JLabel("Cargo:");
		lblcargo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblcargo.setBounds(333, 130, 53, 14);
		panel.add(lblcargo);

		cmbcargo = new JComboBox();
		cmbcargo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getItem().toString() == "Medico") {
					DoctorPanel.setVisible(true);
					DoctorPanel.setEnabled(true);
				} else {
					DoctorPanel.setVisible(false);
					DoctorPanel.setEnabled(false);
				}
			}
		});
		cmbcargo.setModel(new DefaultComboBoxModel(new String[] { "<Seleccionar>", "Medico", "Secretario", "Bedel" }));
		cmbcargo.setBounds(463, 127, 185, 20);
		panel.add(cmbcargo);

		JLabel lblcorreo = new JLabel("Correo Electronico:");
		lblcorreo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblcorreo.setBounds(333, 178, 110, 14);
		panel.add(lblcorreo);

		txtcorreo = new JFormattedTextField();
		txtcorreo.setBounds(463, 175, 185, 20);
		panel.add(txtcorreo);

		JLabel lblsexo = new JLabel("Sexo:");
		lblsexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblsexo.setBounds(26, 178, 46, 14);
		panel.add(lblsexo);

		cmbsexo = new JComboBox();
		cmbsexo.setModel(new DefaultComboBoxModel(new String[] { "<Seleccionar>", "Masculino", "Femenino" }));
		cmbsexo.setBounds(118, 175, 185, 20);
		panel.add(cmbsexo);

		JLabel lbldireccion = new JLabel("Direcci\u00F3n:");
		lbldireccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbldireccion.setBounds(26, 263, 58, 14);
		panel.add(lbldireccion);

		txtdireccion = new JTextArea();
		txtdireccion.setBounds(97, 263, 551, 70);
		panel.add(txtdireccion);

		JLabel lblNewLabel = new JLabel("Departamento:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(26, 221, 95, 14);
		panel.add(lblNewLabel);

		cmbdepartamento = new JComboBox();
		cmbdepartamento.setModel(new DefaultComboBoxModel(
				new String[] { "<Seleccionar>", "Administracion", "Pediatria", "Cirugia", "Laboratorio" }));
		cmbdepartamento.setBounds(118, 218, 185, 20);
		panel.add(cmbdepartamento);

		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
		editor.setEditable(false);
		dateChooser.setBounds(463, 31, 185, 20);
		panel.add(dateChooser);

		DoctorPanel = new JPanel();
		DoctorPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Informacion Medico:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		DoctorPanel.setBounds(10, 389, 673, 119);
		contentPanel.add(DoctorPanel);
		DoctorPanel.setLayout(null);
		DoctorPanel.setVisible(false);
		DoctorPanel.setEnabled(false);

		JLabel lblespecialidad = new JLabel("Especialidad:");
		lblespecialidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblespecialidad.setBounds(27, 22, 76, 14);
		DoctorPanel.add(lblespecialidad);

		cmbespecialidad = new JComboBox();
		cmbespecialidad.setModel(new DefaultComboBoxModel(new String[] { "<Seleccionar>", "Cardiolog\u00EDa",
				"Neurolog\u00EDa", "Nefrolog\u00EDa", "Pediatr\u00EDa", "Reumatolog\u00EDa",
				"Cirug\u00EDa pedi\u00E1trica", "Cirug\u00EDa ortop\u00E9dica y traumatolog\u00EDa",
				"Cirug\u00EDa tor\u00E1cica", "Neurocirug\u00EDa", "An\u00E1lisis cl\u00EDnicos",
				"Anatom\u00EDa patol\u00F3gica", "Bioqu\u00EDmica cl\u00EDnica", "Farmacolog\u00EDa cl\u00EDnica",
				"Inmunolog\u00EDa", "Medicina nuclear", "Microbiolog\u00EDa y parasitolog\u00EDa",
				"Neurofisiolog\u00EDa cl\u00EDnica", "Radiodiagn\u00F3stico" }));
		cmbespecialidad.setBounds(130, 20, 189, 20);
		DoctorPanel.add(cmbespecialidad);

		JLabel lblexequatur = new JLabel("Exequatur:");
		lblexequatur.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblexequatur.setBounds(346, 22, 76, 14);
		DoctorPanel.add(lblexequatur);

		txtexequatur = new JFormattedTextField(createFormatter("###-###"));
		txtexequatur.setBounds(449, 20, 185, 20);
		DoctorPanel.add(txtexequatur);

		JLabel lblnumeroconsultorio = new JLabel("Numero de Consultorio:");
		lblnumeroconsultorio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblnumeroconsultorio.setBounds(27, 68, 138, 14);
		DoctorPanel.add(lblnumeroconsultorio);

		txtconsultorio = new JTextField();
		txtconsultorio.setBounds(171, 65, 185, 20);
		DoctorPanel.add(txtconsultorio);
		txtconsultorio.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (checkfield()) {
							if (empleado == null) {
								if (Clinica.getInstance().checkCedula(txtcedula.getText())) {
									registrarEmpleado();
								} else {
									JOptionPane.showMessageDialog(null, "La Cedula ya esta registrada!", "Registro",
											JOptionPane.INFORMATION_MESSAGE);
								}
							} else {
								modificarEmpleado();
								JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Registro",
										JOptionPane.INFORMATION_MESSAGE);
								dispose();
							}
						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
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
		cargarEmpleado();
	}

	private void modificarEmpleado() {

		if (empleado != null) {
			empleado.setCedula(txtcedula.getText());
			empleado.setNombre(txtnombre.getText());
			empleado.setApellido(txtapellido.getText());
			empleado.setTelefono(txttelefono.getText());
			empleado.setCargo(cmbcargo.getSelectedItem().toString());
			empleado.setCorreoelectronico(txtcorreo.getText());
			empleado.setGenero(cmbsexo.getSelectedItem().toString());
			empleado.setDireccion(txtdireccion.getText());
			empleado.setDepartamento(cmbdepartamento.getSelectedItem().toString());
			if (empleado instanceof Medico) {
				Medico medico = (Medico) empleado;
				medico.setEspecialidad(cmbespecialidad.getSelectedItem().toString());
				medico.setExequatur(txtexequatur.getText());
				medico.setNumeroconsultorio(txtconsultorio.getText());
			}
		}
	}

	public void cargarEmpleado() {
		if (empleado != null) {
			txtcedula.setText(empleado.getCedula());
			if (!empleado.getFechaNacimiento().equals("")) {
				Date fecha = null;
				try {
					fecha = new SimpleDateFormat("yyyy-MM-dd").parse(empleado.getFechaNacimiento());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				dateChooser.setDate(fecha);
			}
			txtnombre.setText(empleado.getNombre());
			txtapellido.setText(empleado.getApellido());
			txttelefono.setText(empleado.getTelefono());
			cmbcargo.setSelectedItem(empleado.getCargo());
			txtcorreo.setText(empleado.getCorreoelectronico());
			cmbsexo.setSelectedItem(empleado.getGenero());
			txtdireccion.setText(empleado.getDireccion());
			cmbdepartamento.setSelectedItem(empleado.getDepartamento());

			if (empleado instanceof Medico) {
				cmbespecialidad.setSelectedItem(((Medico) empleado).getEspecialidad());
				txtexequatur.setText(((Medico) empleado).getExequatur());
				txtconsultorio.setText(((Medico) empleado).getNumeroconsultorio());
			}
		}
	}

	private void registrarEmpleado() {
		String cedula = txtcedula.getText();
		String fechanacimiento = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
				.toString();
		String nombre = txtnombre.getText();
		String apellido = txtapellido.getText();
		String telefono = txttelefono.getText();
		String cargo = cmbcargo.getSelectedItem().toString();
		String correoElectronico = txtcorreo.getText();
		String sexo = cmbsexo.getSelectedItem().toString();
		String direccion = txtdireccion.getText();
		String departamento = cmbdepartamento.getSelectedItem().toString();

		if (cargo.equals("Medico")) {
			String especialidad = cmbespecialidad.getSelectedItem().toString();
			String exequatur = txtexequatur.getText();
			String numeroConsultorio = txtconsultorio.getText();

			Medico medico = new Medico(nombre, apellido, direccion, fechanacimiento, sexo, cedula, telefono,
					correoElectronico, cargo, departamento, especialidad, exequatur, numeroConsultorio);

			Clinica.getInstance().AgregarMedico(medico);
			Clinica.getInstance().AgregarEmpleado(medico);
		} else {
			Empleado empleado = new Empleado(nombre, apellido, direccion, fechanacimiento, sexo, cedula, telefono,
					correoElectronico, cargo, departamento);

			Clinica.getInstance().AgregarEmpleado(empleado);
		}
		JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Registro", JOptionPane.INFORMATION_MESSAGE);
		clean();
	}

	private void clean() {
		txtcedula.setText("");
		dateChooser.setDate(null);
		txtnombre.setText("");
		txtapellido.setText("");
		txttelefono.setText("");
		cmbcargo.setSelectedIndex(0);
		txtcorreo.setText("");
		cmbsexo.setSelectedIndex(0);
		txtdireccion.setText("");
		cmbdepartamento.setSelectedIndex(0);
		cmbespecialidad.setSelectedIndex(0);
		txtexequatur.setText("");
		txtconsultorio.setText("");
	}

	private boolean checkfield() {

		if (txtcedula.getText().isEmpty() || dateChooser.getDate() == null || txtnombre.getText().isEmpty()
				|| txtapellido.getText().isEmpty() || txttelefono.getText().isEmpty()
				|| cmbcargo.getSelectedIndex() == 0 || txtcorreo.getText().isEmpty() || cmbsexo.getSelectedIndex() == 0
				|| txtdireccion.getText().isEmpty() || cmbdepartamento.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Debe completar todos los campos obligatorios", "Error",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if (cmbcargo.getSelectedItem().toString().equals("Medico")) {
			if (cmbespecialidad.getSelectedIndex() == 0 || txtexequatur.getText().isEmpty()
					|| txtconsultorio.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Debe completar todos los campos obligatorios", "Error",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}

		if (LocalDate.from(dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
				.until(LocalDate.now(), ChronoUnit.YEARS) < 18) {
			JOptionPane.showMessageDialog(null, "Debe ingresar una edad valida!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
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

}
