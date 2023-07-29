package logico;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Cita implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String codigo;
	private LocalDate fecha;
	private Persona persona;
	private Medico medico;
	private String estado;
	private LocalTime hora;
	public static int codigoCita = 1;
	
	public Cita(LocalDate fecha, Persona persona, Medico medico,LocalTime hora) {
		super();
		this.codigo = "CITA-N." + codigoCita;
		this.fecha = fecha;
		this.persona = persona;
		this.medico = medico;
		this.estado = "Pendiente";
		this.hora = hora;
		codigoCita++;
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

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public static void setCodigoCita(int codigoCita) {
		Cita.codigoCita = codigoCita;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}



}
