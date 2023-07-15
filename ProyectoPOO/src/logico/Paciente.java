package logico;

public class Paciente extends Persona {

	private String estado;
	private HistorialMedico miHistorial;
	
	public Paciente(String nombre,String apellido,String direccion, String fechaNacimiento, String genero, String cedula,
			String telefono,String correoelectronico,String estado,HistorialMedico historial) {
		super(nombre, apellido, direccion, fechaNacimiento, genero, cedula, telefono,correoelectronico);
		this.estado = estado;
		this.miHistorial = historial;
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public HistorialMedico getMiHistorial() {
		return miHistorial;
	}

	public void setMiHistorial(HistorialMedico miHistorial) {
		this.miHistorial = miHistorial;
	}

}
