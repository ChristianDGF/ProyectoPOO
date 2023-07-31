package visual;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import logico.Consulta;

import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class ShowConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEnfermedad;
	private Consulta miConsulta = null;
	private JTextArea txtSintomas;
	private JTextArea txtDiagnostico;

	public ShowConsulta(Consulta consulta) {
		setResizable(false);
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/icons8-medical-history-40.png"));
		this.setIconImage(icon.getImage());
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

		JLabel lblNewLabel_1 = new JLabel("Diagnostico:");
		lblNewLabel_1.setBounds(10, 210, 76, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Enfermedad");
		lblNewLabel_2.setBounds(10, 408, 76, 14);
		panel.add(lblNewLabel_2);

		txtEnfermedad = new JTextField();
		txtEnfermedad.setEditable(false);
		txtEnfermedad.setBounds(10, 433, 563, 20);
		panel.add(txtEnfermedad);
		txtEnfermedad.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 563, 153);
		panel.add(scrollPane);
		
				txtSintomas = new JTextArea();
				txtSintomas.setEditable(false);
				scrollPane.setViewportView(txtSintomas);
				
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(10, 235, 563, 153);
				panel.add(scrollPane_1);
				
						txtDiagnostico = new JTextArea();
						txtDiagnostico.setEditable(false);
						scrollPane_1.setViewportView(txtDiagnostico);

		loadConsulta();
	}

	public void loadConsulta() {
		if (miConsulta != null) {
			txtSintomas.setText(miConsulta.getSintomas());
			txtDiagnostico.setText(miConsulta.getDiagnostico());
			if (miConsulta.getEnfermedad() != null) {
				txtEnfermedad.setText(miConsulta.getEnfermedad().getNombre());
			}
		}
	}
}
