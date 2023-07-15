package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal {

	private JFrame frmClinicaCw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmClinicaCw.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClinicaCw = new JFrame();
		frmClinicaCw.setTitle("Clinica CW");
		frmClinicaCw.setBounds(100, 100, 767, 557);
		frmClinicaCw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClinicaCw.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmClinicaCw.getContentPane().add(panel, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		frmClinicaCw.setJMenuBar(menuBar);
		
		JMenu menuempleados = new JMenu("Administracion");
		menuBar.add(menuempleados);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Registrar Empleados");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarEmpleado regempleado = new RegistrarEmpleado();
				regempleado.setModal(true);
				regempleado.setLocationRelativeTo(null);
				regempleado.setVisible(true);
			}
		});
		menuempleados.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Registrar Vacunas");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarVacuna regvacuna = new RegistrarVacuna();
				regvacuna.setModal(true);
				regvacuna.setLocationRelativeTo(null);
				regvacuna.setVisible(true);
			}
		});
		menuempleados.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Registrar Enfermedades");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarEnfermedad regenfermedad = new RegistrarEnfermedad();
				regenfermedad.setModal(true);
				regenfermedad.setLocationRelativeTo(null);
				regenfermedad.setVisible(true);
			}
		});
		menuempleados.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Listar Empleados");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarEmpleados listarempleados = new ListarEmpleados();
				listarempleados.setModal(true);
				listarempleados.setLocationRelativeTo(null);
				listarempleados.setVisible(true);
			}
		});
		menuempleados.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Listar Enfermedades");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarEnfermedades listarenfermedades = new ListarEnfermedades();
				listarenfermedades.setModal(true);
				listarenfermedades.setLocationRelativeTo(null);
				listarenfermedades.setVisible(true);
			}
		});
		menuempleados.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Listar Medicos");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarMedicos listarmedicos = new ListarMedicos();
				listarmedicos.setModal(true);
				listarmedicos.setLocationRelativeTo(null);
				listarmedicos.setVisible(true);
			}
		});
		menuempleados.add(mntmNewMenuItem_2);
	}

}
