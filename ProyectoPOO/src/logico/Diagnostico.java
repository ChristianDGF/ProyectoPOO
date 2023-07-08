package logico;

import java.util.ArrayList;

public class Diagnostico {
	
	private String codigo;
	private String descripcion;
	private Enfermedad enfermedad;
	private ArrayList<Estudio>misEstudios;
	
	public Diagnostico(String codigo, String descripcion, Enfermedad enfermedad, ArrayList<Estudio> misEstudios) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.enfermedad = enfermedad;
		this.misEstudios = misEstudios;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

	public ArrayList<Estudio> getMisEstudios() {
		return misEstudios;
	}

	public void setMisEstudios(ArrayList<Estudio> misEstudios) {
		this.misEstudios = misEstudios;
	}

}
