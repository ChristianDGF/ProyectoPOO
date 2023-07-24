package visual;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import logico.Clinica;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Principal extends JFrame{

	private JFrame frmClinicaCw;

	public Principal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FileOutputStream clinica2;
				ObjectOutputStream clinicaWrite;
				try {
					clinica2 = new FileOutputStream("clinica.dat");
					clinicaWrite = new ObjectOutputStream(clinica2);
					clinicaWrite.writeObject(Clinica.getInstance());
				}
				catch(FileNotFoundException e1) {
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		setTitle("Clinica CW");
		setBounds(100, 100, 767, 557);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Pacientes");
		if(!Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador") && !Clinica.getLoginUser().getTipo().equalsIgnoreCase("Privilegiado"))
		{
			mnNewMenu.setEnabled(false);
		}
		
		JMenu mnNewMenu_2 = new JMenu("Citas");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_12 = new JMenuItem("Registrar Citas");
		mntmNewMenuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarCita regCon = new RegistrarCita();
				regCon.setModal(true);
				regCon.setLocationRelativeTo(null);
				regCon.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_12);
		
		JMenuItem mntmNewMenuItem_13 = new JMenuItem("Listado de Citas");
		mnNewMenu_2.add(mntmNewMenuItem_13);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Registrar Pacientes");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarPaciente regpac = new RegistrarPaciente(null);
				regpac.setModal(true);
				regpac.setLocationRelativeTo(null);
				regpac.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Listado de Pacientes");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarPacientes listpaciente = new ListarPacientes();
				listpaciente.setModal(true);
				listpaciente.setLocationRelativeTo(null);
				listpaciente.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_9);
		
		JMenu mnNewMenu_1 = new JMenu("Consultas");
		if(!Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador") && !Clinica.getLoginUser().getTipo().equalsIgnoreCase("Privilegiado"))
		{
			mnNewMenu_1.setEnabled(false);
		}
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Registrar Consultas");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarConsulta regconsulta = new RegistrarConsulta(null, null, null);
				regconsulta.setModal(true);
				regconsulta.setLocationRelativeTo(null);
				regconsulta.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_10);
		
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("Listado de Consultas");
		mnNewMenu_1.add(mntmNewMenuItem_11);
		
		JMenu mnNewMenu_3 = new JMenu("Enfermedades");
		if(!Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador") && !Clinica.getLoginUser().getTipo().equalsIgnoreCase("Privilegiado"))
		{
			mnNewMenu_3.setEnabled(false);
		}

		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_14 = new JMenuItem("Registrar Enfermedad");
		mntmNewMenuItem_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarEnfermedad regenfermedad = new RegistrarEnfermedad(null);
				regenfermedad.setModal(true);
				regenfermedad.setLocationRelativeTo(null);
				regenfermedad.setVisible(true);
			}
		});
		if(!Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador"))
		{
			mntmNewMenuItem_14.setEnabled(false);
		}
		mnNewMenu_3.add(mntmNewMenuItem_14);
		
		JMenuItem mntmNewMenuItem_15 = new JMenuItem("Listado de Enfermedades");
		mntmNewMenuItem_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarEnfermedades listarenfermedades = new ListarEnfermedades();
				listarenfermedades.setModal(true);
				listarenfermedades.setLocationRelativeTo(null);
				listarenfermedades.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_15);
		
		JMenu mnNewMenu_4 = new JMenu("Vacunas");
		if(!Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador") && !Clinica.getLoginUser().getTipo().equalsIgnoreCase("Privilegiado"))
		{
			mnNewMenu_4.setEnabled(false);
		}
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Registrar Vacunas");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarVacuna regvacuna = new RegistrarVacuna(null);
				regvacuna.setModal(true);
				regvacuna.setLocationRelativeTo(null);
				regvacuna.setVisible(true);
			}
		});
		if(!Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador"))
		{
			mntmNewMenuItem_1.setEnabled(false);
		}
		mnNewMenu_4.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Listado de Vacunas");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarVacunas listarvacunas = new ListarVacunas();
				listarvacunas.setModal(true);
				listarvacunas.setLocationRelativeTo(null);
				listarvacunas.setVisible(true);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_6);
		
		JMenu menuempleados = new JMenu("Administracion");
		menuBar.add(menuempleados);
		if(!Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador"))
		{
			menuempleados.setEnabled(false);
		}
		
		JMenu mnNewMenu_5 = new JMenu("Empleados");
		menuempleados.add(mnNewMenu_5);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Registrar Empleados");
		mnNewMenu_5.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Listar Empleados");
		mnNewMenu_5.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Listar Medicos");
		mnNewMenu_5.add(mntmNewMenuItem_2);
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarMedicos listarmedicos = new ListarMedicos();
				listarmedicos.setModal(true);
				listarmedicos.setLocationRelativeTo(null);
				listarmedicos.setVisible(true);
			}
		});
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarEmpleados listarempleados = new ListarEmpleados();
				listarempleados.setModal(true);
				listarempleados.setLocationRelativeTo(null);
				listarempleados.setVisible(true);
			}
		});
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarEmpleado regempleado = new RegistrarEmpleado(null);
				regempleado.setModal(true);
				regempleado.setLocationRelativeTo(null);
				regempleado.setVisible(true);
			}
		});
		
		JMenu mnNewMenu_6 = new JMenu("Usuarios");
		menuempleados.add(mnNewMenu_6);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Registrar Usuarios");
		mnNewMenu_6.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Listar Usuario");
		mnNewMenu_6.add(mntmNewMenuItem_3);
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarUsuarios listusuario = new ListarUsuarios();
				listusuario.setModal(true);
				listusuario.setLocationRelativeTo(null);
				listusuario.setVisible(true);
			}
		});
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarUsuario regusuario = new RegistrarUsuario(null);
				regusuario.setModal(true);
				regusuario.setLocationRelativeTo(null);
				regusuario.setVisible(true);
			}
		});
	}

}
