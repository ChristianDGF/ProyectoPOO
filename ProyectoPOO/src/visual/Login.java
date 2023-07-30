package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Cita;
import logico.Clinica;
import logico.Empleado;
import logico.Enfermedad;
import logico.HistorialMedico;
import logico.Medico;
import logico.Paciente;
import logico.Persona;
import logico.User;
import logico.Vacuna;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtusuario;
	private JTextField txtpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream clinica;
				FileOutputStream clinica2;
				ObjectInputStream clinicaRead;
				ObjectOutputStream clinicaWrite;
				try {
					clinica = new FileInputStream("clinica.dat");
					clinicaRead = new ObjectInputStream(clinica);
					Clinica temp = (Clinica) clinicaRead.readObject();
					Clinica.setClinica(temp);
					clinica.close();
					clinicaRead.close();
				} catch (FileNotFoundException e) {
					try {
						clinica2 = new FileOutputStream("clinica.dat");
						clinicaWrite = new ObjectOutputStream(clinica2);
						User admin = new User("Administrador", "Admin", "Admin", null);
						Clinica.getInstance().AgregarUser(admin);
						clinicaWrite.writeObject(Clinica.getInstance());
						clinica2.close();
						clinicaWrite.close();
					} catch (FileNotFoundException e1) {

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel PanelUsuario = new JPanel();
		PanelUsuario.setBounds(12, 13, 398, 94);
		panel.add(PanelUsuario);
		PanelUsuario.setLayout(null);

		JLabel lblusuario = new JLabel("Usuario:");
		lblusuario.setBounds(12, 16, 56, 16);
		PanelUsuario.add(lblusuario);

		txtusuario = new JTextField();
		txtusuario.setBounds(82, 13, 209, 22);
		PanelUsuario.add(txtusuario);
		txtusuario.setColumns(10);

		txtpassword = new JTextField();
		txtpassword.setBounds(82, 48, 209, 22);
		PanelUsuario.add(txtpassword);
		txtpassword.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(12, 51, 73, 16);
		PanelUsuario.add(lblPassword);

		JButton btnlogin = new JButton("Entrar");
		btnlogin.setBounds(303, 13, 73, 57);
		PanelUsuario.add(btnlogin);

		JPanel PanelINfo = new JPanel();
		PanelINfo.setBounds(12, 120, 398, 110);
		panel.add(PanelINfo);
		PanelINfo.setLayout(null);

		JTextPane txtpnIntroduzcaSuUsuario = new JTextPane();
		txtpnIntroduzcaSuUsuario.setEditable(false);
		txtpnIntroduzcaSuUsuario.setText(
				"Introduzca su usuario y contrase\u00F1a para ingresar al sistema.\r\n\r\nSi olvido su contrase\u00F1a, contactar con un administrador para reiniciarla.\r\n\r\n");
		txtpnIntroduzcaSuUsuario.setBounds(0, 5, 386, 92);
		PanelINfo.add(txtpnIntroduzcaSuUsuario);
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Clinica.getInstance().confirmLogin(txtusuario.getText(), txtpassword.getText())) {
					Principal frame = new Principal();
					dispose();
					frame.setVisible(true);
				}
				;
			}
		});

		loadCodes();
	}

	public void loadCodes() {
		Persona.setProximoID(Clinica.getInstance().getMisPersonas().size() + 1);
		Vacuna.setCodigoVacuna(Clinica.getInstance().getMisVacunas().size() + 1);
		Enfermedad.setCodigoEnfermedad(Clinica.getInstance().getMisEnfermedades().size() + 1);
		;
		Cita.setCodigoCita(Clinica.getInstance().getMisCitas().size() + 1);
		HistorialMedico.setCodigoHistorial(Clinica.getInstance().getMisPacientes().size() + 1);
		Medico.setCodigoMedico(Clinica.getInstance().getMisMedicos().size() + 1);
		Paciente.setCodigoPaciente(Clinica.getInstance().getMisPacientes().size() + 1);
		Empleado.setCodigoEmpleado(Clinica.getInstance().getMisEmpleados().size() + 1);
	}
}
