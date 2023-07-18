package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class HistorialMedico implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String codigo;
	private ArrayList<String> misAlergias;
	private ArrayList<Enfermedad> misPadecimientos;
	private ArrayList<Vacuna> misVancunas;
	
	public HistorialMedico(String codigo, ArrayList<String> misAlergias, ArrayList<Enfermedad> misPadecimientos,
			ArrayList<Vacuna> misVancunas) {
		super();
		this.codigo = codigo;
		this.misAlergias = misAlergias;
		this.misPadecimientos = misPadecimientos;
		this.misVancunas = misVancunas;
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



}
