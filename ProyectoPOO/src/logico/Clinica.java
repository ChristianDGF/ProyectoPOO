package logico;

import java.util.ArrayList;

public class Clinica {
	
	private ArrayList<Persona> misPersonas;
	private ArrayList<Empleado> misEmpleados;
	private ArrayList<Medico> misMedicos;
	private ArrayList<Paciente> misPacientes;
	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<Consulta> misConsultas;
	private ArrayList<Cita> misCitas;
	private ArrayList<Estudio> misEstudios;
	private ArrayList<Vacuna> misVacunas;
	public static Clinica clinica = null;
	
	public Clinica() {
		super();
		this.misPersonas = new ArrayList<Persona>();
		this.misEmpleados = new ArrayList<Empleado>();
		this.misMedicos = new ArrayList<Medico>();
		this.misPacientes = new ArrayList<Paciente>();
		this.misEnfermedades = new ArrayList<Enfermedad>();
		this.misConsultas = new ArrayList<Consulta>();
		this.misCitas = new ArrayList<Cita>();
		this.misEstudios = new ArrayList<Estudio>();
		this.misVacunas = new ArrayList<Vacuna>();
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

	public ArrayList<Estudio> getMisEstudios() {
		return misEstudios;
	}

	public void setMisEstudios(ArrayList<Estudio> misEstudios) {
		this.misEstudios = misEstudios;
	}

	public ArrayList<Vacuna> getMisVacunas() {
		return misVacunas;
	}

	public void setMisVacunas(ArrayList<Vacuna> misVacunas) {
		this.misVacunas = misVacunas;
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
			Empleado aux = misMedicos.get(ind);
			if(aux.getIdempleado().equalsIgnoreCase(MedicoCode))
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
	
	



}
