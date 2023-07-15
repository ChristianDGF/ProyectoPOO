package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Enfermedad;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarEnfermedades extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox comboBox;
	private JTable table;

	public ListarEnfermedades() {
		setBounds(100, 100, 765, 584);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Listado de Enfermedades", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 723, 476);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Estado:");
		lblNewLabel.setBounds(12, 26, 56, 16);
		panel.add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarEnfermedades();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Todos>", "Vigilancia"}));
		comboBox.setBounds(80, 23, 132, 22);
		panel.add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 54, 699, 409);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		cargarEnfermedades();
	}
	private void cargarEnfermedades() {
	    String estadoSeleccionado = comboBox.getSelectedItem().toString();

	    ArrayList<Enfermedad> enfermedades = Clinica.getInstance().obtenerEnfermedadesPorEstado(estadoSeleccionado);

	    DefaultTableModel model = new DefaultTableModel();
	    model.addColumn("Código");
	    model.addColumn("Nombre");
	    model.addColumn("Tipo");
	    model.addColumn("Estado");

	    for (Enfermedad enfermedad : enfermedades) {
	        Object[] row = new Object[5];
	        row[0] = enfermedad.getCodigo();
	        row[1] = enfermedad.getNombre();
	        row[2] = enfermedad.getTipo();
	        row[3] = enfermedad.getEstado();
	        model.addRow(row);
	    }

	    table.setModel(model);
	}

}
