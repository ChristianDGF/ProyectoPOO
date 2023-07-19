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
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuempleados = new JMenu("Administracion");
		if(!Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador"))
		{
			menuempleados.setEnabled(false);
		}
		menuBar.add(menuempleados);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Registrar Empleados");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarEmpleado regempleado = new RegistrarEmpleado(null);
				regempleado.setModal(true);
				regempleado.setLocationRelativeTo(null);
				regempleado.setVisible(true);
			}
		});
		menuempleados.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Registrar Vacunas");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarVacuna regvacuna = new RegistrarVacuna(null);
				regvacuna.setModal(true);
				regvacuna.setLocationRelativeTo(null);
				regvacuna.setVisible(true);
			}
		});
		menuempleados.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Registrar Enfermedades");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarEnfermedad regenfermedad = new RegistrarEnfermedad(null);
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
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Listar Vacunas");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarVacunas listarvacunas = new ListarVacunas();
				listarvacunas.setModal(true);
				listarvacunas.setLocationRelativeTo(null);
				listarvacunas.setVisible(true);
			}
		});
		menuempleados.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Registrar Usuarios");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarUsuario regusuario = new RegistrarUsuario();
				regusuario.setModal(true);
				regusuario.setLocationRelativeTo(null);
				regusuario.setVisible(true);
			}
		});
		menuempleados.add(mntmNewMenuItem_7);
	}

}
