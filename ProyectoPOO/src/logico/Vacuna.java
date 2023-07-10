package logico;

public class Vacuna {
	
	private String codigo;
	private String laboratorio;
	private String descripcion;
	
	public Vacuna(String codigo, String laboratorio, String descripcion) {
		super();
		this.codigo = codigo;
		this.laboratorio = laboratorio;
		this.setDescripcion(descripcion);
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


}
