package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class HistorialMedico implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String codigo;
	private ArrayList<String> misAlergias;
	private ArrayList<Enfermedad> misPadecimientos;
	private ArrayList<Vacuna> misVancunas;
	private ArrayList<Consulta> misConsultas;
	
	public HistorialMedico(String codigo, ArrayList<String> misAlergias, ArrayList<Enfermedad> misPadecimientos,
			ArrayList<Vacuna> misVancunas) {
		super();
		this.codigo = codigo;
		this.misAlergias = new ArrayList<String>();
		this.misPadecimientos = new ArrayList<Enfermedad>();
		this.misVancunas = new ArrayList<Vacuna>();
		this.misConsultas = new ArrayList<Consulta>();
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public ArrayList<String> getMisAlergias() {
		return misAlergias;
	}
	public void setMisAlergias(ArrayList<String> misAlergias) {
		this.misAlergias = misAlergias;
	}
	public ArrayList<Enfermedad> getMisPadecimientos() {
		return misPadecimientos;
	}
	public void setMisPadecimientos(ArrayList<Enfermedad> misPadecimientos) {
		this.misPadecimientos = misPadecimientos;
	}
	public ArrayList<Vacuna> getMisVancunas() {
		return misVancunas;
	}
	public void setMisVancunas(ArrayList<Vacuna> misVancunas) {
		this.misVancunas = misVancunas;
	}

	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}

	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}



}
