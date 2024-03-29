package Logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.Scanner;
/**
 * 
 * @author Vicente Rojas
 * @author Claudio Cortes
 *
 */
public class App {

	private static Scanner s;
	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		UniversitySystem sistema = new UniversitySystemImpl();
		Lectura(sistema);
		s = new Scanner(System.in);
		boolean cerrar = false;
		while (cerrar == false) {
			System.out.println("Ingrese su correo: ");
			String correo = s.nextLine();
			System.out.println("Ingrese su contrase�a: ");
			String contrase�a = s.nextLine();
			int tipoCuenta = sistema.iniciarSesion(correo, contrase�a);
			while (tipoCuenta == -1) {
				System.out.println("No se pudo iniciar sesi�n, porfavor intentelo denuevo");
				System.out.println("Ingrese su correo: ");
				correo = s.nextLine();
				System.out.println("Ingrese su contrase�a: ");
				contrase�a = s.nextLine();
				tipoCuenta = sistema.iniciarSesion(correo, contrase�a);
			}
			String rut = sistema.getRut(correo);
			int menuSemestre = ingresarFecha();
			if (menuSemestre == 0) {
				sistema = new UniversitySystemImpl();
				Lectura(sistema);
				if (tipoCuenta == 0) {
					alumnoInicioSemestre(sistema, correo, rut);
				}
				if (tipoCuenta == 1) {
					profesorInicioSemestre(sistema, correo);
				}
				if (tipoCuenta == 2) {
					System.out.println("No hay Opciones Disponibles");
				}
				System.out.println("Guardando...");
				guardarEstudiantes(sistema);
			}

			if (menuSemestre == 1) {
				sistema = new UniversitySystemImpl();
				Lectura(sistema);
				if (tipoCuenta == 0) {
					alumnoMitadSemestre(sistema, correo);
				} else {
					System.out.println("No hay Opciones Disponibles");
				}
				System.out.println("Guardando...");
				guardarEstudiantes(sistema);
			}

			if (menuSemestre == 2) {
				sistema = new UniversitySystemImpl();
				Lectura(sistema);
				if (tipoCuenta == 1) {
					profesorFinalSemestre(sistema, correo);
				} else {
					System.out.println("No hay Opciones Disponibles");
				}
				System.out.println("Guardando...");
				guardarEstudiantes(sistema);
			}
			if (menuSemestre == 3) {
				if (tipoCuenta == 2) {
					cerrar = adminCierreSemestre(sistema);
				} else {
					System.out.println("No hay Opciones Disponibles");
				}
			}
			if (menuSemestre == 4) {
				System.out.println("Disfrute sus vacaciones");
			}
		}
		generarEgresados(sistema);
		guardarEstudiantes(sistema);
	}
	/**
	 * this function saves the graduated students in a text file
	 * @param sistema
	 * @throws IOException
	 */
	private static void generarEgresados(UniversitySystem sistema) throws IOException {
		FileWriter file = new FileWriter("egresados.txt");
		sistema.guardarEstudiantesEgresados(file);
	}
	/**
	 * this function saves student data to a text file
	 * @param sistema
	 * @throws IOException
	 */
	private static void guardarEstudiantes(UniversitySystem sistema) throws IOException {
		FileWriter file = new FileWriter("estudiantes.txt");
		sistema.guardarEstudiantes(file);
	}
	/**
	 * this function opens the administrator menu in the closing period of the semester
	 * @param sistema
	 * @return
	 */
	private static boolean adminCierreSemestre(UniversitySystem sistema) {
		System.out.println("Bienvenido administrador");
		System.out.println("[1]	Cerrar semestre	[0]	Salir");
		String o = s.nextLine();
		while ((o.equals("1")) == false && (o.equals("0")) == false) {
			System.out.println("Opcion invalida");
			o = s.nextLine();
		}
		while (o.equals("0") == false) {
			if (o.equals("1")) {
				System.out.println("El sistema se cerrar�...");
				return true;
			}
			System.out.println("[1]	Cerrar semestre	[0]	Salir");
			o = s.nextLine();
			while ((o.equals("1")) == false && (o.equals("0")) == false) {
				System.out.println("Opcion invalida");
				o = s.nextLine();
			}
		}
		return false;
	}
	/**
	 * this function opens the end of semester menu for teachers
	 * @param sistema
	 * @param correo
	 */
	private static void profesorFinalSemestre(UniversitySystem sistema, String correo) {
		System.out.println("Bienvenido al final de semestre profesor");
		System.out.println("[1]	Ingresar notas	[0]	Salir");
		String o = s.nextLine();
		while ((o.equals("1")) == false && (o.equals("0")) == false) {
			System.out.println("Opcion invalida");
			o = s.nextLine();
		}
		while (o.equals("0") == false) {
			if (o.equals("1")) {
				ingresarNotas(sistema, correo);
			}
			System.out.println("[1]	Ingresar notas	[0]	Salir");
			o = s.nextLine();
			while ((o.equals("1")) == false && (o.equals("0")) == false) {
				System.out.println("Opcion invalida");
				o = s.nextLine();
			}
		}
	}
	/**
	 * this function allows you to enter the notes to a subject that a student is taking
	 * @param sistema
	 * @param correo
	 */
	private static void ingresarNotas(UniversitySystem sistema, String correo) {
		int paralelo = verAlumnos(sistema, correo);
		if (paralelo == -1) {
			return;
		}
		System.out.println("Ingrese el rut del alumno al que desea evaluar: ");
		String rut = s.nextLine();
		if (rut.equals("0")) {
			return;
		}
		System.out.println("Ingrese la nota: ");
		String nota = s.nextLine();
		if (nota.equals("0")) {
			return;
		}
		double n = escribirNota(nota);
		while (n < 1 || n > 7) {
			System.out.println("Intentelo denuevo: ");
			nota = s.nextLine();
			n = escribirNota(nota);
		}
		while (sistema.introducirNotaAsignatura(correo, rut, n, paralelo) == false) {
			System.out.println("Ingrese el rut del alumno al que desea evaluar: ");
			rut = s.nextLine();
			System.out.println("Ingrese la nota: ");
			nota = s.nextLine();
			if (nota.equals("0")) {
				return;
			}
			n = escribirNota(nota);
			while (n < 1 || n > 7) {
				nota = s.nextLine();
				n = escribirNota(nota);
			}
		}
		sistema.actualizarNivel(rut);
		System.out.println("Nota ingresada");
	}
	/**
	 * this function verifies that the course grade is valid
	 * @param nota
	 * @return
	 */
	private static double escribirNota(String nota) {
		double notad;
		try {
			notad = Double.parseDouble(nota);
		} catch (Exception e) {
			System.out.println("Error, nota invalida");
			return -1;
		}
		return notad;
	}
	/**
	 * this function allows entering the mid-semester menu for students
	 * @param sistema
	 * @param correo
	 */
	private static void alumnoMitadSemestre(UniversitySystem sistema, String correo) {
		System.out.println("Bienvenido a la mitad de semestre alumno");
		System.out.println("[1]	Eliminar asignatura	[0]	Salir");
		String o = s.nextLine();
		while ((o.equals("1")) == false && (o.equals("0")) == false) {
			System.out.println("Opcion invalida");
			o = s.nextLine();
		}
		while (o.equals("0") == false) {
			if (o.equals("1")) {
				eliminarAsignatura(sistema, correo);
			}
			System.out.println("[1]	Eliminar asignatura	[0]	Salir");
			o = s.nextLine();
			while ((o.equals("1")) == false && (o.equals("2")) == false && (o.equals("0")) == false) {
				System.out.println("Opcion invalida");
				o = s.nextLine();
			}
		}
	}
	/**
	 * This function allows you to enter the semester start menu for teachers
	 * @param sistema
	 * @param correo
	 */
	private static void profesorInicioSemestre(UniversitySystem sistema, String correo) {
		System.out.println("Bienvenido al comienzo de semestre profesor");
		System.out.println("[1]	Chequear alumnos	[0]	Salir");
		String o = s.nextLine();
		while ((o.equals("1")) == false && (o.equals("0")) == false) {
			System.out.println("Opcion invalida");
			o = s.nextLine();
		}
		while (o.equals("0") == false) {
			if (o.equals("1")) {
				verAlumnos(sistema, correo);
			}
			System.out.println("[1]	Chequear alumnos	[0]	Salir");
			o = s.nextLine();
			while ((o.equals("1")) == false && (o.equals("0")) == false) {
				System.out.println("Opcion invalida");
				o = s.nextLine();
			}
		}
	}
	/**
	 * this function shows the students of each parallel dictated by a teacher
	 * @param sistema
	 * @param correo
	 * @return
	 */
	private static int verAlumnos(UniversitySystem sistema, String correo) {
		System.out.println("Lista de asignaturas");
		sistema.desplegarParalelosProfesor(correo);
		System.out.println("Ingrese el paralelo que decea ver: ");
		String paralelo = s.nextLine();
		while (correctNumber(paralelo) == false) {
			System.out.println("Intentelo denuevo: ");
			paralelo = s.nextLine();
		}
		if (paralelo.equals("0")) {
			return -1;
		}
		int p = Integer.parseInt(paralelo);
		while (sistema.desplegarEstudiantes(correo, p) == false) {
			System.out.println("Intentelo denuevo: ");
			paralelo = s.nextLine();
			while (correctNumber(paralelo) == false) {
				System.out.println("Intentelo denuevo: ");
				paralelo = s.nextLine();
			}
			if (paralelo.equals("0")) {
				return -1;
			}
			p = Integer.parseInt(paralelo);
		}
		return p - 1;
	}
	/**
	 * this function shows the semester start menu that a student has
	 * @param sistema
	 * @param correo
	 * @param rut
	 */
	private static void alumnoInicioSemestre(UniversitySystem sistema, String correo, String rut) {
		System.out.println("Bienvenido al comienzo de semestre alumno");
		System.out.println("[1]	Inscribir asignatura	[2]	Eliminar asignatura	[0]	Salir");
		String o = s.nextLine();
		while ((o.equals("1")) == false && (o.equals("2")) == false && (o.equals("0")) == false) {
			System.out.println("Opcion invalida");
			o = s.nextLine();
		}
		while (o.equals("0") == false) {
			if (o.equals("1")) {
				inscribirAsignaturas(sistema, correo, rut);
			}
			if (o.equals("2")) {
				eliminarAsignatura(sistema, correo);
			}
			System.out.println("[1]	Inscribir asignatura	[2]	Eliminar asignatura	[0]	Salir");
			o = s.nextLine();
			while ((o.equals("1")) == false && (o.equals("2")) == false && (o.equals("0")) == false) {
				System.out.println("Opcion invalida");
				o = s.nextLine();
			}
		}
	}
	/**
	 * this feature allows students to enroll a course
	 * @param sistema
	 * @param correo
	 * @param rut
	 */
	public static void inscribirAsignaturas(UniversitySystem sistema, String correo, String rut) {
		sistema.desplegarAsignaturasInscribir(correo);
		System.out.println("Ingrese el codigo del ramo que desea inscribir: ");
		String codigo = s.nextLine();
		while (sistema.comprobarAsignatura(correo, codigo) == false && codigo.equals("0") == false) {
			System.out.println("Intentelo denuevo: ");
			codigo = s.nextLine();
		}
		if (codigo.equals("0")) {
			return;
		}
		sistema.desplegarParalelosAsignatura(codigo);
		System.out.println("Ingrese el paralelo: ");
		String paralelo = s.nextLine();
		while (correctNumber(paralelo) == false) {
			System.out.println("Intentelo denuevo: ");
			paralelo = s.nextLine();
		}
		if (paralelo.equals("0")) {
			return;
		}
		int p = Integer.parseInt(paralelo);
		while (sistema.inscribirAsignatura(rut, codigo, p) == false) {
			System.out.println("Intentelo denuevo: ");
			paralelo = s.nextLine();
			if (paralelo.equals("0")) {
				return;
			}
			while (correctNumber(paralelo) == false) {
				System.out.println("Intentelo denuevo: ");
				paralelo = s.nextLine();
				if (paralelo.equals("0")) {
					return;
				}
			}
			p = Integer.parseInt(paralelo);
		}
		System.out.println("Asignatura Inscrita");
	}
	/**
	 * this feature allows students to delete an active course
	 * @param sistema
	 * @param correo
	 */
	private static void eliminarAsignatura(UniversitySystem sistema, String correo) {
		System.out.println("Lista de Asignaturas inscritas");
		if (sistema.desplegarAsignaturasInscritas(correo) == false) {
			return;
		}
		System.out.println("Ingrese el codigo de la asignatura que desea eliminar: ");
		String codigo = s.nextLine();
		while (sistema.comprobarAsignaturaCursando(correo, codigo) == false) {
			System.out.println("Intentelo denuevo: ");
			codigo = s.nextLine();
			if (codigo.equals("0")) {
				return;
			}
		}
		if (sistema.eliminarAsignatura(correo, codigo) == false) {
			System.out.println("No se pudo eliminar la asignatura");
			return;
		} else {
			System.out.println("Asignatura eliminada");
		}
	}
	/**
	 * this function reads the text files and enters them into the system
	 * @param sistema
	 * @throws FileNotFoundException
	 */
	public static void Lectura(UniversitySystem sistema) throws FileNotFoundException {
		File file1 = new File("asignaturas.txt");
		Scanner arch1 = new Scanner(file1);
		while (arch1.hasNextLine()) {
			String linea = arch1.nextLine();
			String[] partes = linea.split(",");
			String codigo = partes[0];
			String nombre = partes[1];
			int creditos = Integer.parseInt(partes[2]);
			if (partes[3].equals("obligatoria")) {
				int nivel = Integer.parseInt(partes[4]);
				sistema.ingresarAsignaturaObligatoria(codigo, nombre, creditos, nivel);
				for (int i = 0; i < Integer.parseInt(partes[5]); i++) {
					String precodigo = partes[6 + i];
					sistema.setPreRequisito(codigo, precodigo);
				}
			}
			if (partes[3].equals("opcional")) {
				int precreditos = Integer.parseInt(partes[4]);
				sistema.ingresarAsignaturaOpcional(codigo, nombre, creditos, precreditos);
			}
		}
		arch1.close();
		File file2 = new File("profesores.txt");
		Scanner arch2 = new Scanner(file2);
		while (arch2.hasNextLine()) {
			String linea = arch2.nextLine();
			String[] partes = linea.split(",");
			String rut = partes[0];
			String correo = partes[1];
			String contrase�a = partes[2];
			int saldo = Integer.parseInt(partes[3]);
			sistema.ingresarProfesor(rut, correo, contrase�a, saldo);
		}
		arch2.close();
		File file3 = new File("paralelos.txt");
		Scanner arch3 = new Scanner(file3);
		while (arch3.hasNextLine()) {
			String linea = arch3.nextLine();
			String[] partes = linea.split(",");
			int numero = Integer.parseInt(partes[0]);
			String codigo = partes[1];
			String rut = partes[2];
			sistema.ingresarParalelo(numero, codigo, rut);
		}
		arch3.close();
		File file4 = new File("estudiantes.txt");
		Scanner arch4 = new Scanner(file4);
		while (arch4.hasNextLine()) {
			String linea = arch4.nextLine();
			String[] partes = linea.split(",");
			String rut = partes[0];
			String correo = partes[1];
			int nivel = Integer.parseInt(partes[2]);
			String contrase�a = partes[3];
			sistema.ingresarEstudiante(rut, correo, nivel, contrase�a);
			linea = arch4.nextLine();
			int cantCursadas = Integer.parseInt(linea);
			for (int i = 0; i < cantCursadas; i++) {
				linea = arch4.nextLine();
				partes = linea.split(",");
				String codigo = partes[0];
				double nota = Double.parseDouble(partes[1]);
				if (sistema.ingresarAsignaturaCursada(rut, codigo, nota) == false) {
					System.out.println("No se pudo ingresar la asignatura cursada");
				}
			}
			linea = arch4.nextLine();
			int cantActivas = Integer.parseInt(linea);
			for (int i = 0; i < cantActivas; i++) {
				linea = arch4.nextLine();
				partes = linea.split(",");
				String codigo = partes[0];
				int paralelo = Integer.parseInt(partes[1]);
				if (sistema.inscribirAsignatura(rut, codigo, paralelo) == false) {
					System.out.println("No se pudo ingresar la asignatura activa");
				}
			}

		}
		arch4.close();
	}
	/**
	 * this function verifies that the date is entered correctly
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int ingresarFecha() {
		System.out.println("Dia: ");
		String dia = s.nextLine();
		System.out.println("Mes: ");
		String mes = s.nextLine();
		while (diaCorrecto((dia), (mes)) == false) {
			System.out.println("Dia: ");
			dia = s.nextLine();
			System.out.println("Mes: ");
			mes = s.nextLine();
		}
		int m = Integer.parseInt(mes);
		int d = Integer.parseInt(dia);
		Date fI = new Date(121, m - 1, d);
		Date fi1 = new Date(121, 2, 8);
		Date ff1 = new Date(121, 4, 2);
		Date fi2 = new Date(121, 4, 3);
		Date ff2 = new Date(121, 6, 11);
		Date fi3 = new Date(121, 6, 12);
		Date ff3 = new Date(121, 6, 25);
		Date f4 = new Date(121, 6, 26);
		System.out.println(fI);
		if ((fI.after(fi1) && fI.before(ff1)) || fI.equals(fi1) || fI.equals(ff1)) {
			System.out.println("Inicio Semestre");
			return 0;
		}
		if ((fI.after(fi2) && fI.before(ff2)) || fI.equals(fi2) || fI.equals(ff2)) {
			System.out.println("Mitad Semestre");
			return 1;
		}
		if ((fI.after(fi3) && fI.before(ff3)) || fI.equals(fi3) || fI.equals(ff3)) {
			System.out.println("Final Semestre");
			return 2;
		}
		if (fI.equals(f4)) {
			System.out.println("Cierre Semestre");
			return 3;
		} else {
			System.out.println("No hay men�");
			return 4;
		}
	}
	/**
	 * this function verifies that the days correspond to their corresponding months
	 * @param d
	 * @param m
	 * @return
	 */
	public static boolean diaCorrecto(String d, String m) {
		if (correctNumber(d) == false || correctNumber(m) == false) {
			return false;
		}
		int dia = Integer.parseInt(d);
		int mes = Integer.parseInt(m);
		if (mes == 2) {
			if (dia < 29 && dia > 0) {
				return true;
			}
		}
		if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
			if (dia < 31 && dia > 0) {
				return true;
			}
		}
		if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
			if (dia < 32 && dia > 0) {
				return true;
			}
		}
		return false;
	}
	/**
	 * this function verifies that a String can be transformed into an int
	 * @param numero
	 * @return
	 */
	public static boolean correctNumber(String numero) {
		String partes[] = numero.split("");
		for (int i = 0; i < partes.length; i++) {
			if (partes[i].equals("0") == false && partes[i].equals("1") == false && partes[i].equals("2") == false
					&& partes[i].equals("3") == false && partes[i].equals("4") == false
					&& partes[i].equals("5") == false && partes[i].equals("6") == false
					&& partes[i].equals("7") == false && partes[i].equals("8") == false
					&& partes[i].equals("9") == false) {
				return false;
			}
		}
		return true;
	}

}
