package Logica;

import java.io.FileWriter;

import Dominio.Asignatura;
import Dominio.Obligatoria;
import Dominio.Opcional;
import Dominio.Paralelo;
import Dominio.Profesor;

public class UniversitySystemImpl implements UniversitySystem {
	
	private ListaCuentas listaCuentas;
	private ListaAsignaturas listaAsignaturas;
	
	@Override
	public boolean ingresarProfesor(String rut, String correo, String contraseña, int saldo) {
		Profesor profe = new Profesor(rut,correo,contraseña,saldo);
		return listaCuentas.agregar(profe);
	}

	@Override
	public boolean ingresarAsignaturaObligatoria(String codigo, String nombre, int credito, int nivel) {
		if(listaAsignaturas.getAsignatura(codigo)!=null) {
			Obligatoria ob = (Obligatoria)listaAsignaturas.getAsignatura(codigo);
			ob.setNombre(nombre);
			ob.setCreditos(credito);
			ob.setNivelRequerido(nivel);
			return true;
		}
		Obligatoria ob = new Obligatoria(nombre,codigo,credito,nivel);
		return listaAsignaturas.agregar(ob);
	}

	@Override
	public boolean ingresarAsignaturaOpcional(String codigo, String nombre, int credito, int creditoMin) {
		Opcional op = new Opcional(nombre,codigo,credito,creditoMin);
		return listaAsignaturas.agregar(op);
	}

	@Override
	public boolean setPreRequisito(String codigoRamoActual, String codigoRamoPre) {
		Obligatoria ramo = (Obligatoria) listaAsignaturas.getAsignatura(codigoRamoActual);
		Obligatoria prer = (Obligatoria) listaAsignaturas.getAsignatura(codigoRamoPre);
		if (prer == null){
			prer = new Obligatoria("",codigoRamoPre,0,0);
			if(listaAsignaturas.agregar(prer)==false) {
				return false;
			}
		}
		return ramo.getPrerequisitos().agregar(prer);
	}

	@Override
	public boolean ingresarParalelo(int numeroParalelo, String codigo, String rutProfesor) {
		Paralelo para = new Paralelo(numeroParalelo);
		Asignatura asig = listaAsignaturas.getAsignatura(codigo);
		if(asig != null) {
			Profesor prof = (Profesor) listaCuentas.getCuentaRut(rutProfesor);
			if(prof != null) {
				if(asig.getParalelos().getCant()<100) {
					if(prof.getParalelosAsignados().getCant()<5) {
						prof.getParalelosAsignados().agregar(para);
						para.setProfesor(prof);
						asig.getParalelos().agregar(para);
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean ingresarEstudiante(String rut, String correo, int nivel, String contraseña) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ingresarAsignaturaCursada(String correo, int codigoAsignatura, double nota) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean inscribirAsignatura(String correo, String codigo, int paralelo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean guardarEstudiantes(FileWriter file) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean iniciarSesion(String correo, String contraseña) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void ingresarFecha(String fecha) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desplegarAsignaturasInscribir(String correo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desplegarParalelosAsignatura(String codigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desplegarAsignaturasInscritas(String correo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean eliminarAsignatura(String correo, String codigo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void desplegarParalelosProfesor(String correo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean introducirNotaAsignatura(String correoPro, String rutEst, double nota, String codigo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean guardarEstudiantesEgresados(FileWriter file) {
		// TODO Auto-generated method stub
		return false;
	}



}