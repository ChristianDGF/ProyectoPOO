package visual;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import logico.Cliente;
import logico.Clinica;
import logico.Medico;
import logico.Vacuna;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import net.miginfocom.swing.MigLayout;

import javax.swing.JComboBox;

public class Principal extends JFrame {

	private JFrame frmClinicaCw;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private Dimension dim;
	private JPanel panel5;
	private JComboBox comboBox;

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
				} catch (FileNotFoundException e1) {

				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		setTitle("Clinica CW");
		setBounds(100, 100, 767, 557);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dim = super.getToolkit().getScreenSize();
		super.setSize(dim.width + 50, dim.height);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("fill", "[grow][grow][grow]", "[][grow][grow][grow]"));

		comboBox = new JComboBox();
		llenarComboBoxVacunas(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarGraficoPastelPanel5();
			}
		});
		panel.add(comboBox, "cell 2 0,growx");

		panel1 = new JPanel();
		panel.add(panel1, "cell 0 1,grow");

		panel2 = new JPanel();
		panel.add(panel2, "cell 1 1,grow");

		panel5 = new JPanel();
		panel.add(panel5, "cell 2 1,grow");

		panel3 = new JPanel();
		panel.add(panel3, "cell 0 2 2 1,grow");

		panel4 = new JPanel();
		panel.add(panel4, "cell 2 2,grow");

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Pacientes");
		if (!Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador")
				&& !Clinica.getLoginUser().getTipo().equalsIgnoreCase("Privilegiado")) {
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
		mntmNewMenuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Clinica.getLoginUser().getEmpleado() instanceof Medico) {
					JOptionPane.showMessageDialog(null, "Hay doctor logeado :D", "Registro",
							JOptionPane.INFORMATION_MESSAGE);
					Medico medico = (Medico) Clinica.getLoginUser().getEmpleado();
					ListarCitas listCitas = new ListarCitas(medico);
					listCitas.setModal(true);
					listCitas.setLocationRelativeTo(null);
					listCitas.setVisible(true);
				} else {

					ListarCitas listCitas = new ListarCitas(null);
					listCitas.setModal(true);
					listCitas.setLocationRelativeTo(null);
					listCitas.setVisible(true);
				}

			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_13);
		menuBar.add(mnNewMenu);

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
		if (!Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador")
				&& !Clinica.getLoginUser().getTipo().equalsIgnoreCase("Privilegiado")) {
			mnNewMenu_1.setEnabled(false);
		}
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_11 = new JMenuItem("Listado de Consultas");
		mnNewMenu_1.add(mntmNewMenuItem_11);

		JMenu mnNewMenu_3 = new JMenu("Enfermedades");
		if (!Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador")
				&& !Clinica.getLoginUser().getTipo().equalsIgnoreCase("Privilegiado")) {
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
		if (!Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador")) {
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
		if (!Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador")
				&& !Clinica.getLoginUser().getTipo().equalsIgnoreCase("Privilegiado")) {
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
		if (!Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador")) {
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
		if (!Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador")) {
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

		JMenu mnNewMenu_7 = new JMenu("Respaldo");
		menuempleados.add(mnNewMenu_7);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Realizar Backup");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "Estas seguro(a) que desea hacer el respaldo ",
						"Confirmacion", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					Cliente.enviarCopiaRespaldo();
				}
			}
		});
		mnNewMenu_7.add(mntmNewMenuItem_5);
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
		Thread actualizacionThread = new Thread(() -> {
			while (true) {
				SwingUtilities.invokeLater(this::actualizarGraficoPastelPanel1);
				SwingUtilities.invokeLater(this::actualizarGraficoBarrasPanel2);
				SwingUtilities.invokeLater(this::actualizarGraficoBarrasPanel3);
				SwingUtilities.invokeLater(this::actualizarGraficoBarrasPanel4);
				SwingUtilities.invokeLater(this::actualizarGraficoPastelPanel5);
				try {
					Thread.sleep(3600000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		actualizacionThread.start();

	}

	private void actualizarGraficoPastelPanel1() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Masculino", Clinica.getInstance().obtenerNumeroPacientesPorSexo("Masculino"));
		dataset.setValue("Femenino", Clinica.getInstance().obtenerNumeroPacientesPorSexo("Femenino"));

		JFreeChart chart = ChartFactory.createPieChart("Distribución de sexos de pacientes", dataset, true, true, true);
		ChartPanel chartPanel = new ChartPanel(chart);

		panel1.removeAll();
		panel1.setLayout(new BorderLayout());
		panel1.add(chartPanel, BorderLayout.CENTER);
		panel1.revalidate();
	}

	private void actualizarGraficoBarrasPanel2() {
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
		dataset2.setValue(Clinica.getInstance().contarPacientesPorTipoSangre("A+"), "Cantidad", "A+");
		dataset2.setValue(Clinica.getInstance().contarPacientesPorTipoSangre("B+"), "Cantidad", "B+");
		dataset2.setValue(Clinica.getInstance().contarPacientesPorTipoSangre("O+"), "Cantidad", "O+");
		dataset2.setValue(Clinica.getInstance().contarPacientesPorTipoSangre("AB+"), "Cantidad", "AB+");
		dataset2.setValue(Clinica.getInstance().contarPacientesPorTipoSangre("A-"), "Cantidad", "A-");
		dataset2.setValue(Clinica.getInstance().contarPacientesPorTipoSangre("B-"), "Cantidad", "B-");
		dataset2.setValue(Clinica.getInstance().contarPacientesPorTipoSangre("O-"), "Cantidad", "O-");
		dataset2.setValue(Clinica.getInstance().contarPacientesPorTipoSangre("AB-"), "Cantidad", "AB+");

		JFreeChart chart2 = ChartFactory.createBarChart("Distribución de tipos de sangre de pacientes",
				"Tipo de Sangre", "Cantidad", dataset2);

		NumberAxis yAxis = (NumberAxis) chart2.getCategoryPlot().getRangeAxis();
		yAxis.setTickUnit(new NumberTickUnit(1));

		ChartPanel chartPanel2 = new ChartPanel(chart2);
		panel2.removeAll();
		panel2.setLayout(new BorderLayout());
		panel2.add(chartPanel2, BorderLayout.CENTER);
		panel2.revalidate();
	}

	private void actualizarGraficoBarrasPanel3() {
		DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
		dataset3.addValue(Clinica.getInstance().contarPacientesMenoresDe(18), "Pacientes", "-18 ");
		dataset3.addValue(Clinica.getInstance().contarPacientesPorRangoEdad(18, 25), "Pacientes", "18-25 ");
		dataset3.addValue(Clinica.getInstance().contarPacientesPorRangoEdad(25, 35), "Pacientes", "25-35 ");
		dataset3.addValue(Clinica.getInstance().contarPacientesPorRangoEdad(35, 45), "Pacientes", "35-45 ");
		dataset3.addValue(Clinica.getInstance().contarPacientesMayoresDe(45), "Pacientes", "45+ ");

		JFreeChart chart3 = ChartFactory.createBarChart("Rango de edad de pacientes", "Rango de Edad", "Pacientes",
				dataset3);

		NumberAxis yAxis = (NumberAxis) chart3.getCategoryPlot().getRangeAxis();
		yAxis.setTickUnit(new NumberTickUnit(1));

		ChartPanel chartPanel3 = new ChartPanel(chart3);
		panel3.removeAll();
		panel3.setLayout(new BorderLayout());
		panel3.add(chartPanel3, BorderLayout.CENTER);
		panel3.revalidate();
	}

	private void actualizarGraficoBarrasPanel4() {
		DefaultCategoryDataset dataset4 = new DefaultCategoryDataset();
		dataset4.addValue(Clinica.getInstance().pacientesEnfermedades(), "Enfermos", "");
		dataset4.addValue(Clinica.getInstance().PacientesEnfermedadesVigilancia(), "Enfermedades en Vigilancia", "");
		dataset4.addValue(Clinica.getInstance().PacientesSanos(), "Sanos", "");

		JFreeChart chart4 = ChartFactory.createBarChart("Cantidad de Pacientes por Estado de Salud", "", "Cantidad",
				dataset4);

		NumberAxis yAxis = (NumberAxis) chart4.getCategoryPlot().getRangeAxis();
		yAxis.setTickUnit(new NumberTickUnit(1));

		ChartPanel chartPanel4 = new ChartPanel(chart4);
		panel4.removeAll();
		panel4.setLayout(new BorderLayout());
		panel4.add(chartPanel4, BorderLayout.CENTER);
		panel4.revalidate();
	}

	private void actualizarGraficoPastelPanel5() {
		DefaultPieDataset dataset5 = new DefaultPieDataset();
		dataset5.setValue("Vacunados",
				Clinica.getInstance().contarPacientesConVacuna(comboBox.getSelectedItem().toString()));
		dataset5.setValue("No Vacunados",
				Clinica.getInstance().contarPacientesSinVacuna(comboBox.getSelectedItem().toString()));

		JFreeChart chart5 = ChartFactory.createPieChart("Pacintes vacunados", dataset5, true, true, true);
		ChartPanel chartPanel5 = new ChartPanel(chart5);

		panel5.removeAll();
		panel5.setLayout(new BorderLayout());
		panel5.add(chartPanel5, BorderLayout.CENTER);
		panel5.revalidate();
	}

	public void llenarComboBoxVacunas(JComboBox<String> comboBox) {
		comboBox.removeAllItems();
		comboBox.addItem("<seleccionar>");
		for (Vacuna vacuna : Clinica.getInstance().getMisVacunas()) {
			comboBox.addItem(vacuna.getCodigo());
		}
	}
}
