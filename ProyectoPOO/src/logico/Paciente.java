package logico;

import java.util.ArrayList;

public class Paciente extends Persona {

	private static final long serialVersionUID = 1L;
	private String estado;
	private HistorialMedico miHistorial;
	private float peso;
	private float altura;
	private String tipoSangre;
	private int edad;
	private static int codigoPaciente = 1;
	
	public Paciente(String nombre,String apellido,String direccion, String fechaNacimiento, String genero, String cedula,
			String telefono,String correoelectronico,String estado,float peso,float altura,String tipoSangre,int edad) {
		super(nombre, apellido, direccion, fechaNacimiento, genero, cedula, telefono,correoelectronico);
		this.estado = "Sano";
		this.miHistorial = new HistorialMedico();
		this.peso = peso;
		this.altura = altura;
		this.tipoSangre = tipoSangre;
		this.edad = edad;
		this.codigo = "PACIENTE-" + codigoPaciente;
		codigoPaciente++;
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public HistorialMedico getMiHistorial() {
		return miHistorial;
	}

	public void setMiHistorial(HistorialMedico miHistorial) {
		this.miHistorial = miHistorial;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public String getTipoSangre() {
		return tipoSangre;
	}

	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public static void setCodigoPaciente(int codigoPaciente) {
		Paciente.codigoPaciente = codigoPaciente;
	}

}
