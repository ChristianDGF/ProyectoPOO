package visual;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import logico.Cita;
import logico.Cliente;
import logico.Clinica;
import logico.Enfermedad;
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
import java.time.LocalDate;
import java.util.ArrayList;

import net.miginfocom.swing.MigLayout;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Principal extends JFrame {

	private JFrame frmClinicaCw;
	private Dimension dim;
	private JMenu MenuCitas;
	private JMenu MenuPacientes;
	private JMenu MenuEnfermedades;
	private JMenu MenuVacunas;
	private JMenu MenuAdministracion;
	private JMenuItem MenuRegEnfermedad;
	private JMenuItem MenuRegVacuna;
	private JMenuItem MenuRegCita;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_8;
	private JPanel panel_9;
	private JComboBox cmbvacuna;
	private JComboBox cmbenfermedad;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel CitasModel = new DefaultTableModel();
	private Object[] row;
	private Medico miMedico = null;
	private ArrayList<Cita> misCitas = new ArrayList<Cita>();
	private ArrayList<Cita> misCitasShowed = new ArrayList<Cita>();

	public Principal() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/icons8-ambulance-48.png"));
		this.setIconImage(icon.getImage());
		if (Clinica.getLoginUser().getEmpleado() instanceof Medico) {
			miMedico = (Medico) Clinica.getLoginUser().getEmpleado();
		}
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
		panel.setLayout(new MigLayout("fill", "[400.00,grow,left][403.00,grow,center][411.00,grow,right]",
				"[5.00][300.00,grow][299.00,grow,center][300.00,grow]"));

		panel_1 = new JPanel();
		ImageIcon imageIcon = new ImageIcon("download.jpg");

		JLabel imageLabel = new JLabel(imageIcon);

		JPanel panel_1 = new JPanel();

		panel_1.add(imageLabel);

		panel_2 = new JPanel();
		panel_3 = new JPanel();
		panel_4 = new JPanel();
		panel_5 = new JPanel();
		panel_6 = new JPanel();
		panel_8 = new JPanel();
		panel_9 = new JPanel();

		cmbenfermedad = new JComboBox();
		panel.add(cmbenfermedad, "cell 1 0,growx");
		llenarComboBoxEnfermedades(cmbenfermedad);
		cmbenfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarGraficoPastelPanel6();
			}
		});

		cmbvacuna = new JComboBox();
		panel.add(cmbvacuna, "cell 2 0,growx");
		llenarComboBoxVacunas(cmbvacuna);
		cmbvacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarGraficoPastelPanel5();
			}
		});

		panel.add(panel_1, "cell 0 1,grow");
		panel.add(panel_2, "cell 1 1,grow");
		panel.add(panel_3, "cell 2 1,grow");
		panel.add(panel_4, "cell 0 2 1 2,grow");
		panel_4.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panel_4.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);
		String headers[] = { "Persona", "Cedula", "Hora", "Estado" };
		CitasModel.setColumnIdentifiers(headers);
		table.setModel(CitasModel);
		scrollPane.setViewportView(table);
		panel.add(panel_5, "cell 1 2,grow");
		panel.add(panel_6, "cell 2 2,grow");
		panel.add(panel_8, "cell 1 3,grow");
		panel.add(panel_9, "cell 2 3,grow");

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		MenuPacientes = new JMenu("Pacientes");

		MenuCitas = new JMenu("Citas");
		menuBar.add(MenuCitas);

		MenuRegCita = new JMenuItem("Registrar Citas");
		MenuRegCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarCita regCon = new RegistrarCita();
				regCon.setModal(true);
				regCon.setLocationRelativeTo(null);
				regCon.setVisible(true);
			}
		});
		MenuCitas.add(MenuRegCita);

		JMenuItem mntmNewMenuItem_13 = new JMenuItem("Listado de Citas");
		mntmNewMenuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Clinica.getLoginUser().getEmpleado() instanceof Medico) {
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
		MenuCitas.add(mntmNewMenuItem_13);
		menuBar.add(MenuPacientes);

		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Listado de Pacientes");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarPacientes listpaciente = new ListarPacientes();
				listpaciente.setModal(true);
				listpaciente.setLocationRelativeTo(null);
				listpaciente.setVisible(true);
			}
		});
		MenuPacientes.add(mntmNewMenuItem_9);

		MenuEnfermedades = new JMenu("Enfermedades");
		menuBar.add(MenuEnfermedades);

		MenuRegEnfermedad = new JMenuItem("Registrar Enfermedad");
		MenuRegEnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador")) {
					RegistrarEnfermedad regenfermedad = new RegistrarEnfermedad(null, false);
					regenfermedad.setModal(true);
					regenfermedad.setLocationRelativeTo(null);
					regenfermedad.setVisible(true);
				} else {

					RegistrarEnfermedad regenfermedad = new RegistrarEnfermedad(null, true);
					regenfermedad.setModal(true);
					regenfermedad.setLocationRelativeTo(null);
					regenfermedad.setVisible(true);
				}

			}
		});
		MenuEnfermedades.add(MenuRegEnfermedad);

		JMenuItem mntmNewMenuItem_15 = new JMenuItem("Listado de Enfermedades");
		mntmNewMenuItem_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarEnfermedades listarenfermedades = new ListarEnfermedades();
				listarenfermedades.setModal(true);
				listarenfermedades.setLocationRelativeTo(null);
				listarenfermedades.setVisible(true);
			}
		});
		MenuEnfermedades.add(mntmNewMenuItem_15);

		MenuVacunas = new JMenu("Vacunas");
		menuBar.add(MenuVacunas);

		MenuRegVacuna = new JMenuItem("Registrar Vacunas");
		MenuRegVacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador")) {
					RegistrarVacuna regvacuna = new RegistrarVacuna(null, false);
					regvacuna.setModal(true);
					regvacuna.setLocationRelativeTo(null);
					regvacuna.setVisible(true);
				} else {
					RegistrarVacuna regvacuna = new RegistrarVacuna(null, true);
					regvacuna.setModal(true);
					regvacuna.setLocationRelativeTo(null);
					regvacuna.setVisible(true);
				}

			}
		});
		MenuVacunas.add(MenuRegVacuna);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Listado de Vacunas");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarVacunas listarvacunas = new ListarVacunas();
				listarvacunas.setModal(true);
				listarvacunas.setLocationRelativeTo(null);
				listarvacunas.setVisible(true);
			}
		});
		MenuVacunas.add(mntmNewMenuItem_6);

		MenuAdministracion = new JMenu("Administracion");
		menuBar.add(MenuAdministracion);
		JMenu mnNewMenu_5 = new JMenu("Empleados");
		MenuAdministracion.add(mnNewMenu_5);

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
		MenuAdministracion.add(mnNewMenu_6);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Registrar Usuarios");
		mnNewMenu_6.add(mntmNewMenuItem_7);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Listar Usuario");
		mnNewMenu_6.add(mntmNewMenuItem_3);

		JMenu mnNewMenu_7 = new JMenu("Respaldo");
		MenuAdministracion.add(mnNewMenu_7);

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
				SwingUtilities.invokeLater(this::actualizarGraficoPastelPanel6);
				try {
					Thread.sleep(3600000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		actualizacionThread.start();
		LoadRoles();
		loadCitas();

	}

	private void LoadRoles() {

		if (Clinica.getLoginUser().getTipo().equalsIgnoreCase("Basico")) {
			MenuVacunas.setVisible(false);
			MenuEnfermedades.setVisible(false);
			MenuAdministracion.setVisible(false);
		}

		if (Clinica.getLoginUser().getTipo().equalsIgnoreCase("Privilegiado")) {
			MenuAdministracion.setVisible(false);
			MenuRegVacuna.setVisible(false);
			MenuRegEnfermedad.setVisible(false);
			MenuRegCita.setVisible(false);
		}

	}

	private void actualizarGraficoPastelPanel1() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Masculino", Clinica.getInstance().obtenerNumeroPacientesPorSexo("Masculino"));
		dataset.setValue("Femenino", Clinica.getInstance().obtenerNumeroPacientesPorSexo("Femenino"));

		JFreeChart chart = ChartFactory.createPieChart("Distribución de sexos de pacientes", dataset, true, true, true);
		ChartPanel chartPanel = new ChartPanel(chart);

		panel_9.removeAll();
		panel_9.setLayout(new BorderLayout());
		panel_9.add(chartPanel, BorderLayout.CENTER);
		panel_9.revalidate();
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
		panel_5.removeAll();
		panel_5.setLayout(new BorderLayout());
		panel_5.add(chartPanel2, BorderLayout.CENTER);
		panel_5.revalidate();
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
		panel_8.removeAll();
		panel_8.setLayout(new BorderLayout());
		panel_8.add(chartPanel3, BorderLayout.CENTER);
		panel_8.revalidate();
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
		panel_6.removeAll();
		panel_6.setLayout(new BorderLayout());
		panel_6.add(chartPanel4, BorderLayout.CENTER);
		panel_6.revalidate();
	}

	private void actualizarGraficoPastelPanel5() {
		DefaultPieDataset dataset5 = new DefaultPieDataset();
		dataset5.setValue("Vacunados",
				Clinica.getInstance().contarPacientesConVacuna(cmbvacuna.getSelectedItem().toString()));
		dataset5.setValue("No Vacunados",
				Clinica.getInstance().contarPacientesSinVacuna(cmbvacuna.getSelectedItem().toString()));

		JFreeChart chart5 = ChartFactory.createPieChart("Pacintes vacunados", dataset5, true, true, true);
		ChartPanel chartPanel5 = new ChartPanel(chart5);

		panel_3.removeAll();
		panel_3.setLayout(new BorderLayout());
		panel_3.add(chartPanel5, BorderLayout.CENTER);
		panel_3.revalidate();
	}

	private void actualizarGraficoPastelPanel6() {
		DefaultPieDataset dataset6 = new DefaultPieDataset();
		dataset6.setValue("Enfermos",
				Clinica.getInstance().contarPacientesConEnfermedad(cmbenfermedad.getSelectedItem().toString()));
		dataset6.setValue("No Enfermos",
				Clinica.getInstance().contarPacientesSinEnfermedad(cmbenfermedad.getSelectedItem().toString()));

		JFreeChart chart5 = ChartFactory.createPieChart("Pacintes enfermos", dataset6, true, true, true);
		ChartPanel chartPanel5 = new ChartPanel(chart5);

		panel_2.removeAll();
		panel_2.setLayout(new BorderLayout());
		panel_2.add(chartPanel5, BorderLayout.CENTER);
		panel_2.revalidate();
	}

	public void llenarComboBoxVacunas(JComboBox<String> comboBox) {
		comboBox.removeAllItems();
		comboBox.addItem("<seleccionar>");
		for (Vacuna vacuna : Clinica.getInstance().getMisVacunas()) {
			comboBox.addItem(vacuna.getCodigo());
		}
	}

	public void llenarComboBoxEnfermedades(JComboBox<String> comboBox) {
		comboBox.removeAllItems();
		comboBox.addItem("<seleccionar>");
		for (Enfermedad enfermedad : Clinica.getInstance().getMisEnfermedades()) {
			comboBox.addItem(enfermedad.getCodigo());
		}
	}

	public void loadCitas() {
		CitasModel.setRowCount(0);
		row = new Object[table.getColumnCount()];

		if (miMedico != null) {
			for (Cita cita : Clinica.getInstance().getMisCitas()) {
				if (cita.getMedico().equals(miMedico) && cita.getEstado().equals("Pendiente")
						&& LocalDate.now().equals(cita.getFecha())) {
					misCitasShowed.add(cita);
					misCitas.add(cita);
					row[0] = cita.getPersona().getNombre();
					row[1] = cita.getPersona().getCedula();
					row[2] = cita.getHora();
					row[3] = cita.getEstado();
					CitasModel.addRow(row);
				}
			}

		} else {

			for (Cita cita : Clinica.getInstance().getMisCitas()) {
				misCitasShowed.add(cita);
				misCitas.add(cita);
				row[0] = cita.getPersona().getNombre();
				row[1] = cita.getPersona().getCedula();
				row[2] = cita.getHora();
				row[3] = cita.getEstado();
				CitasModel.addRow(row);
			}
		}
	}
}
