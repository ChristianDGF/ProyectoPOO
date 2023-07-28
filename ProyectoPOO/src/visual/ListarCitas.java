package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cita;
import logico.Clinica;
import logico.Medico;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ListarCitas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel CitasModel = new DefaultTableModel();
	private Cita selected = null;
	private Object[] row;
	private Medico miMedico = null;
	private ArrayList<Cita> misCitas = new ArrayList<Cita>();
	private JButton realizarBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarCitas dialog = new ListarCitas(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarCitas(Medico medico) {
		miMedico = medico;
		setTitle("Citas");
		setBounds(100, 100, 649, 470);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 613, 376);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				if(index >= 0)
				{
					selected = misCitas.get(index);
					realizarBtn.setEnabled(true);
				}else {
					selected = null;
					realizarBtn.setEnabled(false);
				}
			}
		});
		String headers[] = {"Codigo","Persona","Cedula","Fecha","Estado"};
		CitasModel.setColumnIdentifiers(headers);
		table.setModel(CitasModel);
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				realizarBtn = new JButton("Realizar");
				realizarBtn.setEnabled(false);
				realizarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegistrarConsulta regConsulta = new RegistrarConsulta(selected.getPersona(), selected.getMedico(), selected);
						regConsulta.setModal(true);
						regConsulta.setLocationRelativeTo(null);
						regConsulta.setVisible(true);
					}
				});
				realizarBtn.setActionCommand("OK");
				buttonPane.add(realizarBtn);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadCitas();
	}
	
	public void loadCitas()
	{
		CitasModel.setRowCount(0);
		row = new Object[table.getColumnCount()];
		
		if(miMedico != null)
		{
			for(Cita cita: Clinica.getInstance().getMisCitas())
			{
				if(cita.getMedico().equals(miMedico))
				{
					misCitas.add(cita);
					row[0] = cita.getCodigo();
					row[1] = cita.getPersona().getNombre();
					row[2] = cita.getPersona().getCedula();
					row[3] = cita.getFecha();
					row[4] = cita.getEstado();
					CitasModel.addRow(row);
				}		
			}
			
		}else {
			
			for(Cita cita: Clinica.getInstance().getMisCitas())
			{
					misCitas.add(cita);
					row[0] = cita.getCodigo();
					row[1] = cita.getPersona().getNombre();
					row[2] = cita.getPersona().getCedula();
					row[3] = cita.getFecha();
					row[4] = cita.getEstado();
					CitasModel.addRow(row);	
			}
		} 
	}
}
