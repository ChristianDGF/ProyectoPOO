package logico;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Cita implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String codigo;
	private LocalDate fecha;
	private Paciente paciente;
	private Medico medico;
	
	public Cita(String codigo, LocalDate fecha, Paciente paciente, Medico medico) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.paciente = paciente;
		this.medico = medico;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
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


}
