package logico;

import java.util.ArrayList;

public class Medico extends Empleado {

	private static final long serialVersionUID = 1L;
	private String especialidad;
	private String exequatur;
	private String numeroconsultorio;
	private ArrayList<Consulta> misconsultas;
	
	public Medico(String nombre, String apellido, String direccion, String fechaNacimiento, String genero,
			String cedula, String telefono,String correoelectronico, String cargo, String departamento,String especialidad,String exequatur,
			String numeroconsultorio) {
		super(nombre, apellido, direccion, fechaNacimiento, genero, cedula, telefono,correoelectronico, cargo, departamento);
		this.especialidad = especialidad;
		this.exequatur = exequatur;
		this.numeroconsultorio = numeroconsultorio;
		this.misconsultas = new ArrayList<Consulta>();
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}


	public ArrayList<Consulta> getMisconsultas() {
		return misconsultas;
	}

	public String getExequatur() {
		return exequatur;
	}

	public void setExequatur(String exequatur) {
		this.exequatur = exequatur;
	}

	public String getNumeroconsultorio() {
		return numeroconsultorio;
	}

	public void setNumeroconsultorio(String numeroconsultorio) {
		this.numeroconsultorio = numeroconsultorio;
	}

	public void setMisconsultas(ArrayList<Consulta> misconsultas) {
		this.misconsultas = misconsultas;
	}
}
