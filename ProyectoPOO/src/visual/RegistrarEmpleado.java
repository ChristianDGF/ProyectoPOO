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
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class RegistrarEmpleado extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBoxCargo;
	private JComboBox comboBoxSexo;
	private JTextField textField_2;
	private JPanel DoctorPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarEmpleado dialog = new RegistrarEmpleado();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
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
		
		JLabel lblNewLabel = new JLabel("Cedula:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(26, 34, 53, 14);
		panel.add(lblNewLabel);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(97, 29, 185, 20);
		panel.add(formattedTextField);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha Nacimiento:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(312, 34, 110, 14);
		panel.add(lblNewLabel_1);
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setBounds(442, 29, 185, 20);
		panel.add(formattedTextField_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(26, 82, 53, 14);
		panel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(97, 78, 185, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Apellido:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(312, 82, 65, 14);
		panel.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(442, 78, 185, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Telefono:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(26, 130, 53, 14);
		panel.add(lblNewLabel_4);
		
		JFormattedTextField formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setBounds(97, 127, 185, 20);
		panel.add(formattedTextField_2);
		
		JLabel lblNewLabel_5 = new JLabel("Cargo:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(312, 130, 53, 14);
		panel.add(lblNewLabel_5);
		
		comboBoxCargo = new JComboBox();
		comboBoxCargo.addItemListener(new ItemListener() {
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
		comboBoxCargo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccionar>", "Doctor", "Secretaria", "Vedel"}));
		comboBoxCargo.setBounds(442, 127, 185, 20);
		panel.add(comboBoxCargo);
		
		JLabel lblNewLabel_6 = new JLabel("Correo Electronico:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(312, 178, 110, 14);
		panel.add(lblNewLabel_6);
		
		JFormattedTextField formattedTextField_3 = new JFormattedTextField();
		formattedTextField_3.setBounds(442, 176, 185, 20);
		panel.add(formattedTextField_3);
		
		JLabel lblNewLabel_7 = new JLabel("Sexo:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(26, 178, 46, 14);
		panel.add(lblNewLabel_7);
		
		comboBoxSexo = new JComboBox();
		comboBoxSexo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccionar>", "Masculino", "Femenino"}));
		comboBoxSexo.setBounds(97, 176, 185, 20);
		panel.add(comboBoxSexo);
		
		JLabel lblNewLabel_8 = new JLabel("Direcci\u00F3n:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(21, 223, 58, 14);
		panel.add(lblNewLabel_8);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(97, 223, 530, 70);
		panel.add(textArea);
		
		DoctorPanel = new JPanel();
		DoctorPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		DoctorPanel.setBounds(10, 342, 661, 119);
		contentPanel.add(DoctorPanel);
		DoctorPanel.setLayout(null);
		DoctorPanel.setVisible(false);
		DoctorPanel.setEnabled(false);
		
		JLabel lblNewLabel_9 = new JLabel("Especialidad:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_9.setBounds(27, 22, 76, 14);
		DoctorPanel.add(lblNewLabel_9);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Seleccionar>", "Cardiolog\u00EDa", "Neurolog\u00EDa", "Nefrolog\u00EDa", "Pediatr\u00EDa", "Reumatolog\u00EDa", "Cirug\u00EDa pedi\u00E1trica", "Cirug\u00EDa ortop\u00E9dica y traumatolog\u00EDa", "Cirug\u00EDa tor\u00E1cica", "Neurocirug\u00EDa", "An\u00E1lisis cl\u00EDnicos", "Anatom\u00EDa patol\u00F3gica", "Bioqu\u00EDmica cl\u00EDnica", "Farmacolog\u00EDa cl\u00EDnica", "Inmunolog\u00EDa", "Medicina nuclear", "Microbiolog\u00EDa y parasitolog\u00EDa", "Neurofisiolog\u00EDa cl\u00EDnica", "Radiodiagn\u00F3stico"}));
		comboBox.setBounds(130, 20, 189, 20);
		DoctorPanel.add(comboBox);
		
		JLabel lblNewLabel_10 = new JLabel("Exequatur:");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_10.setBounds(346, 22, 76, 14);
		DoctorPanel.add(lblNewLabel_10);
		
		JFormattedTextField formattedTextField_4 = new JFormattedTextField();
		formattedTextField_4.setBounds(449, 20, 185, 20);
		DoctorPanel.add(formattedTextField_4);
		
		JLabel lblNewLabel_11 = new JLabel("Numero de Consultorio:");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_11.setBounds(27, 68, 138, 14);
		DoctorPanel.add(lblNewLabel_11);
		
		textField_2 = new JTextField();
		textField_2.setBounds(171, 65, 185, 20);
		DoctorPanel.add(textField_2);
		textField_2.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
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
}
