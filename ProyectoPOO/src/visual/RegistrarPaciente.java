package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import logico.Clinica;
import logico.HistorialMedico;
import logico.Paciente;

import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JFormattedTextField;

public class RegistrarPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCedula;
	private JTextField txtPeso;
	private JTextField txtAltura;
	private JTextField txtEdad;
	private JTextField txtEmail;
	private JTextField txtEstado;
	private JTextField txtAlergia;
	private JTable tableAlergias;
	private Paciente miPaciente = null;
	private JTextArea txtDireccion;
	private JComboBox comboBoxSexo;
	private JComboBox comboBoxSangre;
	private JDateChooser dateChooser;
	private DefaultTableModel AlergiaModel;
	private Object[] row;
	private String fecha;
	private JButton btnBorrar;
	private JButton btnAgregarAlergia;
	private String alergiaString;
	private JFormattedTextField txtTelefono;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarPaciente dialog = new RegistrarPaciente(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarPaciente(Paciente paciente) {
		ArrayList<String> alergias = new ArrayList<String>();
		alergias.add("COCO");
		alergias.add("asdklasjdokasd");
		alergias.add("afasfasfasf");
		HistorialMedico historial = new HistorialMedico("41242", alergias, null, null);
		miPaciente = new Paciente("PEPE", "ALCANTARA", "en su casa", "2004-08-13", "Femenino", "1309423231", "809-901-0977", "MMG@gmail.com", "Enfermo", historial, 13, 10, "O-", 13);
		setTitle("Actualizar Paciente");
		setBounds(100, 100, 1071, 465);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Informacion General:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 510, 371);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(10, 27, 56, 14);
		panel.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
			    if(!Character.isAlphabetic(c) && c != ' ')
				{
					e.consume();
				}
			}
		});
		txtNombre.setBounds(76, 24, 417, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Apellido:");
		lblNewLabel_1.setBounds(10, 58, 56, 14);
		panel.add(lblNewLabel_1);
		
		txtApellido = new JTextField();
		txtApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
			    if(!Character.isAlphabetic(c) && c != ' ')
				{
					e.consume();
				}
			}
		});
		txtApellido.setBounds(76, 55, 417, 20);
		panel.add(txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Cedula:");
		lblNewLabel_2.setBounds(10, 95, 46, 14);
		panel.add(lblNewLabel_2);
		
		txtCedula = new JTextField();
		txtCedula.setEditable(false);
		txtCedula.setBounds(77, 92, 171, 20);
		panel.add(txtCedula);
		txtCedula.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Telefono:");
		lblNewLabel_3.setBounds(258, 95, 56, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Sexo:");
		lblNewLabel_4.setBounds(10, 126, 46, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Tipo Sangre:");
		lblNewLabel_5.setBounds(258, 126, 79, 14);
		panel.add(lblNewLabel_5);
		
		comboBoxSangre = new JComboBox();
		comboBoxSangre.setModel(new DefaultComboBoxModel(new String[] {"A+", "B+", "O+", "AB+", "A-", "B-", "O-", "AB-"}));
		comboBoxSangre.setBounds(344, 123, 149, 20);
		panel.add(comboBoxSangre);
		
		JLabel lblNewLabel_6 = new JLabel("(lb) Peso:");
		lblNewLabel_6.setBounds(10, 157, 61, 14);
		panel.add(lblNewLabel_6);
		
		txtPeso = new JTextField();
		txtPeso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
			    if(!Character.isDigit(c))
				{
					if(txtPeso.getText().contains("."))
					{
						e.consume();	
					}else if(c != '.')
					{
						e.consume();
					}
				}
			    if(txtPeso.getText().length() >= 10)
			    {
			    	e.consume();
			    } 
			}
		});
		txtPeso.setBounds(76, 154, 172, 20);
		panel.add(txtPeso);
		txtPeso.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("(Pie) Altura:");
		lblNewLabel_7.setBounds(258, 157, 68, 14);
		panel.add(lblNewLabel_7);
		
		txtAltura = new JTextField();
		txtAltura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
			    if(!Character.isDigit(c))
				{
					if(txtAltura.getText().contains("."))
					{
						e.consume();	
					}else if(c != '.')
					{
						e.consume();
					}
				}
			    if(txtAltura.getText().length() >= 10)
			    {
			    	e.consume();
			    } 
			}
		});
		txtAltura.setBounds(331, 154, 162, 20);
		panel.add(txtAltura);
		txtAltura.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Fecha de Nacimiento:");
		lblNewLabel_9.setBounds(10, 188, 125, 14);
		panel.add(lblNewLabel_9);
		
		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				LocalDate nac = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate current = LocalDate.now();
				txtEdad.setText(String.valueOf(Period.between(nac, current).getYears()));
			}
		});
		JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
		editor.setEditable(false);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(152, 185, 162, 20);
		panel.add(dateChooser);
		
		JLabel lblNewLabel_8 = new JLabel("Edad:");
		lblNewLabel_8.setBounds(324, 188, 39, 14);
		panel.add(lblNewLabel_8);
		
		txtEdad = new JTextField();
		txtEdad.setEditable(false);
		txtEdad.setBounds(368, 185, 125, 20);
		panel.add(txtEdad);
		txtEdad.setColumns(10);
		
		comboBoxSexo = new JComboBox();
		comboBoxSexo.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		comboBoxSexo.setBounds(77, 123, 171, 20);
		panel.add(comboBoxSexo);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(77, 216, 171, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Email:");
		lblNewLabel_10.setBounds(10, 219, 46, 14);
		panel.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Estado:");
		lblNewLabel_11.setBounds(273, 219, 46, 14);
		panel.add(lblNewLabel_11);
		
		txtEstado = new JTextField();
		txtEstado.setEditable(false);
		txtEstado.setBounds(334, 216, 159, 20);
		panel.add(txtEstado);
		txtEstado.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Direccion:");
		lblNewLabel_12.setBounds(10, 253, 79, 14);
		panel.add(lblNewLabel_12);
		
		txtDireccion = new JTextArea();
		txtDireccion.setBounds(77, 253, 416, 100);
		panel.add(txtDireccion);
		
		txtTelefono = new JFormattedTextField(createFormatter("###-###-####"));
		txtTelefono.setBounds(331, 92, 162, 20);
		panel.add(txtTelefono);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Alergias:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(533, 11, 496, 371);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		txtAlergia = new JTextField();
		txtAlergia.setBounds(20, 25, 276, 20);
		panel_1.add(txtAlergia);
		txtAlergia.setColumns(10);
		
		btnAgregarAlergia = new JButton("Agregar");
		btnAgregarAlergia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miPaciente.getMiHistorial().getMisAlergias().add(txtAlergia.getText());
				loadAlergias();
				txtAlergia.setText("");
			}
		});
		btnAgregarAlergia.setBounds(306, 24, 81, 23);
		panel_1.add(btnAgregarAlergia);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(20, 55, 458, 305);
		panel_1.add(scrollPane);
		
		tableAlergias = new JTable();
		tableAlergias.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableAlergias.getSelectedRow();
				if(index >= 0)
				{
					btnBorrar.setEnabled(true);
					alergiaString = tableAlergias.getValueAt(index, 0).toString();
				}
			}
		});
		AlergiaModel = new DefaultTableModel();
		String header[] = {"Alergias"};
		AlergiaModel.setColumnIdentifiers(header);
		tableAlergias.setModel(AlergiaModel);
		scrollPane.setViewportView(tableAlergias);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miPaciente.getMiHistorial().getMisAlergias().remove(alergiaString);
				btnBorrar.setEnabled(false);
				loadAlergias();
			}
		});
		btnBorrar.setEnabled(false);
		btnBorrar.setBounds(397, 24, 81, 23);
		panel_1.add(btnBorrar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnActualizar = new JButton("Actualizar");
				btnActualizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						LocalDate nac = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						LocalDate current = LocalDate.now();
						
						miPaciente.setNombre(txtNombre.getText());
						miPaciente.setApellido(txtApellido.getText());
						miPaciente.setCedula(txtCedula.getText());
						miPaciente.setTelefono(txtTelefono.getText());
						miPaciente.setAltura(Float.parseFloat(txtAltura.getText()));
						miPaciente.setPeso(Float.parseFloat(txtPeso.getText()));
						miPaciente.setCorreoelectronico(txtEmail.getText());
						miPaciente.setEstado(txtEstado.getText());
						miPaciente.setDireccion(txtDireccion.getText());
						miPaciente.setGenero(comboBoxSexo.getSelectedItem().toString());
						miPaciente.setTipoSangre(comboBoxSangre.getSelectedItem().toString());
						miPaciente.setFechaNacimiento(dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());
						miPaciente.setEdad(Period.between(nac, current).getYears());
						
						Clinica.getInstance().ActualizarPaciente(miPaciente);
						JOptionPane.showMessageDialog(null, "El paciente ha sido actualizado correctamente!");  
						dispose();
						
					}
				});
				btnActualizar.setActionCommand("OK");
				buttonPane.add(btnActualizar);
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
		loadPaciente();
	}
	
	public void loadPaciente()
	{
		if(miPaciente != null)
		{
			txtNombre.setText(miPaciente.getNombre());
			txtApellido.setText(miPaciente.getApellido());
			txtCedula.setText(miPaciente.getCedula());
			txtTelefono.setText(miPaciente.getTelefono());
			txtAltura.setText(String.valueOf(miPaciente.getAltura()));
			txtPeso.setText(String.valueOf(miPaciente.getPeso()));
			txtEmail.setText(miPaciente.getCorreoelectronico());
			txtEstado.setText(miPaciente.getEstado());
			txtDireccion.setText(miPaciente.getDireccion());
			comboBoxSexo.setSelectedItem(miPaciente.getGenero());
			comboBoxSangre.setSelectedItem(miPaciente.getTipoSangre());
			Date fecha = null;
			try {
				fecha = new SimpleDateFormat("yyyy-MM-dd").parse(miPaciente.getFechaNacimiento());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			dateChooser.setDate(fecha);
			txtEdad.setText(String.valueOf(miPaciente.getEdad()));
			
			loadAlergias();
			
		}
	}
	
	public void loadAlergias()
	{
		AlergiaModel.setRowCount(0);
		row = new Object[tableAlergias.getColumnCount()];
		
		for(String alergia: miPaciente.getMiHistorial().getMisAlergias())
		{
			row[0] = alergia;
			AlergiaModel.addRow(row);
		}
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
