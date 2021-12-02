package Logica;

import java.io.FileWriter;
import java.io.PrintWriter;
import Dominio.Asignatura;
import Dominio.Cuenta;
import Dominio.Cursada;
import Dominio.Estudiante;
import Dominio.Obligatoria;
import Dominio.Opcional;
import Dominio.Paralelo;
import Dominio.Profesor;

public class UniversitySystemImpl implements UniversitySystem {

	private ListaCuentas listaCuentas;
	private ListaAsignaturas listaAsignaturas;


	
	public UniversitySystemImpl() {
		
		this.listaCuentas = new ListaCuentas(9999);
		this.listaAsignaturas = new ListaAsignaturas(9999);
	}

	@Override
	public boolean ingresarProfesor(String rut, String correo, String contraseña, int saldo) {
		Profesor profe = new Profesor(rut, correo, contraseña, saldo);
		return listaCuentas.agregar(profe);
	}

	@Override
	public boolean ingresarAsignaturaObligatoria(String codigo, String nombre, int credito, int nivel) {
		if (listaAsignaturas.getAsignatura(codigo) != null) {
			Obligatoria ob = (Obligatoria) listaAsignaturas.getAsignatura(codigo);
			ob.setNombre(nombre);
			ob.setCreditos(credito);
			ob.setNivelRequerido(nivel);
			return true;
		}
		Obligatoria ob = new Obligatoria(nombre, codigo, credito, nivel);
		return listaAsignaturas.agregar(ob);
	}

	@Override
	public boolean ingresarAsignaturaOpcional(String codigo, String nombre, int credito, int creditoMin) {
		Opcional op = new Opcional(nombre, codigo, credito, creditoMin);
		return listaAsignaturas.agregar(op);
	}

	@Override
	public boolean setPreRequisito(String codigoRamoActual, String codigoRamoPre) {
		Obligatoria ramo = (Obligatoria) listaAsignaturas.getAsignatura(codigoRamoActual);
		Obligatoria prer = (Obligatoria) listaAsignaturas.getAsignatura(codigoRamoPre);
		if (prer == null) {
			prer = new Obligatoria("", codigoRamoPre, 0, 0);
			if (listaAsignaturas.agregar(prer) == false) {
				return false;
			}
		}
		return ramo.getPrerequisitos().agregar(prer);
	}

	@Override
	public boolean ingresarParalelo(int numeroParalelo, String codigo, String rutProfesor) {
		Paralelo para = new Paralelo(numeroParalelo);
		Asignatura asig = listaAsignaturas.getAsignatura(codigo);
		if (asig != null) {
			Profesor prof = (Profesor) listaCuentas.getCuentaRut(rutProfesor);
			if (prof != null) {
				if (asig.getParalelos().getCant() < 100) {
					if (prof.getParalelosAsignados().getCant() < 5) {
						prof.getParalelosAsignados().agregar(para);
						para.setProfesor(prof);
						para.setAsignatura(asig);
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
		Estudiante estudiante = new Estudiante(rut, correo, contraseña, 0, nivel);
		return listaCuentas.agregar(estudiante);
	}

	@Override
	public boolean ingresarAsignaturaCursada(String rut, String codigoAsignatura, double nota) {
		Asignatura asig = listaAsignaturas.getAsignatura(codigoAsignatura);
		Cursada curs = new Cursada(asig, nota);
		Estudiante est = (Estudiante) listaCuentas.getCuentaRut(rut);
		return est.getAsignaturasCursadas().agregar(curs);
	}

	@Override
	public boolean inscribirAsignatura(String rut, String codigo, int paralelo) {
		Asignatura asig = listaAsignaturas.getAsignatura(codigo);
		if (asig == null) {
			return false;
		}
		Paralelo para = asig.getParalelos().getParaleloNumero(paralelo);
		if (para == null && para.getEstudiantes().getCant() >= 100) {
			return false;
		}
		Estudiante est = (Estudiante) listaCuentas.getCuentaRut(rut);
		if (est == null && est.getCreditos() + para.getAsignatura().getCreditos() >= 40) {
			return false;
		}
		para.getEstudiantes().agregar(est);
		est.getAsignaturasActivas().agregar(para);
		est.setCreditos(est.getCreditos() + asig.getCreditos());
		return true;
	}

	@Override
	public boolean guardarEstudiantes(FileWriter file) {
		PrintWriter escritura = new PrintWriter(file);
		for (int i = 0; i < listaCuentas.getCant(); i++) {
			Cuenta cuenta = listaCuentas.getCuenta(i);
			if (cuenta instanceof Estudiante) {
				Estudiante e = (Estudiante) cuenta;
				escritura.println(e.getRut() + "," + e.getCorreo() + "," + e.getNivel() + "," + e.getContraseña());
				escritura.println(e.getAsignaturasCursadas().getCant());
				for (int a = 0; a < e.getAsignaturasCursadas().getCant(); a++) {
					escritura.println(e.getAsignaturasCursadas().getCursada(a).getAsignatura().getNombre() + ","
							+ e.getAsignaturasCursadas().getCursada(a).getNota());
				}
				escritura.println(e.getAsignaturasActivas().getCant());
				for (int a = 0; a < e.getAsignaturasActivas().getCant(); a++) {
					escritura.println(e.getAsignaturasActivas().getParalelo(a).getAsignatura().getCodigo() + ","
							+ e.getAsignaturasActivas().getParalelo(a).getNumero());
				}
			}
		}
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