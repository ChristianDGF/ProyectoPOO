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
	
	public Paciente(String nombre,String apellido,String direccion, String fechaNacimiento, String genero, String cedula,
			String telefono,String correoelectronico,String estado,float peso,float altura,String tipoSangre,int edad) {
		super(nombre, apellido, direccion, fechaNacimiento, genero, cedula, telefono,correoelectronico);
		this.estado = estado;
		this.miHistorial = new HistorialMedico("MIHISTORIAL");
		this.peso = peso;
		this.altura = altura;
		this.tipoSangre = tipoSangre;
		this.edad = edad;
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

}
