package logico;

import java.util.Date;

public class Vacuna {
	
	private String codigo;
	private String laboratorio;
	private Date fechaAdministracion;
	private String dosis;
	
	public Vacuna(String codigo, String laboratorio, Date fechaAdministracion, String dosis) {
		super();
		this.codigo = codigo;
		this.laboratorio = laboratorio;
		this.fechaAdministracion = fechaAdministracion;
		this.dosis = dosis;
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

	public Date getFechaAdministracion() {
		return fechaAdministracion;
	}

	public void setFechaAdministracion(Date fechaAdministracion) {
		this.fechaAdministracion = fechaAdministracion;
	}

	public String getDosis() {
		return dosis;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
	}

}
