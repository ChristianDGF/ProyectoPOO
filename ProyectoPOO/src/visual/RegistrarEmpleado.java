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
import java.awt.Color;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.awt.event.ItemEvent;

public class RegistrarEmpleado extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtnombre;
	private JTextField txtapellido;
	private JComboBox cmbcargo;
	private JComboBox cmbsexo;
	private JTextField txtconsultorio;
	private JPanel DoctorPanel;
	private JFormattedTextField txtcedula;
	private JFormattedTextField txtfechanacimiento;
	private JFormattedTextField txttelefono;
	private JFormattedTextField txtcorreo;
	private JTextArea txtdireccion;
	private JComboBox cmbespecialidad;
	private JFormattedTextField txtexequatur;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");



	public RegistrarEmpleado() {
		setTitle("Registrar Empleado");
		setResizable(false);
		setBounds(100, 100, 699, 557);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.background"));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Informacion General:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 661, 320);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblcedula = new JLabel("Cedula:");
		lblcedula.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblcedula.setBounds(26, 34, 53, 14);
		panel.add(lblcedula);
		
		txtcedula = new JFormattedTextField();
		txtcedula.setBounds(97, 29, 185, 20);
		panel.add(txtcedula);
		
		JLabel lblfechanacim = new JLabel("Fecha Nacimiento:");
		lblfechanacim.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblfechanacim.setBounds(312, 34, 110, 14);
		panel.add(lblfechanacim);
		
		txtfechanacimiento = new JFormattedTextField();
		txtfechanacimiento.setBounds(442, 29, 185, 20);
		panel.add(txtfechanacimiento);
		
		JLabel lblnombre = new JLabel("Nombre:");
		lblnombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblnombre.setBounds(26, 82, 53, 14);
		panel.add(lblnombre);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(97, 78, 185, 20);
		panel.add(txtnombre);
		txtnombre.setColumns(10);
		
		JLabel lblapellido = new JLabel("Apellido:");
		lblapellido.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblapellido.setBounds(312, 82, 65, 14);
		panel.add(lblapellido);
		
		txtapellido = new JTextField();
		txtapellido.setBounds(442, 78, 185, 20);
		panel.add(txtapellido);
		txtapellido.setColumns(10);
		
		JLabel lbltelefono = new JLabel("Telefono:");
		lbltelefono.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbltelefono.setBounds(26, 130, 53, 14);
		panel.add(lbltelefono);
		
		txttelefono = new JFormattedTextField();
		txttelefono.setBounds(97, 127, 185, 20);
		panel.add(txttelefono);
		
		JLabel lblcargo = new JLabel("Cargo:");
		lblcargo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblcargo.setBounds(312, 130, 53, 14);
		panel.add(lblcargo);
		
		cmbcargo = new JComboBox();
		cmbcargo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getItem().toString() == "Doctor")
				{
					DoctorPanel.setVisible(true);
					DoctorPanel.setEnabled(true);
				}else {
					DoctorPanel.setVisible(false);
					DoctorPanel.setEnabled(false);
				}
			}
		});
		cmbcargo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccionar>", "Doctor", "Secretaria", "Vedel"}));
		cmbcargo.setBounds(442, 127, 185, 20);
		panel.add(cmbcargo);
		
		JLabel lblcorreo = new JLabel("Correo Electronico:");
		lblcorreo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblcorreo.setBounds(312, 178, 110, 14);
		panel.add(lblcorreo);
		
		txtcorreo = new JFormattedTextField();
		txtcorreo.setBounds(442, 176, 185, 20);
		panel.add(txtcorreo);
		
		JLabel lblsexo = new JLabel("Sexo:");
		lblsexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblsexo.setBounds(26, 178, 46, 14);
		panel.add(lblsexo);
		
		cmbsexo = new JComboBox();
		cmbsexo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccionar>", "Masculino", "Femenino"}));
		cmbsexo.setBounds(97, 176, 185, 20);
		panel.add(cmbsexo);
		
		JLabel lbldireccion = new JLabel("Direcci\u00F3n:");
		lbldireccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbldireccion.setBounds(21, 223, 58, 14);
		panel.add(lbldireccion);
		
		txtdireccion = new JTextArea();
		txtdireccion.setBounds(97, 223, 530, 70);
		panel.add(txtdireccion);
		
		DoctorPanel = new JPanel();
		DoctorPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		DoctorPanel.setBounds(10, 342, 661, 119);
		contentPanel.add(DoctorPanel);
		DoctorPanel.setLayout(null);
		DoctorPanel.setVisible(false);
		DoctorPanel.setEnabled(false);
		
		JLabel lblespecialidad = new JLabel("Especialidad:");
		lblespecialidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblespecialidad.setBounds(27, 22, 76, 14);
		DoctorPanel.add(lblespecialidad);
		
		cmbespecialidad = new JComboBox();
		cmbespecialidad.setModel(new DefaultComboBoxModel(new String[] {"<Seleccionar>", "Cardiolog\u00EDa", "Neurolog\u00EDa", "Nefrolog\u00EDa", "Pediatr\u00EDa", "Reumatolog\u00EDa", "Cirug\u00EDa pedi\u00E1trica", "Cirug\u00EDa ortop\u00E9dica y traumatolog\u00EDa", "Cirug\u00EDa tor\u00E1cica", "Neurocirug\u00EDa", "An\u00E1lisis cl\u00EDnicos", "Anatom\u00EDa patol\u00F3gica", "Bioqu\u00EDmica cl\u00EDnica", "Farmacolog\u00EDa cl\u00EDnica", "Inmunolog\u00EDa", "Medicina nuclear", "Microbiolog\u00EDa y parasitolog\u00EDa", "Neurofisiolog\u00EDa cl\u00EDnica", "Radiodiagn\u00F3stico"}));
		cmbespecialidad.setBounds(130, 20, 189, 20);
		DoctorPanel.add(cmbespecialidad);
		
		JLabel lblexequatur = new JLabel("Exequatur:");
		lblexequatur.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblexequatur.setBounds(346, 22, 76, 14);
		DoctorPanel.add(lblexequatur);
		
		txtexequatur = new JFormattedTextField();
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
						registrarEmpleado();
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
	}
	private void registrarEmpleado()
	{
	    String cedula = txtcedula.getText();
	    String fechaNacimiento = dateFormat.format(txtfechanacimiento.getValue());
	    String nombre = txtnombre.getText();
	    String apellido = txtapellido.getText();
	    String telefono = txttelefono.getText();
	    String cargo = cmbcargo.getSelectedItem().toString();
	    String correoElectronico = txtcorreo.getText();
	    String sexo = cmbsexo.getSelectedItem().toString();
	    String direccion = txtdireccion.getText();
	    String departamento = "General";
	    
	    if (cargo.equals("Doctor")) 
	    {
	        String especialidad = cmbespecialidad.getSelectedItem().toString();
	        String exequatur = txtexequatur.getText();
	        String numeroConsultorio = txtconsultorio.getText();
	        
	        Medico medico = new Medico(nombre, apellido, direccion, fechaNacimiento, sexo, cedula, telefono,
	            correoElectronico, cargo, departamento, especialidad, exequatur, numeroConsultorio);
	        
	        Clinica.getInstance().AgregarMedico(medico);
	        
	    } else {
	        Empleado empleado = new Empleado(nombre, apellido, direccion, fechaNacimiento, sexo, cedula, telefono,
	            correoElectronico, cargo, departamento);
	        
	        Clinica.getInstance().AgregarEmpleado(empleado);
	    }
	    JOptionPane.showMessageDialog(null, "Operaci�n satisfactoria", "Registro",
				JOptionPane.INFORMATION_MESSAGE);
	    clean();
	}
	private void clean() {
	    txtcedula.setText("");
	    txtfechanacimiento.setValue(null);
	    txtnombre.setText("");
	    txtapellido.setText("");
	    txttelefono.setText("");
	    cmbcargo.setSelectedIndex(0);
	    txtcorreo.setText("");
	    cmbsexo.setSelectedIndex(0);
	    txtdireccion.setText("");
	    cmbespecialidad.setSelectedIndex(0);
	    txtexequatur.setText("");
	    txtconsultorio.setText("");
	}


}
