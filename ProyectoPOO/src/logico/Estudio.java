package logico;

public class Estudio {
	
	private String codigo;
	private String tipo;
	private String observaciones;
	private String resultados;
	
	public Estudio(String codigo, String tipo, String observaciones, String resultados) {
		super();
		this.codigo = codigo;
		this.tipo = tipo;
		this.observaciones = observaciones;
		this.resultados = resultados;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getResultados() {
		return resultados;
	}

	public void setResultados(String resultados) {
		this.resultados = resultados;
	}

}
