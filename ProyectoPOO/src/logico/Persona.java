package logico;

public class Persona {
	
	protected String nombre;
	protected String direccion;
	protected String fechaNacimiento;
	protected String genero;
	protected String cedula;
	protected String telefono;
    
	public Persona(String nombre, String direccion, String fechaNacimiento, String genero, String cedula,
			String telefono) 
	{
		super();
		this.setNombre(nombre);
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
		this.genero = genero;
		this.cedula = cedula;
		this.telefono = telefono;
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
}
