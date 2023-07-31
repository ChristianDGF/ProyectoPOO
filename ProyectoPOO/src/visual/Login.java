package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtusuario;
	private JPasswordField txtpass;

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
		setResizable(false);
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/icons8-ambulance-48.png"));
		this.setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 403);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("INICIAR SEISON");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 27));
		lblNewLabel.setBounds(25, 26, 260, 36);
		panel.add(lblNewLabel);

		txtusuario = new JTextField();
		txtusuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (txtusuario.getText().equalsIgnoreCase("Nombre de usuario...")) {
					txtusuario.setText("");
					txtusuario.setForeground(Color.black);
				}
				if (String.valueOf(txtpass.getPassword()).isEmpty()) {
					txtpass.setText("*********");
					txtpass.setForeground(Color.gray);
				}
			}
		});
		txtusuario.setForeground(Color.BLACK);
		txtusuario.setBorder(null);
		txtusuario.setBounds(28, 120, 260, 22);
		panel.add(txtusuario);
		txtusuario.setColumns(10);

		JLabel lblusuario = new JLabel("Usuario");
		lblusuario.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblusuario.setBounds(28, 93, 73, 16);
		panel.add(lblusuario);

		txtpass = new JPasswordField();
		txtpass.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (String.valueOf(txtpass.getPassword()).equals("*********")) {
					txtpass.setText("");
					txtpass.setForeground(Color.black);
				}
				if (txtusuario.getText().isEmpty()) {
					txtusuario.setText("Nombre de usuario...");
					txtusuario.setForeground(Color.gray);
				}
			}
		});
		txtpass.setText("*********");
		txtpass.setForeground(Color.LIGHT_GRAY);
		txtpass.setBorder(null);
		txtpass.setBounds(28, 210, 260, 22);
		txtpass.setColumns(10);
		panel.add(txtpass);

		JLabel lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblPassword.setBounds(28, 183, 85, 16);
		panel.add(lblPassword);

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setBounds(28, 239, 260, 8);
		panel.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(28, 149, 260, 8);
		panel.add(separator_1);

		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Clinica.getInstance().confirmLogin(txtusuario.getText(), String.valueOf(txtpass.getPassword()))) {
					Principal frame = new Principal();
					dispose();
					frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "El usuario ingresado no es valido", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel_1.setBackground(new Color(123, 104, 238));
		panel_1.setBounds(93, 285, 125, 42);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Entrar");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNewLabel_1.setBounds(22, 11, 77, 20);
		panel_1.add(lblNewLabel_1);

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
