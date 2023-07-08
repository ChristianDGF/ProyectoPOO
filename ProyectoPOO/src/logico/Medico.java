package logico;

import java.util.ArrayList;

public class Medico extends Empleado {

	private String especialidad;
	private String idcolegiomedico;
	private ArrayList<Consulta> misconsultas;
	
	public Medico(String nombre, String direccion, String fechaNacimiento, String genero, String cedula,
			String telefono, String cargo, String idempleado, Double sueldo, String departamento,String especialidad,String idcolegiomedico,
			ArrayList<Consulta> misconsultas) {
		
		super(nombre, direccion, fechaNacimiento, genero, cedula, telefono, cargo, idempleado, sueldo, departamento);
		this.especialidad = especialidad;
		this.idcolegiomedico = idcolegiomedico;
		this.misconsultas = misconsultas;
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

	public ArrayList<Consulta> getMisconsultas() {
		return misconsultas;
	}
}
