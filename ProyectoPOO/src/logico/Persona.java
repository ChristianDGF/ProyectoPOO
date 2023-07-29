package logico;

import java.io.Serializable;

public class Persona implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static int proximoID = 1;
	protected String nombre;
	protected String apellido;
	protected String direccion;
	protected String fechaNacimiento;
	protected String genero;
	protected String cedula; 
	protected String telefono;
	protected String correoelectronico;
	protected String codigo;
    
	public Persona(String nombre,String apellido,String direccion, String fechaNacimiento, String genero, String cedula,
			String telefono,String correoelectronico) 
	{
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
		this.genero = genero;
		this.cedula = cedula;
		this.telefono = telefono;
		this.correoelectronico = correoelectronico;
		this.codigo = "P" + proximoID;
		proximoID++;
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreoelectronico() {
		return correoelectronico;
	}

	public void setCorreoelectronico(String correoelectronico) {
		this.correoelectronico = correoelectronico;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public static void setProximoID(int proximoID) {
		Persona.proximoID = proximoID;
	}
}
