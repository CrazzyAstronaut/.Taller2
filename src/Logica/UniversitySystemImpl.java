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
		Paralelo para = asig.getParalelos().getParaleloNumero(paralelo);
		Estudiante est = (Estudiante) listaCuentas.getCuentaRut(rut);
		if (para == null) {
			System.out.println("Paralelo no encontrado");
			return false;
		}
		if (para.getEstudiantes().getCant() >= 100) {
			System.out.println("Paralelo está lleno");
			return false;
		}
		para.getEstudiantes().agregar(est);
		est.getAsignaturasActivas().agregar(para);
		est.setCreditos(est.getCreditos() + asig.getCreditos());
		return true;
	}

	@Override
	public FileWriter guardarEstudiantes(FileWriter file) {
		PrintWriter escritura = new PrintWriter(file);
		for (int i = 0; i < listaCuentas.getCant(); i++) {
			Cuenta cuenta = listaCuentas.getCuenta(i);
			if (cuenta instanceof Estudiante) {
				Estudiante e = (Estudiante) cuenta;
				escritura.println(e.getRut() + "," + e.getCorreo() + "," + e.getNivel() + "," + e.getContraseña());
				escritura.println(e.getAsignaturasCursadas().getCant());
				for (int a = 0; a < e.getAsignaturasCursadas().getCant(); a++) {
					escritura.println(e.getAsignaturasCursadas().getCursada(a).getAsignatura().getCodigo() + ","
							+ e.getAsignaturasCursadas().getCursada(a).getNota());
				}
				escritura.println(e.getAsignaturasActivas().getCant());
				for (int a = 0; a < e.getAsignaturasActivas().getCant(); a++) {
					escritura.println(e.getAsignaturasActivas().getParalelo(a).getAsignatura().getCodigo() + ","
							+ e.getAsignaturasActivas().getParalelo(a).getNumero());
				}
			}
		}
		escritura.close();
		return file;
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
	public void desplegarAsignaturasInscribir(String correo) {
		Estudiante est = (Estudiante) listaCuentas.getCuentaCorreo(correo);
		System.out.println("Creditos: " + est.getCreditos() + "/40");
		System.out.println(" - Lista de asiganturas obligatorias nuevas: ");
		for (int i = 0; i < listaAsignaturas.getCant(); i++) {
			Asignatura asig = listaAsignaturas.getAsignatura(i);
			if (asig instanceof Obligatoria) {
				Obligatoria asigOb = (Obligatoria) asig;
				if (est.getNivel() >= asigOb.getNivelRequerido()) {
					if (est.getAsignaturasCursadas().getCursada(asig) == null
							&& est.getAsignaturasActivas().getParalelo(asig) == null) {
						System.out.println("	- " + asig.getNombre() + " Codigo: " + asig.getCodigo() + " Creditos: "
								+ asig.getCreditos());
					}
				}
			}
		}
		System.out.println(" - Lista de asiganturas opcionales nuevas: ");
		for (int i = 0; i < listaAsignaturas.getCant(); i++) {
			Asignatura asig = listaAsignaturas.getAsignatura(i);
			if (asig instanceof Opcional) {
				if (est.getAsignaturasCursadas().getCursada(asig) == null
						&& est.getAsignaturasActivas().getParalelo(asig) == null) {
					System.out.println("	- " + asig.getNombre() + " Codigo: " + asig.getCodigo() + " Creditos: "
							+ asig.getCreditos());
				}
			}
		}
		System.out.println(" - Lista de asiganturas repetidas: ");
		for (int i = 0; i < est.getAsignaturasCursadas().getCant(); i++) {
			if (est.getAsignaturasCursadas().getCursada(i).isAprobada() == false) {
				Asignatura asig = est.getAsignaturasCursadas().getCursada(i).getAsignatura();
				System.out.println("	- " + asig.getNombre() + " Codigo: " + asig.getCodigo() + " Creditos: "
						+ asig.getCreditos());
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
	public boolean desplegarAsignaturasInscritas(String correo) {
		Estudiante est = (Estudiante) listaCuentas.getCuentaCorreo(correo);
		if (est.getAsignaturasActivas().getCant() == 0) {
			System.out.println("No tiene asignaturas inscritas");
			return false;
		}
		for (int i = 0; i < est.getAsignaturasActivas().getCant(); i++) {
			System.out.println(est.getAsignaturasActivas().getParalelo(i).toString());
		}
		return true;
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
				return true;
			}
		}
		return false;
	}

	@Override
	public void desplegarParalelosProfesor(String correo) {
		Profesor prof = (Profesor) listaCuentas.getCuentaCorreo(correo);
		for (int i = 0; i < prof.getParalelosAsignados().getCant(); i++) {
			System.out.println(
					"[" + (i + 1) + "] " + prof.getParalelosAsignados().getParalelo(i).getAsignatura().getNombre()
							+ " Paralelo: " + prof.getParalelosAsignados().getParalelo(i).getNumero());
		}
	}

	@Override
	public boolean introducirNotaAsignatura(String correoPro, String rutEst, double nota, int indexParalelo) {
		Profesor prof = (Profesor) listaCuentas.getCuentaCorreo(correoPro);
		Estudiante est = (Estudiante) listaCuentas.getCuentaRut(rutEst);
		if (est == null) {
			System.out.println("Estudiante no encontrado");
			return false;
		}
		Paralelo para = prof.getParalelosAsignados().getParalelo(indexParalelo);
		if (para == null) {
			System.out.println("Paralelo no encontrado");
			return false;
		}
		if (para.getEstudiantes().index(est) == -1) {
			System.out.println("Estudiante no encontrado en el paralelo");
			return false;
		}
		Asignatura asig = para.getAsignatura();
		Cursada cursada = est.getAsignaturasCursadas().getCursada(asig);
		if (cursada == null) {
			cursada = new Cursada(asig, nota);
			est.getAsignaturasCursadas().agregar(cursada);
		} else {
			cursada.setNota(nota);
		}
		est.getAsignaturasActivas().eliminar(para);
		para.getEstudiantes().eliminar(est);
		est.setCreditos(est.getCreditos() - asig.getCreditos());
		return true;
	}

	@Override
	public FileWriter guardarEstudiantesEgresados(FileWriter file) {
		PrintWriter escritura = new PrintWriter(file);
		for (int i = 0; i < listaCuentas.getCant(); i++) {
			if (listaCuentas.getCuenta(i) instanceof Estudiante) {
				Estudiante est = (Estudiante) listaCuentas.getCuenta(i);
				if (est.getNivel() == 11) {
					escritura.println(est.getRut());
					listaCuentas.eliminar(i);
				}
			}
		}
		escritura.close();
		return file;
	}

	
	@Override
	public boolean prerequisitosAprovados(Estudiante est, Obligatoria obl) {
		ListaCursadas cursadas = est.getAsignaturasCursadas();
		ListaObligatorias prerequisitos = obl.getPrerequisitos();
		int cont = 0;
		for (int i = 0; i < prerequisitos.getCant(); i++) {
			Obligatoria requisito = prerequisitos.getObligatoria(i);
			for (int j = 0; j < cursadas.getCant(); j++) {
				Cursada cursada = cursadas.getCursada(j);
				if (cursada.getAsignatura() instanceof Obligatoria) {
					Obligatoria asigOblCursada = (Obligatoria) cursada.getAsignatura();
					if (requisito.equals(asigOblCursada) && cursada.isAprobada()) {
						cont++;
					}
				}
			}
		}
		if (cont == prerequisitos.getCant()) {
			return true;
		}
		return false;
	}

	@Override
	public String getRut(String correo) {
		if (correo.equals("Admin")) {
			return "Admin";
		}
		String rut = listaCuentas.getCuentaCorreo(correo).getRut();
		return rut;
	}

	@Override
	public boolean comprobarAsignatura(String correo, String codigo) {
		Estudiante est = (Estudiante) listaCuentas.getCuentaCorreo(correo);
		Asignatura asig = listaAsignaturas.getAsignatura(codigo);
		if (asig == null) {
			System.out.println("Asignatura no encontrada");
			return false;
		}
		if (asig instanceof Obligatoria) {
			Obligatoria obl = (Obligatoria) asig;
			if (est.getNivel() < obl.getNivelRequerido() && prerequisitosAprovados(est, obl) == false) {
				System.out.println("No cumple con los requerimientos");
				return false;
			}
		}
		if (asig instanceof Opcional) {
			Opcional opc = (Opcional) asig;
			if (est.getCreditos() < opc.getCreditosNecesarios()) {
				System.out.println("No cumple con los creditos de prerrequisitos");
				return false;
			}
		}
		if (est.getAsignaturasActivas().getParalelo(asig) != null) {
			System.out.println("Ya se esta cursando");
			return false;
		}
		if (est.getCreditos() + asig.getCreditos() > 40) {
			System.out.println("Sobrecarga de creditos");
			return false;
		}
		if (est.getAsignaturasCursadas().getCursada(asig) != null) {
			if (est.getAsignaturasCursadas().getCursada(asig).isAprobada() == true) {
				System.out.println("Ya ha cursado este curso");
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean desplegarEstudiantes(String correo, int pIndex) {
		pIndex--;
		Profesor prof = (Profesor) listaCuentas.getCuentaCorreo(correo);
		if (pIndex + 1 > prof.getParalelosAsignados().getCant() || pIndex < 0) {
			return false;
		}
		Paralelo para = prof.getParalelosAsignados().getParalelo(pIndex);
		System.out.println("Estudiantes de " + para.getAsignatura().getNombre() + " paralelo: " + para.getNumero());
		for (int i = 0; i < para.getEstudiantes().getCant(); i++) {
			Cuenta cuenta = para.getEstudiantes().getEstudiante(i);
			System.out.println("- " + cuenta.getCorreo() + " " + cuenta.getRut());
		}
		return true;
	}

	@Override
	public boolean comprobarAsignaturaCursando(String correo, String codigo) {
		Estudiante est = (Estudiante) listaCuentas.getCuentaCorreo(correo);
		Asignatura asig = listaAsignaturas.getAsignatura(codigo);
		if (est.getAsignaturasActivas().getParalelo(asig) != null) {
			return true;
		}
		return false;
	}

	@Override
	public void actualizarNivel(String rut) {
		Estudiante est = (Estudiante) listaCuentas.getCuentaRut(rut);
		int nivel = 1;
		while ((nivel == 11) == false) {
			for (int i = 0; i < listaAsignaturas.getCant(); i++) {
				if (listaAsignaturas.getAsignatura(i) instanceof Obligatoria) {
					Obligatoria obl = (Obligatoria) listaAsignaturas.getAsignatura(i);
					if (obl.getNivelRequerido() == nivel) {
						Cursada curs = est.getAsignaturasCursadas().getCursada(listaAsignaturas.getAsignatura(i));
						if (curs == null) {
							est.setNivel(nivel);
							System.out.println("Nivel nuevo :" + nivel);
							return;
						}
						if (curs.isAprobada() == false) {
							est.setNivel(nivel);
							System.out.println("Nivel nuevo :" + nivel);
							return;
						}
					}
				}
			}
			nivel++;
		}
		est.setNivel(nivel);
		System.out.println("Nivel nuevo :" + nivel);
		return;
	}

}