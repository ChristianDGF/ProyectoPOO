package logico;

public class Consulta {
	private String codigo;
	private Cita cita;
	private Diagnostico diagnostico;
	private String estado;
	
	public Consulta(String codigo, Cita cita, Diagnostico diagnostico, String estado) {
		super();
		this.codigo = codigo;
		this.cita = cita;
		this.diagnostico = diagnostico;
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

	public Diagnostico getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
