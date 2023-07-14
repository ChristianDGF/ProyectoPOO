package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

public class RegistrarCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtcedula;
	private JTextField txtnombre;
	private JTextField txtapellido;
	private JTextField textField;
	private JTextField txtcodigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarCita dialog = new RegistrarCita();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarCita() {
		setTitle("Registrar CIta");
		setBounds(100, 100, 638, 374);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblcedula = new JLabel("Cedula:");
			lblcedula.setBounds(24, 17, 56, 16);
			panel.add(lblcedula);
			
			txtcedula = new JTextField();
			txtcedula.setBounds(92, 14, 178, 22);
			panel.add(txtcedula);
			txtcedula.setColumns(10);
			
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.setBounds(282, 13, 97, 25);
			panel.add(btnBuscar);
			
			JPanel panelpaciente = new JPanel();
			panelpaciente.setBorder(new TitledBorder(null, "Informacion Personal", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelpaciente.setBounds(12, 54, 586, 96);
			panel.add(panelpaciente);
			panelpaciente.setLayout(null);
			
			JLabel lblnombre = new JLabel("Nombre:");
			lblnombre.setBounds(12, 26, 56, 16);
			panelpaciente.add(lblnombre);
			
			txtnombre = new JTextField();
			txtnombre.setColumns(10);
			txtnombre.setBounds(80, 23, 178, 22);
			panelpaciente.add(txtnombre);
			
			JLabel lblapellido = new JLabel("Apellido:");
			lblapellido.setBounds(302, 26, 56, 16);
			panelpaciente.add(lblapellido);
			
			txtapellido = new JTextField();
			txtapellido.setColumns(10);
			txtapellido.setBounds(370, 23, 178, 22);
			panelpaciente.add(txtapellido);
			
			JLabel lbltelefono = new JLabel("Telefono:");
			lbltelefono.setBounds(12, 63, 56, 16);
			panelpaciente.add(lbltelefono);
			
			textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(80, 60, 178, 22);
			panelpaciente.add(textField);
			
			JLabel lblgenero = new JLabel("Genero:");
			lblgenero.setBounds(302, 63, 56, 16);
			panelpaciente.add(lblgenero);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Seleccionar>", "Masculino", "Femenino"}));
			comboBox.setBounds(370, 60, 178, 22);
			panelpaciente.add(comboBox);
			
			JPanel panelcita = new JPanel();
			panelcita.setBorder(new TitledBorder(null, "Informacion Cita", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelcita.setBounds(12, 163, 586, 96);
			panel.add(panelcita);
			panelcita.setLayout(null);
			
			JLabel lblCodigo = new JLabel("Codigo:");
			lblCodigo.setBounds(12, 29, 56, 16);
			panelcita.add(lblCodigo);
			
			txtcodigo = new JTextField();
			txtcodigo.setColumns(10);
			txtcodigo.setBounds(80, 26, 178, 22);
			panelcita.add(txtcodigo);
			
			JLabel lblMedico = new JLabel("Medico:");
			lblMedico.setBounds(12, 64, 56, 16);
			panelcita.add(lblMedico);
			
			JComboBox comboBox_1 = new JComboBox();
			comboBox_1.setBounds(80, 61, 178, 22);
			panelcita.add(comboBox_1);
			
			JLabel lblFecha = new JLabel("Fecha:");
			lblFecha.setBounds(302, 29, 56, 16);
			panelcita.add(lblFecha);
			
			JSpinner spinner = new JSpinner();
			spinner.setModel(new SpinnerDateModel(new Date(1689231600000L), null, null, Calendar.DAY_OF_YEAR));
			spinner.setBounds(370, 26, 178, 22);
			panelcita.add(spinner);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnregistrar = new JButton("Registrar");
				btnregistrar.setActionCommand("OK");
				buttonPane.add(btnregistrar);
				getRootPane().setDefaultButton(btnregistrar);
			}
			{
				JButton btncancelar = new JButton("Cancelar");
				btncancelar.setActionCommand("Cancel");
				buttonPane.add(btncancelar);
			}
		}
	}
}
