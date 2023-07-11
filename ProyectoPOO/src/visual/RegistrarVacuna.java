package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrarVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtenfermedad;
	private JTextField txtcodigo;
	private JTextField txtlaboratorio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarVacuna dialog = new RegistrarVacuna();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarVacuna() {
		setTitle("Registrar Vacuna");
		setBounds(100, 100, 580, 296);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblcodigo = new JLabel("Codigo:");
			lblcodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblcodigo.setBounds(287, 25, 56, 16);
			panel.add(lblcodigo);
			
			txtenfermedad = new JTextField();
			txtenfermedad.setBounds(99, 23, 174, 22);
			panel.add(txtenfermedad);
			txtenfermedad.setColumns(10);
			
			JLabel lblenfermedad = new JLabel("Enfermedad:");
			lblenfermedad.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblenfermedad.setBounds(12, 25, 94, 16);
			panel.add(lblenfermedad);
			
			txtcodigo = new JTextField();
			txtcodigo.setColumns(10);
			txtcodigo.setBounds(355, 23, 174, 22);
			panel.add(txtcodigo);
			
			JLabel lbllaboratorio = new JLabel("Laboratorio:");
			lbllaboratorio.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbllaboratorio.setBounds(12, 58, 75, 16);
			panel.add(lbllaboratorio);
			
			txtlaboratorio = new JTextField();
			txtlaboratorio.setColumns(10);
			txtlaboratorio.setBounds(99, 58, 174, 22);
			panel.add(txtlaboratorio);
			
			JLabel lbltipo = new JLabel("Tipo:");
			lbltipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbltipo.setBounds(287, 58, 56, 16);
			panel.add(lbltipo);
			
			JComboBox cmbtipo = new JComboBox();
			cmbtipo.setBounds(355, 58, 174, 22);
			panel.add(cmbtipo);
			
			JLabel lbldescripcion = new JLabel("Descripcion:");
			lbldescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbldescripcion.setBounds(12, 94, 88, 16);
			panel.add(lbldescripcion);
			
			JTextPane textdescripcion = new JTextPane();
			textdescripcion.setBounds(99, 100, 430, 54);
			panel.add(textdescripcion);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
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
