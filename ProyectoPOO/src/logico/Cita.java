package logico;

import java.io.Serializable;
import java.util.Date;

public class Cita implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String codigo;
	private Date fecha;
	private Paciente paciente;
	private Medico medico;
	private String motivoConsulta;
	
	public Cita(String codigo, Date fecha, Paciente paciente, Medico medico, String motivoConsulta) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.paciente = paciente;
		this.medico = medico;
		this.motivoConsulta = motivoConsulta;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public String getMotivoConsulta() {
		return motivoConsulta;
	}

	public void setMotivoConsulta(String motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}

}
