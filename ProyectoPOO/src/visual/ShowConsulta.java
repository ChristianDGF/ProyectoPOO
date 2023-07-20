package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import logico.Consulta;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEnfermedad;
	private Consulta miConsulta = null;
	private JTextArea txtSintomas;
	private JTextArea txtDiagnostico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ShowConsulta dialog = new ShowConsulta(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ShowConsulta(Consulta consulta) {
		miConsulta = consulta;
		setTitle("Consulta");
		setBounds(100, 100, 619, 543);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 583, 479);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sintomas:");
		lblNewLabel.setBounds(10, 11, 64, 14);
		panel.add(lblNewLabel);
		
		txtSintomas = new JTextArea();
		txtSintomas.setEnabled(false);
		txtSintomas.setBounds(10, 36, 563, 153);
		panel.add(txtSintomas);
		
		JLabel lblNewLabel_1 = new JLabel("Diagnostico:");
		lblNewLabel_1.setBounds(10, 210, 76, 14);
		panel.add(lblNewLabel_1);
		
		txtDiagnostico = new JTextArea();
		txtDiagnostico.setEnabled(false);
		txtDiagnostico.setBounds(10, 237, 563, 153);
		panel.add(txtDiagnostico);
		
		JLabel lblNewLabel_2 = new JLabel("Enfermedad");
		lblNewLabel_2.setBounds(10, 408, 76, 14);
		panel.add(lblNewLabel_2);
		
		txtEnfermedad = new JTextField();
		txtEnfermedad.setEnabled(false);
		txtEnfermedad.setBounds(10, 433, 563, 20);
		panel.add(txtEnfermedad);
		txtEnfermedad.setColumns(10);
	}
	
	public void loadConsulta()
	{
		if(miConsulta != null)
		{
			txtSintomas.setText(miConsulta.getSintomas());
			txtDiagnostico.setText(miConsulta.getDiagnostico());
			txtEnfermedad.setText(miConsulta.getEnfermedad().getNombre());
		}
	}
}
