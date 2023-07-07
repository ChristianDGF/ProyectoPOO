package logico;

public class Empleado extends Persona {

	protected String cargo;
	protected String idempleado;
	protected Double sueldo;
	protected String departamento;
	
	public Empleado(String nombre, String direccion, String fechaNacimiento, String genero, String cedula,
			String telefono,String cargo,String idempleado,Double sueldo,String departamento) {
		super(nombre, direccion, fechaNacimiento, genero, cedula, telefono);
		this.cargo = cargo;
		this.idempleado = idempleado;
		this.sueldo = sueldo;
		this.departamento = departamento;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getIdempleado() {
		return idempleado;
	}

	public void setIdempleado(String idempleado) {
		this.idempleado = idempleado;
	}

	public Double getSueldo() {
		return sueldo;
	}

	public void setSueldo(Double sueldo) {
		this.sueldo = sueldo;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

}
