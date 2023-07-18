package logico;

public class Empleado extends Persona {

	private static final long serialVersionUID = 1L;
	protected String cargo;
	protected String departamento; 
	
	public Empleado(String nombre, String apellido, String direccion, String fechaNacimiento, String genero,
			String cedula, String telefono,String correoelectronico,String cargo,String departamento)
	{
		super(nombre, apellido, direccion, fechaNacimiento, genero, cedula, telefono,correoelectronico);
		this.cargo = cargo;
		this.departamento = departamento;
	}
	
	

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

}
