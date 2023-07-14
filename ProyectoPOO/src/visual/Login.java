package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		setBounds(100, 100, 450, 205);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblusuario = new JLabel("Usuario:");
				lblusuario.setBounds(50, 31, 56, 16);
				panel.add(lblusuario);
			}
			{
				textField = new JTextField();
				textField.setBounds(128, 28, 233, 22);
				panel.add(textField);
				textField.setColumns(10);
			}
			{
				JLabel lblpassword = new JLabel("Contrase\u00F1a:");
				lblpassword.setBounds(50, 68, 89, 16);
				panel.add(lblpassword);
			}
			{
				textField_1 = new JTextField();
				textField_1.setColumns(10);
				textField_1.setBounds(128, 63, 233, 22);
				panel.add(textField_1);
			}
			{
				JButton btnacceder = new JButton("Iniciar");
				btnacceder.setBounds(163, 98, 97, 25);
				panel.add(btnacceder);
			}
		}
	}

}
