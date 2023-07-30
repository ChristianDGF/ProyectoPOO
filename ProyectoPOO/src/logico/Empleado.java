package logico;

public class Empleado extends Persona {

	private static final long serialVersionUID = 1L;
	protected String cargo;
	protected String departamento;
	private static int codigoEmpleado = 1;

	public Empleado(String nombre, String apellido, String direccion, String fechaNacimiento, String genero,
			String cedula, String telefono, String correoelectronico, String cargo, String departamento) {
		super(nombre, apellido, direccion, fechaNacimiento, genero, cedula, telefono, correoelectronico);
		this.cargo = cargo;
		this.departamento = departamento;
		this.codigo = "EMPLEADO-" + codigoEmpleado;
		codigoEmpleado++;
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

	public static void setCodigoEmpleado(int codigoEmpleado) {
		Empleado.codigoEmpleado = codigoEmpleado;
	}

}
