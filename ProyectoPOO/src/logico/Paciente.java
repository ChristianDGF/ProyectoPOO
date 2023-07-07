package logico;

public class Paciente extends Persona {

	private String estado;
	//agregar historial medico 
	public Paciente(String nombre, String direccion, String fechaNacimiento, String genero, String cedula,
			String telefono,String estado) {
		super(nombre, direccion, fechaNacimiento, genero, cedula, telefono);
		this.setEstado(estado);
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
