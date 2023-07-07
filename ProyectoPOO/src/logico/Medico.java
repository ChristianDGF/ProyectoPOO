package logico;


public class Medico extends Empleado {

	private String especialidad;
	private String idcolegiomedico;
	//agregar arraylist de consultas 
	
	public Medico(String nombre, String direccion, String fechaNacimiento, String genero, String cedula,
			String telefono, String cargo, String idempleado, Double sueldo, String departamento,String especialidad,String idcolegiomedico) {
		super(nombre, direccion, fechaNacimiento, genero, cedula, telefono, cargo, idempleado, sueldo, departamento);
		this.especialidad = especialidad;
		this.idcolegiomedico = idcolegiomedico;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getIdcolegiomedico() {
		return idcolegiomedico;
	}

	public void setIdcolegiomedico(String idcolegiomedico) {
		this.idcolegiomedico = idcolegiomedico;
	}

}
