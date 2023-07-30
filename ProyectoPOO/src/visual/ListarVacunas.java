package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Vacuna;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class ListarVacunas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox cmbtipo;
	private JTable table;
	private JButton btnmodificar;
	private JButton btneliminar;
	private Vacuna mivacuna = null;

	public ListarVacunas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\Icons for project\\icons8-vaccine-50.png"));
		setTitle("Vacunas");
		setBounds(100, 100, 736, 505);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(
					new TitledBorder(null, "Listado de Vacunas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(12, 13, 694, 397);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lbltipo = new JLabel("Tipo:");
				lbltipo.setBounds(12, 25, 56, 16);
				panel.add(lbltipo);
			}
			{
				cmbtipo = new JComboBox();
				cmbtipo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						listarVacunasPorTipo();
					}
				});
				cmbtipo.setModel(new DefaultComboBoxModel(new String[] { "<Todos>", "Vivas atenuadas", "Inactivadas",
						"Toxoides", "Subunidades", "Vector recombinante", "Vacuna de ADN", "Vacuna de ARN" }));
				cmbtipo.setBounds(51, 22, 165, 22);
				panel.add(cmbtipo);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(12, 54, 670, 330);
				panel.add(scrollPane);
				{
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int index = table.getSelectedRow();
							if (index >= 0 && !Clinica.getLoginUser().getTipo().equalsIgnoreCase("basico")) {
								btnmodificar.setEnabled(true);
								if (Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador")) {
									btneliminar.setEnabled(true);
								}
								mivacuna = Clinica.getInstance().getVacunaByCode(table.getValueAt(index, 0).toString());
							}
						}
					});
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btneliminar = new JButton("Eliminar");
				btneliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (mivacuna != null) {
							int option = JOptionPane
									.showConfirmDialog(null,
											"Estas seguro(a) que desea eliminar la vacuna con el codigo: "
													+ mivacuna.getCodigo(),
											"Confirmacion", JOptionPane.OK_CANCEL_OPTION);
							if (option == JOptionPane.OK_OPTION) {
								Clinica.getInstance().EliminarVacuna(mivacuna);
								btneliminar.setEnabled(false);
								btnmodificar.setEnabled(false);
								listarVacunasPorTipo();
							}
						}
					}
				});
				btneliminar.setEnabled(false);
				if(!Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador"))
				{
					btneliminar.setVisible(false);
				}
				buttonPane.add(btneliminar);
			}
			{
				btnmodificar = new JButton("Modificar");
				if (Clinica.getLoginUser().getTipo().equalsIgnoreCase("Privilegiado")) {
					btnmodificar.setText("Visualizar");
				}
				btnmodificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador"))
						{
							RegistrarVacuna modificar = new RegistrarVacuna(mivacuna,false);
							modificar.setModal(true);
							modificar.setVisible(true);
							listarVacunasPorTipo();
						}else {
							RegistrarVacuna modificar = new RegistrarVacuna(mivacuna,true);
							modificar.setModal(true);
							modificar.setVisible(true);
							listarVacunasPorTipo();
						}
				
					}
				});
				btnmodificar.setEnabled(false);
				btnmodificar.setActionCommand("OK");
				buttonPane.add(btnmodificar);
				getRootPane().setDefaultButton(btnmodificar);
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
		listarVacunasPorTipo();
	}

	private void listarVacunasPorTipo() {
		String tipoSeleccionado = cmbtipo.getSelectedItem().toString();
		ArrayList<Vacuna> vacunas = Clinica.getInstance().getVacunasPorTipo(tipoSeleccionado);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Codigo");
		model.addColumn("Enfermedad");
		model.addColumn("Laboratorio");
		model.addColumn("Tipo");

		for (Vacuna vacuna : vacunas) {
			model.addRow(new Object[] { vacuna.getCodigo(), vacuna.getEnfermedad(), vacuna.getLaboratorio(),
					vacuna.getTipo() });
		}

		table.setModel(model);
	}

}
