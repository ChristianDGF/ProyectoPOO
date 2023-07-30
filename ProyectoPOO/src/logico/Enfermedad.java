package logico;

import java.io.Serializable;

public class Enfermedad implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigo;
	private String nombre;
	private String descripcion;
	private String tipo;
	private String estado;
	public static int codigoEnfermedad = 1;

	public Enfermedad(String codigo, String nombre, String descripcion, String tipo, String estado) {
		super();
		this.codigo = "ENFERMEDAD-" + codigoEnfermedad;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.estado = estado;
		codigoEnfermedad++;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public static void setCodigoEnfermedad(int codigoEnfermedad) {
		Enfermedad.codigoEnfermedad = codigoEnfermedad;
	}

}
