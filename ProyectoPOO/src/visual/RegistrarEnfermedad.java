package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

import logico.Clinica;
import logico.Enfermedad;

import javax.swing.DefaultComboBoxModel;

public class RegistrarEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtnombre;
	private JTextField txttipo;
	private JTextField txtcodigo;
	private JTextPane txtdescripcion;
	private JComboBox cmbestado;

	public RegistrarEnfermedad() {
		setTitle("Registrar Enfermedad");
		setBounds(100, 100, 576, 288);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(null);
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblnombre = new JLabel("Nombre:");
				lblnombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblnombre.setBounds(12, 18, 94, 16);
				panel.add(lblnombre);
			}
			{
				txtnombre = new JTextField();
				txtnombre.setColumns(10);
				txtnombre.setBounds(101, 15, 174, 22);
				panel.add(txtnombre);
			}
			{
				JLabel lbltipo = new JLabel("Tipo:");
				lbltipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lbltipo.setBounds(12, 53, 75, 16);
				panel.add(lbltipo);
			}
			{
				txttipo = new JTextField();
				txttipo.setColumns(10);
				txttipo.setBounds(101, 50, 174, 22);
				panel.add(txttipo);
			}
			{
				JLabel lblcodigo = new JLabel("Codigo:");
				lblcodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblcodigo.setBounds(287, 18, 56, 16);
				panel.add(lblcodigo);
			}
			{
				txtcodigo = new JTextField();
				txtcodigo.setColumns(10);
				txtcodigo.setBounds(355, 15, 174, 22);
				panel.add(txtcodigo);
			}
			{
				cmbestado = new JComboBox();
				cmbestado.setModel(new DefaultComboBoxModel(new String[] {"<Seleccionar>", "Vigilancia"}));
				cmbestado.setBounds(355, 50, 174, 22);
				panel.add(cmbestado);
			}
			{
				JLabel lblestado = new JLabel("Estado:");
				lblestado.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblestado.setBounds(287, 53, 56, 16);
				panel.add(lblestado);
			}
			{
				JLabel label = new JLabel("Descripcion:");
				label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				label.setBounds(12, 91, 88, 16);
				panel.add(label);
			}
			{
				txtdescripcion = new JTextPane();
				txtdescripcion.setBounds(99, 91, 430, 91);
				panel.add(txtdescripcion);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnregistrar = new JButton("Registrar");
				btnregistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(checkfield())
						{
						  registrarEnfermedad();
						}
					}
				});
				btnregistrar.setActionCommand("OK");
				buttonPane.add(btnregistrar);
				getRootPane().setDefaultButton(btnregistrar);
			}
			{
				JButton btncancelar = new JButton("Cancelar");
				btncancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btncancelar.setActionCommand("Cancel");
				buttonPane.add(btncancelar);
			}
		}
	}

	
	private void registrarEnfermedad() {
	    String codigo = txtcodigo.getText();
	    String nombre = txtnombre.getText();
	    String descripcion = txtdescripcion.getText();
	    String tipo = txttipo.getText();
	    String estado = cmbestado.getSelectedItem().toString();

	    Enfermedad enfermedad = new Enfermedad(codigo, nombre, descripcion, tipo, estado);
	    
	    Clinica.getInstance().AgregarEnfermedad(enfermedad);

	    JOptionPane.showMessageDialog(null, "Enfermedad registrada correctamente", "Registro",
	            JOptionPane.INFORMATION_MESSAGE);
	    clean();
	}

	private void clean() {
	    txtcodigo.setText("");
	    txtnombre.setText("");
	    txtdescripcion.setText("");
	    txttipo.setText("");
	    cmbestado.setSelectedIndex(0);
	}
	private boolean checkfield() {
	    if (txtnombre.getText().isEmpty() || txttipo.getText().isEmpty() || txtcodigo.getText().isEmpty()
	            || cmbestado.getSelectedIndex() == 0 || txtdescripcion.getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Debe completar todos los campos obligatorios", "Error",
	                JOptionPane.INFORMATION_MESSAGE);
	        return false;
	    }
	    return true;
	}


}
