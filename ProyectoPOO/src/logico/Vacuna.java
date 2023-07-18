package logico;

import java.io.Serializable;

public class Vacuna implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String laboratorio;
	private String descripcion;
	private String enfermedad;
	private String tipo;
	
	public Vacuna(String codigo, String laboratorio, String descripcion, String enfermedad,String tipo) {
		super();
		this.codigo = codigo;
		this.laboratorio = laboratorio;
		this.enfermedad = enfermedad;
		this.tipo = tipo;
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(String enfermedad) {
		this.enfermedad = enfermedad;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


}
