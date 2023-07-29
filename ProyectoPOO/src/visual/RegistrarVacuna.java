package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;

import logico.Clinica;
import logico.Vacuna;

import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;

public class RegistrarVacuna extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtenfermedad;
	private JTextField txtcodigo;
	private JTextField txtlaboratorio;
	private JComboBox cmbtipo;
	private JTextPane textdescripcion;
	private Vacuna vacuna = null;
	private JButton okButton;

	public RegistrarVacuna(Vacuna mivacuna) {
		setResizable(false);
		vacuna = mivacuna;
		if (Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador")) {
			if (vacuna == null) {
				setTitle("Registrar Vacuna");
			} else {
				setTitle("Modificar Vacuna");
			}
		} else {
			setTitle("Visualizar Vacuna");
		}

		setBounds(100, 100, 580, 306);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
					JLabel lblenfermedad = new JLabel("Enfermedad:");
					lblenfermedad.setBounds(22, 25, 94, 16);
					contentPanel.add(lblenfermedad);
					lblenfermedad.setFont(new Font("Tahoma", Font.PLAIN, 12));
					
								txtenfermedad = new JTextField();
								txtenfermedad.setBounds(109, 22, 174, 22);
								contentPanel.add(txtenfermedad);
								txtenfermedad.setColumns(10);
								
											JLabel lblcodigo = new JLabel("Codigo:");
											lblcodigo.setBounds(297, 25, 56, 16);
											contentPanel.add(lblcodigo);
											lblcodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
											
														txtcodigo = new JTextField();
														txtcodigo.setBounds(365, 22, 174, 22);
														contentPanel.add(txtcodigo);
														txtcodigo.setColumns(10);
														
																	JLabel lbllaboratorio = new JLabel("Laboratorio:");
																	lbllaboratorio.setBounds(22, 61, 75, 16);
																	contentPanel.add(lbllaboratorio);
																	lbllaboratorio.setFont(new Font("Tahoma", Font.PLAIN, 12));
																	
																				txtlaboratorio = new JTextField();
																				txtlaboratorio.setBounds(109, 58, 174, 22);
																				contentPanel.add(txtlaboratorio);
																				txtlaboratorio.setColumns(10);
																				
																							JLabel lbltipo = new JLabel("Tipo:");
																							lbltipo.setBounds(297, 61, 56, 16);
																							contentPanel.add(lbltipo);
																							lbltipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
																							
																										cmbtipo = new JComboBox();
																										cmbtipo.setBounds(365, 58, 174, 22);
																										contentPanel.add(cmbtipo);
																										cmbtipo.setModel(new DefaultComboBoxModel(new String[] { "<Seleccionar>", "Vivas atenuadas", "Inactivadas",
																												"Toxoides", "Subunidades", "Vector recombinante", "Vacuna de ADN", "Vacuna de ARN" }));
																										
																													JLabel lbldescripcion = new JLabel("Descripcion:");
																													lbldescripcion.setBounds(22, 100, 88, 16);
																													contentPanel.add(lbldescripcion);
																													lbldescripcion.setFont(new Font("Tahoma", Font.PLAIN, 12));
																													
																																textdescripcion = new JTextPane();
																																textdescripcion.setBounds(109, 100, 430, 107);
																																contentPanel.add(textdescripcion);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Registrar");
				if (!Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador")) {
					okButton.setEnabled(false);
				}
				if (vacuna != null) {
					okButton.setText("Modificar");
				}
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (checkfield() && vacuna == null) {
							registrarVacuna();
						} else {
							modificarVacuna();
							JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Registro",
									JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
					}
				});

				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		cargarVacuna();
		if (!Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador")) {
			lockfields();
		}
	}

	private void lockfields() {
		txtenfermedad.setEditable(false);
		txtcodigo.setEditable(false);
		txtlaboratorio.setEditable(false);
		textdescripcion.setEditable(false);
		cmbtipo.setEnabled(false);
	}

	protected void modificarVacuna() {
		vacuna.setEnfermedad(txtenfermedad.getText());
		vacuna.setCodigo(txtcodigo.getText());
		vacuna.setLaboratorio(txtlaboratorio.getText());
		vacuna.setTipo(cmbtipo.getSelectedItem().toString());
		vacuna.setDescripcion(textdescripcion.getText());
	}

	private void cargarVacuna() {
		if (vacuna != null) {
			txtenfermedad.setText(vacuna.getEnfermedad());
			txtcodigo.setText(vacuna.getCodigo());
			txtlaboratorio.setText(vacuna.getLaboratorio());
			cmbtipo.setSelectedItem(vacuna.getTipo());
			textdescripcion.setText(vacuna.getDescripcion());
		}
	}

	private void registrarVacuna() {
		String enfermedad = txtenfermedad.getText();
		String codigo = txtcodigo.getText();
		String laboratorio = txtlaboratorio.getText();
		String tipo = cmbtipo.getSelectedItem().toString();
		String descripcion = textdescripcion.getText();

		Vacuna vacuna = new Vacuna(codigo, laboratorio, descripcion, enfermedad, tipo);
		Clinica.getInstance().AgregarVacuna(vacuna);

		JOptionPane.showMessageDialog(null, "Vacuna registrada correctamente", "Registro de Vacuna",
				JOptionPane.INFORMATION_MESSAGE);

		clean();
	}

	private void clean() {
		txtenfermedad.setText("");
		txtcodigo.setText("");
		txtlaboratorio.setText("");
		cmbtipo.setSelectedIndex(0);
		textdescripcion.setText("");
	}

	private boolean checkfield() {
		if (txtenfermedad.getText().isEmpty() || txtcodigo.getText().isEmpty() || txtlaboratorio.getText().isEmpty()
				|| cmbtipo.getSelectedIndex() == 0 || textdescripcion.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

}
