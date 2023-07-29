package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Cita;
import logico.Clinica;
import logico.HistorialMedico;
import logico.Vacuna;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AgregarVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnCancelar;
	private JButton btnAplicar;
	private JTable tableVacunasAplicadas;
	private JTable tableVacunasDisponibles;
	private DefaultTableModel VacunasAplicadas = new DefaultTableModel();
	private DefaultTableModel VacunasDisponibles = new DefaultTableModel();
	private HistorialMedico miHistorial = null;
	private Object[] row;
	private ArrayList<Vacuna> misVacunas = new ArrayList<Vacuna>();
	private ArrayList<Vacuna> misVacunasDisponibles = new ArrayList<Vacuna>();
	private ArrayList<Vacuna> misVacunasShowed = new ArrayList<Vacuna>();
	private JComboBox comboBox;
	private JButton btnAgregar;
	private Vacuna selected = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AgregarVacuna dialog = new AgregarVacuna(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AgregarVacuna(HistorialMedico historial) {
		miHistorial = historial;
		setTitle("Vacunaci\u00F3n");
		setBounds(100, 100, 1224, 512);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Vacunas Aplicadas:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 490, 414);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 470, 381);
		panel.add(scrollPane);
		
		tableVacunasAplicadas = new JTable();
		String headers[] = {"Enfermedad","Tipo","Laboratorio"};
		VacunasAplicadas.setColumnIdentifiers(headers);
		tableVacunasAplicadas.setModel(VacunasAplicadas);
		scrollPane.setViewportView(tableVacunasAplicadas);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Vacunas Disponibles:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(510, 11, 490, 414);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 22, 470, 381);
		panel_1.add(scrollPane_1);
		
		tableVacunasDisponibles = new JTable();
		tableVacunasDisponibles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableVacunasDisponibles.getSelectedRow();
				if(index >= 0)
				{
					selected = misVacunasShowed.get(index);
					btnAgregar.setEnabled(true);
				}else {
					selected = null;
					btnAgregar.setEnabled(false);
				}
			}
		});
		VacunasDisponibles.setColumnIdentifiers(headers);
		tableVacunasDisponibles.setModel(VacunasDisponibles);
		scrollPane_1.setViewportView(tableVacunasDisponibles);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Seleccionar>", "Vivas atenuadas", "Inactivadas", "Toxoides", "Subunidades", "Vector recombinante", "Vacuna de ADN", "Vacuna de ARN"}));
		comboBox.setBounds(1010, 46, 188, 20);
		contentPanel.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Tipo:");
		lblNewLabel.setBounds(1010, 21, 46, 14);
		contentPanel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Filtrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadFilters();
			}
		});
		btnNewButton.setBounds(1060, 85, 89, 23);
		contentPanel.add(btnNewButton);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setEnabled(false);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				misVacunas.add(selected);
				misVacunasDisponibles.remove(selected);
				refreshMisVacunas();
				refreshVacunasDisponibles();
				selected = null;
				btnAgregar.setEnabled(false);
			}
		});
		btnAgregar.setBounds(1060, 119, 89, 23);
		contentPanel.add(btnAgregar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAplicar = new JButton("Aplicar");
				btnAplicar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnAplicar.setActionCommand("OK");
				buttonPane.add(btnAplicar);
				getRootPane().setDefaultButton(btnAplicar);
			}
			{
				btnCancelar = new JButton("Cancel");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		
		loadMisVacunas();
	}
	
	public void refreshMisVacunas()
	{
		VacunasAplicadas.setRowCount(0);
		row = new Object[tableVacunasAplicadas.getColumnCount()];
		
		if(miHistorial != null)
		{
			for(Vacuna vacuna: misVacunas)
			{
				row[0] = vacuna.getEnfermedad();
				row[1] = vacuna.getTipo();
				row[2] = vacuna.getLaboratorio();
				VacunasAplicadas.addRow(row);	
			}		
		}
	}
	
	public void refreshVacunasDisponibles()
	{
		VacunasDisponibles.setRowCount(0);
		row = new Object[tableVacunasDisponibles.getColumnCount()];
		
		misVacunasShowed.clear();
		for(Vacuna vacuna: misVacunasDisponibles)
		{
			    misVacunasShowed.add(vacuna);
				row[0] = vacuna.getEnfermedad();
				row[1] = vacuna.getTipo();
				row[2] = vacuna.getLaboratorio();
				VacunasDisponibles.addRow(row);	
		}
	}
	
	public void loadFilters()
	{
		VacunasDisponibles.setRowCount(0);
		row = new Object[tableVacunasDisponibles.getColumnCount()];
		
		if(comboBox.getSelectedIndex() > 0)
		{
			misVacunasShowed.clear();
			for(Vacuna vacuna: misVacunasDisponibles)
			{
				if(vacuna.getTipo().equals(comboBox.getSelectedItem()))
				{
					misVacunasShowed.add(vacuna);
					row[0] = vacuna.getEnfermedad();
					row[1] = vacuna.getTipo();
					row[2] = vacuna.getLaboratorio();
					VacunasDisponibles.addRow(row);	
				}
			}
		}
	}
	
	public void loadMisVacunas()
	{
		VacunasAplicadas.setRowCount(0);
		row = new Object[tableVacunasAplicadas.getColumnCount()];
		
		if(miHistorial != null)
		{
			for(Vacuna vacuna: miHistorial.getMisVancunas())
			{
				misVacunas.add(vacuna);
				row[0] = vacuna.getEnfermedad();
				row[1] = vacuna.getTipo();
				row[2] = vacuna.getLaboratorio();
				VacunasAplicadas.addRow(row);	
			}
			
			loadVacunasDisponibles();			
		}
	}
	
	public void loadVacunasDisponibles()
	{
		VacunasDisponibles.setRowCount(0);
		row = new Object[tableVacunasDisponibles.getColumnCount()];
		
		for(Vacuna vacuna: Clinica.getInstance().getMisVacunas())
		{
			if(!misVacunas.contains(vacuna))
			{
				misVacunasDisponibles.add(vacuna);
				misVacunasShowed.add(vacuna);
				row[0] = vacuna.getEnfermedad();
				row[1] = vacuna.getTipo();
				row[2] = vacuna.getLaboratorio();
				VacunasDisponibles.addRow(row);	
			}
			
		}
	}
}
