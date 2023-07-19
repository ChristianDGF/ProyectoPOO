package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Clinica implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Persona> misPersonas;
	private ArrayList<Empleado> misEmpleados;
	private ArrayList<Medico> misMedicos;
	private ArrayList<Paciente> misPacientes;
	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<Consulta> misConsultas;
	private ArrayList<Cita> misCitas;
	private ArrayList<Vacuna> misVacunas;
	private ArrayList<User>misUsuarios;
	public static Clinica clinica ;
	private static User loginUser;
	public static int codigoCita = 1;
	
	public Clinica() {
		super();
		this.misPersonas = new ArrayList<Persona>();
		this.misEmpleados = new ArrayList<Empleado>();
		this.misMedicos = new ArrayList<Medico>();
		this.misPacientes = new ArrayList<Paciente>();
		this.misEnfermedades = new ArrayList<Enfermedad>();
		this.misConsultas = new ArrayList<Consulta>();
		this.misCitas = new ArrayList<Cita>();
		this.misVacunas = new ArrayList<Vacuna>();
		this.misUsuarios = new ArrayList<User>();
	}
	
	public static Clinica getInstance()
	{
		if(clinica == null)
		{
			clinica = new Clinica();
		}
		
		return clinica;
	}

	public ArrayList<Persona> getMisPersonas() {
		return misPersonas;
	}

	public void setMisPersonas(ArrayList<Persona> misPersonas) {
		this.misPersonas = misPersonas;
	}

	public ArrayList<Empleado> getMisEmpleados() {
		return misEmpleados;
	}

	public void setMisEmpleados(ArrayList<Empleado> misEmpleados) {
		this.misEmpleados = misEmpleados;
	}

	public ArrayList<Medico> getMisMedicos() {
		return misMedicos;
	}

	public void setMisMedicos(ArrayList<Medico> misMedicos) {
		this.misMedicos = misMedicos;
	}

	public ArrayList<Paciente> getMisPacientes() {
		return misPacientes;
	}

	public void setMisPacientes(ArrayList<Paciente> misPacientes) {
		this.misPacientes = misPacientes;
	}

	public ArrayList<Enfermedad> getMisEnfermedades() {
		return misEnfermedades;
	}

	public void setMisEnfermedades(ArrayList<Enfermedad> misEnfermedades) {
		this.misEnfermedades = misEnfermedades;
	}

	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}

	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}

	public ArrayList<Cita> getMisCitas() {
		return misCitas;
	}

	public void setMisCitas(ArrayList<Cita> misCitas) {
		this.misCitas = misCitas;
	}

	public ArrayList<Vacuna> getMisVacunas() {
		return misVacunas;
	}

	public void setMisVacunas(ArrayList<Vacuna> misVacunas) {
		this.misVacunas = misVacunas;
	}
	
	
	public static void setClinica(Clinica clinica) {
		Clinica.clinica = clinica;
	}

	public ArrayList<User> getMisUsuarios() {
		return misUsuarios;
	}

	public void setMisUsuarios(ArrayList<User> misUsuarios) {
		this.misUsuarios = misUsuarios;
	}
	
	public static User getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(User loginUser) {
		Clinica.loginUser = loginUser;
	}
	
	public Paciente getPacienteByCedula(String PacienteCedula)
	{
		Paciente temp = null;
		boolean encontrado = false;
		int ind = 0;
		
		while(!encontrado && ind < misPacientes.size()) {
			if(misPacientes.get(ind).getCedula().equalsIgnoreCase(PacienteCedula))
			{
				temp = misPacientes.get(ind);
				encontrado = true;
			}	    
			ind++;
		}
		return temp;
		
	}
	
	public Medico getMedicoByCode(String MedicoCode)
	{
		Medico temp = null;
		boolean encontrado = false;
		int ind = 0;
		
		while(!encontrado && ind < misMedicos.size()) {
			if(misMedicos.get(ind).getExequatur().equalsIgnoreCase(MedicoCode))
			{
				temp = misMedicos.get(ind);
				encontrado = true;
			}	    
			ind++;
		}
		return temp;
		
	}
	
	public Vacuna getVacunaByCode(String VacunaCode)
	{
		Vacuna temp = null;
		boolean encontrado = false;
		int ind = 0;
		
		while(!encontrado && ind < misVacunas.size()) {
			if(misVacunas.get(ind).getCodigo().equalsIgnoreCase(VacunaCode))
			{
				temp = misVacunas.get(ind);
				encontrado = true;
			}	    
			ind++;
		}
		return temp;
		
	}
	
	public Cita getCitaByCode(String CitaCode)
	{
		Cita temp = null;
		boolean encontrado = false;
		int ind = 0;
		
		while(!encontrado && ind < misCitas.size()) {
			if(misCitas.get(ind).getCodigo().equalsIgnoreCase(CitaCode))
			{
				temp = misCitas.get(ind);
				encontrado = true;
			}	    
			ind++;
		}
		return temp;
		
	}
	
	public Cita getCitaByPaciente(Paciente paciente)
	{
		Cita temp = null;
		boolean encontrado = false;
		int ind = 0;
		
		while(!encontrado && ind < misCitas.size()) {
			if(misCitas.get(ind).getPaciente().equals(paciente))
			{
				temp = misCitas.get(ind);
				encontrado = true;
			}	    
			ind++;
		}
		return temp;
		
	}
	
	public Cita getCitaByMedico(Medico medico)
	{
		Cita temp = null;
		boolean encontrado = false;
		int ind = 0;
		
		while(!encontrado && ind < misCitas.size()) {
			if(misCitas.get(ind).getMedico().equals(medico))
			{
				temp = misCitas.get(ind);
				encontrado = true;
			}	    
			ind++;
		}
		return temp;
		
	}
	
	public Consulta getConsultaByCode(String ConsultaCode)
	{
		Consulta temp = null;
		boolean encontrado = false;
		int ind = 0;
		
		while(!encontrado && ind < misConsultas.size()) {
			if(misConsultas.get(ind).getCodigo().equalsIgnoreCase(ConsultaCode))
			{
				temp = misConsultas.get(ind);
				encontrado = true;
			}	    
			ind++;
		}
		return temp;
		
	}
	
	public Consulta getConsultaByPaciente(Paciente paciente)
	{
		Consulta temp = null;
		boolean encontrado = false;
		int ind = 0;
		
		while(!encontrado && ind < misConsultas.size()) {
			if(misConsultas.get(ind).getCita().getPaciente().equals(paciente))
			{
				temp = misConsultas.get(ind);
				encontrado = true;
			}	    
			ind++;
		}
		return temp;
		
	}
	
	public Consulta getConsultaByMedico(Medico medico)
	{
		Consulta temp = null;
		boolean encontrado = false;
		int ind = 0;
		
		while(!encontrado && ind < misConsultas.size()) {
			if(misConsultas.get(ind).getCita().getMedico().equals(medico))
			{
				temp = misConsultas.get(ind);
				encontrado = true;
			}	    
			ind++;
		}
		return temp;
		
	}
	
	public Enfermedad getEnfermedadByCode(String EnfermedadCode)
	{
		Enfermedad temp = null;
		boolean encontrado = false;
		int ind = 0;
		
		while(!encontrado && ind < misEnfermedades.size()) {
			if(misEnfermedades.get(ind).getCodigo().equalsIgnoreCase(EnfermedadCode))
			{
				temp = misEnfermedades.get(ind);
				encontrado = true;
			}	    
			ind++;
		}
		return temp;
		
	}
	
	
	public Enfermedad getEnfermedadByStatusVigilancia(String StatusVigilancia)
	{
		Enfermedad temp = null;
		boolean encontrado = false;
		int ind = 0;
		
		while(!encontrado && ind < misEnfermedades.size()) {
			if(misEnfermedades.get(ind).getEstado().equalsIgnoreCase(StatusVigilancia))
			{
				temp = misEnfermedades.get(ind);
				encontrado = true;
			}	    
			ind++;
		}
		return temp;
		
	}
	
	public ArrayList<Empleado> getEmpleadosPorCargo(String cargo) {
	    ArrayList<Empleado> empleadosPorCargo = new ArrayList<>();

	    if (cargo.equalsIgnoreCase("<Todos>")) {
	        return getMisEmpleados();
	    }

	    for (Empleado empleado : misEmpleados) {
	        if (empleado.getCargo().equalsIgnoreCase(cargo)) {
	            empleadosPorCargo.add(empleado);
	        }
	    }

	    return empleadosPorCargo;
	}

	public ArrayList<Enfermedad> obtenerEnfermedadesPorEstado(String estado) {
	    ArrayList<Enfermedad> enfermedadesFiltradas = new ArrayList<>();

	    ArrayList<Enfermedad> todasLasEnfermedades = getMisEnfermedades();

	    for (Enfermedad enfermedad : todasLasEnfermedades) {
	        if (enfermedad.getEstado().equalsIgnoreCase(estado)) {
	            enfermedadesFiltradas.add(enfermedad);
	        }
	        if(estado.equalsIgnoreCase("<Todos>"))
	        {
	        	enfermedadesFiltradas = getMisEnfermedades();
	        }
	    }

	    return enfermedadesFiltradas;
	}
	
	public ArrayList<Medico> obtenerMedicosPorEspecialidad(String especialidad) {
        ArrayList<Medico> medicosPorEspecialidad = new ArrayList<>();

        for (Medico medico : getMisMedicos()) {
            if (medico.getEspecialidad().equalsIgnoreCase(especialidad)) {
                medicosPorEspecialidad.add(medico);
            }
            if(especialidad.equalsIgnoreCase("<Todas>"))
            {
            	medicosPorEspecialidad = getMisMedicos();
            }
        }

        return medicosPorEspecialidad;
    }
	
	public ArrayList<Vacuna> getVacunasPorTipo(String tipo) {
	    ArrayList<Vacuna> vacunasPorTipo = new ArrayList<>();

	    for (Vacuna vacuna : misVacunas) {
	        if (vacuna.getTipo().equals(tipo)) {
	            vacunasPorTipo.add(vacuna);
	        }
	        if(tipo.equalsIgnoreCase("<Todos>"))
            {
	        	vacunasPorTipo = getMisVacunas();
            }
	    }

	    return vacunasPorTipo;
	}

	
	public void AgregarVacuna(Vacuna vacuna)
	{
		misVacunas.add(vacuna);
	}
	public void EliminarVacuna(Vacuna vacuna)
	{
		misVacunas.remove(vacuna);
	}
	
	public void AgregarPaciente(Paciente paciente)
	{
		misPacientes.add(paciente);
	}
	public void EliminarPaciente(Paciente paciente)
	{
		misPacientes.remove(paciente);
	}
	
	public void AgregarEnfermedad(Enfermedad enfermedad)
	{
		misEnfermedades.add(enfermedad);
	}
	public void EliminarEnfermedad(Enfermedad enfermedad)
	{
		misEnfermedades.remove(enfermedad);
	}
	
	public void AgregarCita(Cita cita)
	{
		misCitas.add(cita);
	}
	public void EliminarCita(Cita cita)
	{
		misCitas.remove(cita);
	}
	
	public void AgregarConsulta(Consulta consulta)
	{
		misConsultas.add(consulta);
	}
	public void EliminarConsulta(Consulta consulta)
	{
		misConsultas.remove(consulta);
	}
	
	public void AgregarEmpleado(Empleado empleado)
	{
		misEmpleados.add(empleado);
	}
	public void EliminarEmpleado(Empleado empleado)
	{
		misEmpleados.remove(empleado);
	}
	
	public void AgregarMedico(Medico medico)
	{
		misMedicos.add(medico);
	}
	public void EliminarMedico(Medico medico)
	{
		misMedicos.remove(medico);
	}
	
	public void AgregarUser(User user)
	{
		misUsuarios.add(user);
	}
	public void EliminarUser(User user)
	{
		misUsuarios.remove(user);
	}

	public void ActualizarPaciente(Paciente miPaciente) {
		int index = BuscarIndexByCodePaciente(miPaciente.getCedula());
		misPacientes.set(index, miPaciente);
		
	}

	public int BuscarIndexByCodePaciente(String cedula) {
		int aux = -1;
		boolean encontrado = false;
		int ind = 0;
		while (ind < misPacientes.size() && !encontrado) {
			if (misPacientes.get(ind).getCedula().equalsIgnoreCase(cedula)) {
				encontrado = true;
				aux = ind;
			}
			ind++;
		}
		return aux;
	}
	
	public Paciente BuscarPacienteByCedula(String cedula)
	{
		Paciente temp = null;
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < misPacientes.size()) {
			if (misPacientes.get(i).getCedula().equalsIgnoreCase(cedula)) {
				temp = misPacientes.get(i);
				encontrado = true;
			}
			i++;
		}

		return temp;
	}

	public Medico BuscarMedicoByExequatur(String Exequatur) {
		Medico temp = null;
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < misMedicos.size()) {
			if (misMedicos.get(i).getExequatur().equalsIgnoreCase(Exequatur)) {
				temp = misMedicos.get(i);
				encontrado = true;
			}
			i++;
		}

		return temp;
	}
	public boolean confirmLogin(String usuario, String password) {
		boolean login = false;
		for (User user : misUsuarios) {
			if(user.getUsuario().equals(usuario) && user.getPassword().equals(password)){
				loginUser = user;
				login = true;
			}
		}
		return login;
	}

	public Empleado getEmpleadoByCode(String string) {
	    Empleado aux = null;
	    boolean find = false;
	    int i = 0;
	    while (i < misEmpleados.size() && !find) {
	        Empleado empleado = misEmpleados.get(i);
	        if (empleado != null && empleado.getCodigo() != null && empleado.getCodigo().equalsIgnoreCase(string)) {
	            find = true;
	            aux = empleado;
	        }
	        i++;
	    }
	    return aux;
	}

}
