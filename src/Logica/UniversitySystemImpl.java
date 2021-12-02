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
	public int iniciarSesion(String correo, String contraseña) {
		if (correo.equals("Admin") && contraseña.equals("GHI_789")) {
			return 2;
		}
		Cuenta cuenta = listaCuentas.getCuenta(correo, contraseña);
		if (cuenta == null) {
			return -1;
		}
		if (cuenta instanceof Estudiante) {
			return 0;
		}
		if (cuenta instanceof Profesor) {
			return 1;
		}
		return -1;
	}

	@Override
	public void ingresarFecha(String fecha) {
		// TODO Auto-generated method stub

	}

	@Override
	public void desplegarAsignaturasInscribir(String correo) {
		Estudiante est = (Estudiante) listaCuentas.getCuentaCorreo(correo);
		System.out.println("Creditos: "+est.getCreditos()+"/40");
		System.out.println(" - Lista de asiganturas obligatorias nuevas: ");
		for(int i = 0;i<listaAsignaturas.getCant();i++) {
			Asignatura asig = listaAsignaturas.getAsignatura(i);
			if(asig instanceof Obligatoria) {
				Obligatoria asigOb = (Obligatoria) asig;
				if(est.getNivel()>=asigOb.getNivelRequerido()) {
					if(est.getAsignaturasCursadas().getCursada(asig)==null) {
						System.out.println("	- "+asig.getNombre()+" Codigo: "+asig.getCodigo()+" Creditos: "+asig.getCreditos());
					}
				}
			}
		}
		System.out.println(" - Lista de asiganturas opcionales nuevas: ");
		for(int i = 0;i<listaAsignaturas.getCant();i++) {
			Asignatura asig = listaAsignaturas.getAsignatura(i);
			if(asig instanceof Opcional) {
				if(est.getAsignaturasCursadas().getCursada(asig)==null) {
					System.out.println("	- "+asig.getNombre()+" Codigo: "+asig.getCodigo()+" Creditos: "+asig.getCreditos());
				}
			}
		}
		System.out.println(" - Lista de asiganturas repetidas: ");
		for(int i =0;i<est.getAsignaturasCursadas().getCant();i++) {
			if(est.getAsignaturasCursadas().getCursada(i).isAprobada()==false) {
				Asignatura asig = est.getAsignaturasCursadas().getCursada(i).getAsignatura();
				System.out.println("	- "+asig.getNombre()+" Codigo: "+asig.getCodigo()+" Creditos: "+asig.getCreditos());
			}
		}
	}

	@Override
	public void desplegarParalelosAsignatura(String codigo) {
		Asignatura asig = listaAsignaturas.getAsignatura(codigo);
		for (int i = 0; i < asig.getParalelos().getCant(); i++) {
			System.out.println("Paralelo " + asig.getParalelos().getParalelo(i).getNumero() + " Profesor: "
					+ asig.getParalelos().getParalelo(i).getProfesor().getCorreo() + " Capacidad: "
					+ asig.getParalelos().getParalelo(i).getEstudiantes().getCant() + "/100");
		}
	}

	@Override
	public void desplegarAsignaturasInscritas(String correo) {
		Estudiante est = (Estudiante) listaCuentas.getCuentaCorreo(correo);
		for (int i = 0; i < est.getAsignaturasActivas().getCant(); i++) {
			System.out.println(est.getAsignaturasActivas().getParalelo(i).toString());
		}
	}

	@Override
	public boolean eliminarAsignatura(String correo, String codigo) {
		Estudiante est = (Estudiante) listaCuentas.getCuentaCorreo(correo);
		for (int i = 0; i < est.getAsignaturasActivas().getCant(); i++) {
			if (est.getAsignaturasActivas().getParalelo(i).getAsignatura().getCodigo().equals(codigo)) {
				est.setCreditos(
						est.getCreditos() - est.getAsignaturasActivas().getParalelo(i).getAsignatura().getCreditos());
				est.getAsignaturasActivas().getParalelo(i).getEstudiantes().eliminar(est);
				est.getAsignaturasActivas().eliminar(i);
			}
		}
		return false;
	}

	@Override
	public void desplegarParalelosProfesor(String correo) {
		Profesor prof = (Profesor) listaCuentas.getCuentaCorreo(correo);
		for (int i = 0; i < prof.getParalelosAsignados().getCant(); i++) {
			System.out.println(prof.getParalelosAsignados().getParalelo(i).getAsignatura().getNombre() + " Paralelo: "
					+ prof.getParalelosAsignados().getParalelo(i).getNumero());
		}
	}

	@Override
	public boolean introducirNotaAsignatura(String correoPro, String rutEst, double nota, String codigo, int paralelo) {
		Profesor prof = (Profesor) listaCuentas.getCuentaCorreo(correoPro);
		Estudiante est = (Estudiante) listaCuentas.getCuentaRut(rutEst);
		if (est == null) {
			return false;
		}
		Asignatura asig = listaAsignaturas.getAsignatura(codigo);
		if (asig == null) {
			return false;
		}
		Paralelo para = asig.getParalelos().getParaleloNumero(paralelo);
		if (para == null) {
			return false;
		}
		if (para.getProfesor().equals(prof) == false) {
			return false;
		}
		if (para.getEstudiantes().index(est) == -1) {
			return false;
		}
		Cursada cursada = new Cursada(asig, nota);
		est.getAsignaturasCursadas().agregar(cursada);
		est.getAsignaturasActivas().eliminar(para);
		return true;
	}

	@Override
	public boolean guardarEstudiantesEgresados(FileWriter file) {
		// TODO Auto-generated method stub
		return false;
	}

}