package logico;

import java.io.Serializable;

public class Consulta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String codigo;
	private Cita cita;
	private String sintomas;
	private String diagnostico;
	private Enfermedad enfermedad;
	private String estado;
	
	public Consulta(String codigo, Cita cita, String sintomas,String diagnostico
			,Enfermedad enfermedad,String estado) {
		super();
		this.codigo = codigo;
		this.cita = cita;
		this.sintomas = sintomas;
		this.diagnostico = diagnostico;
		this.enfermedad = enfermedad;	
		this.estado = estado;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Cita getCita() {
		return cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getSintomas() {
		return sintomas;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

}
