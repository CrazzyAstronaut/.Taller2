package Logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.Scanner;

public class App {

	private static Scanner s;

	public static void main(String[] args) throws IOException {
		UniversitySystem sistema = new UniversitySystemImpl();
		Lectura(sistema);
		boolean cerrar = false;
		while (cerrar == false) {
			s = new Scanner(System.in);
			System.out.println("Ingrese su correo: ");
			String correo = s.nextLine();
			System.out.println("Ingrese su contraseña: ");
			String contraseña = s.nextLine();
			int tipoCuenta = sistema.iniciarSesion(correo, contraseña);
			while (tipoCuenta == -1) {
				System.out.println("No se pudo iniciar sesión, porfavor intentelo denuevo");
				System.out.println("Ingrese su correo: ");
				correo = s.nextLine();
				System.out.println("Ingrese su contraseña: ");
				contraseña = s.nextLine();
				tipoCuenta = sistema.iniciarSesion(correo, contraseña);
			}
			String rut = sistema.getRut(correo);
			int menuSemestre = ingresarFecha();
			if (menuSemestre == 0) {
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
				// guardarEstudiantes(sistema);
			}
			if (menuSemestre == 1) {
				if (tipoCuenta == 0) {
					alumnoMitadSemestre(sistema, correo);
				} else {
					System.out.println("No hay Opciones Disponibles");
				}
				System.out.println("Guardando...");
				// guardarEstudiantes(sistema);
			}
			if (menuSemestre == 2) {
				if (tipoCuenta == 1) {
					profesorFinalSemestre(sistema, correo);
				} else {
					System.out.println("No hay Opciones Disponibles");
				}
				System.out.println("Guardando...");
				// guardarEstudiantes(sistema);
			}
			if (menuSemestre == 3) {
				if (tipoCuenta == 2) {
					adminCierreSemestre(sistema, cerrar);
				} else {
					System.out.println("No hay Opciones Disponibles");
				}
			}
			if (menuSemestre == 4) {
				System.out.println("Disfrute sus vacaciones");
			}
		}
		/*
		 * boolean cerrar while scanner(log) scanner(fecha) if(fecha)(caso 1) ^ inicio
		 * semestre (Estudiantes inscribiro eliminar ramos)(Profesor ver alumnos)
		 * 
		 * if(fecha)(caso 2) ^ mitad semestre (Estudiantes eliminar ramos)
		 * if(fecha)(caso 3) ^ final semestre (Profesor ingresar notas) !!!Esto se debe
		 * hacer si o si if(fecha)(caso 4)//cierre ^ Cierre semestre (Admin generar
		 * archivo de egresados)
		 * 
		 */
	}

	private static void guardarEstudiantes(UniversitySystem sistema) throws IOException {
		FileWriter file1 = new FileWriter("estudiantes.txt");
		sistema.guardarEstudiantes(file1);
	}

	
	private static void adminCierreSemestre(UniversitySystem sistema, boolean cerrar) {
		System.out.println("Bienvenido administrador");

	}

	
	private static void profesorFinalSemestre(UniversitySystem sistema, String correo) {
		System.out.println("Bienvenido al final de semestre profesor");

	}

	
	private static void alumnoMitadSemestre(UniversitySystem sistema, String correo) {
		System.out.println("Bienvenido a la mitad de semestre alumno");
		System.out.println("[1]	Eliminar asignatura	[0]	Salir");
		s = new Scanner(System.in);
		String o = s.nextLine();
		while ((o.equals("1")) == false && (o.equals("0")) == false) {
			System.out.println("Opcion invalida");
			o = s.nextLine();
		}
		while (o.equals("0") == false) {
			if (o.equals("1")) {
				eliminarAsignatura(sistema,correo);
			}
			System.out.println("[1]	Eliminar asignatura	[0]	Salir");
			o = s.nextLine();
			while ((o.equals("1")) == false && (o.equals("2")) == false && (o.equals("0")) == false) {
				System.out.println("Opcion invalida");
				o = s.nextLine();
			}
		}
	}
	

	private static void profesorInicioSemestre(UniversitySystem sistema, String correo) {
		System.out.println("Bienvenido al comienzo de semestre profesor");
		System.out.println("[1]	Chequear alumnos	[0]	Salir");
		s = new Scanner(System.in);
		String o = s.nextLine();
		while ((o.equals("1")) == false && (o.equals("0")) == false) {
			System.out.println("Opcion invalida");
			o = s.nextLine();
		}
		while (o.equals("0") == false) {
			if (o.equals("1")) {
				verAlumnos(sistema,correo);
			}
			System.out.println("[1]	Chequear alumnos	[0]	Salir");
			o = s.nextLine();
			while ((o.equals("1")) == false && (o.equals("0")) == false) {
				System.out.println("Opcion invalida");
				o = s.nextLine();
			}
		}
	}

	private static void verAlumnos(UniversitySystem sistema, String correo) {
		System.out.println("Lista de asignaturas");
		sistema.desplegarParalelosProfesor(correo);
		System.out.println("Ingrese el paralelo que decea ver: ");
		String paralelo = s.nextLine();
		while (correctNumber(paralelo)==false) {
			System.out.println("Intentelo denuevo: ");
			paralelo = s.nextLine();
			if (paralelo.equals("0")) {
				return;
			}
		}
		int p = Integer.parseInt(paralelo);
		while (sistema.desplegarEstudiantes(correo,p)==false) {
			System.out.println("Intentelo denuevo: ");
			paralelo = s.nextLine();
			while (correctNumber(paralelo)==false) {
				System.out.println("Intentelo denuevo: ");
				paralelo = s.nextLine();
				if (paralelo.equals("0")) {
					return;
				}
				p = Integer.parseInt(paralelo);
			}
		}
	}

	private static void alumnoInicioSemestre(UniversitySystem sistema, String correo, String rut) {
		System.out.println("Bienvenido al comienzo de semestre alumno");
		System.out.println("[1]	Inscribir asignatura	[2]	Eliminar asignatura	[0]	Salir");
		s = new Scanner(System.in);
		String o = s.nextLine();
		while ((o.equals("1")) == false && (o.equals("2")) == false && (o.equals("0")) == false) {
			System.out.println("Opcion invalida");
			o = s.nextLine();
		}
		while (o.equals("0") == false) {
			if (o.equals("1")) {
				inscribirAsignaturas(sistema,correo,rut);
			}
			if (o.equals("2")) {
				eliminarAsignatura(sistema,correo);
			}
			System.out.println("[1]	Inscribir asignatura	[2]	Eliminar asignatura	[0]	Salir");
			o = s.nextLine();
			while ((o.equals("1")) == false && (o.equals("2")) == false && (o.equals("0")) == false) {
				System.out.println("Opcion invalida");
				o = s.nextLine();
			}
		}
	}


	public static void inscribirAsignaturas(UniversitySystem sistema, String correo, String rut) {
		sistema.desplegarAsignaturasInscribir(correo);
		System.out.println("Ingrese el codigo del ramo que desea inscribir: ");
		String codigo = s.nextLine();
		while (sistema.comprobarAsignatura(correo, codigo)==false) {
			System.out.println("Intentelo denuevo: ");
			codigo = s.nextLine();
			if (codigo.equals("0")) {
				return;
			}
		}
		sistema.desplegarParalelosAsignatura(codigo);
		System.out.println("Ingrese el paralelo: ");
		String paralelo = s.nextLine();
		while (correctNumber(paralelo)==false) {
			System.out.println("Intentelo denuevo: ");
			paralelo = s.nextLine();
			if (paralelo.equals("0")) {
				return;
			}
		}
		int p = Integer.parseInt(paralelo);
		while (sistema.inscribirAsignatura(rut, codigo, p)==false) {
			System.out.println("Intentelo denuevo: ");
			paralelo = s.nextLine();
			while (correctNumber(paralelo)==false) {
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
	private static void eliminarAsignatura(UniversitySystem sistema, String correo) {
		System.out.println("Lista de Asignaturas inscritas");
		if(sistema.desplegarAsignaturasInscritas(correo)==false) {
			return;
		}
		System.out.println("Ingrese el codigo de la asignatura que desea eliminar: ");
		String codigo = s.nextLine();
		while (sistema.comprobarAsignaturaCursando(correo, codigo)==false) {
			System.out.println("Intentelo denuevo: ");
			codigo = s.nextLine();
			if (codigo.equals("0")) {
				return;
			}
		}
		if(sistema.eliminarAsignatura(correo, codigo)==false) {
			System.out.println("No se pudo eliminar la asignatura");
			return;
		}
		else {
			System.out.println("Asignatura eliminada");
		}
	}

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
			String contraseña = partes[2];
			int saldo = Integer.parseInt(partes[3]);
			sistema.ingresarProfesor(rut, correo, contraseña, saldo);
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
		// lectura de estudiantes.txt
		File file4 = new File("estudiantes.txt");
		Scanner arch4 = new Scanner(file4);
		while (arch4.hasNextLine()) {
			String linea = arch4.nextLine();
			String[] partes = linea.split(",");
			String rut = partes[0];
			String correo = partes[1];
			int nivel = Integer.parseInt(partes[2]);
			String contraseña = partes[3];
			sistema.ingresarEstudiante(rut, correo, nivel, contraseña);
			linea = arch4.nextLine();
			int cantCursadas = Integer.parseInt(linea);
			for (int i = 0; i < cantCursadas; i++) {
				linea = arch4.nextLine();
				partes = linea.split(",");
				String codigo = partes[0];
				double nota = Double.parseDouble(partes[1]);
				if (sistema.ingresarAsignaturaCursada(rut, codigo, nota)) {
					System.out.println(rut + " Asignatura cursada " + codigo + " :" + nota);
				} else {
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
				if (sistema.inscribirAsignatura(rut, codigo, paralelo)) {
					System.out.println(rut + " Asignatura activa " + codigo + " :" + paralelo);
				} else {
					System.out.println("No se pudo ingresar la asignatura activa");
				}
			}

		}
	}

	public static int ingresarFecha() {
		s = new Scanner(System.in);
		System.out.println("dia: ");
		String dia = s.nextLine();
		System.out.println("mes: ");
		String mes = s.nextLine();
		while (diaCorrecto((dia), (mes)) == false) {
			System.out.println("dia: ");
			dia = s.nextLine();
			System.out.println("mes: ");
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
			System.out.println("No hay menú");
			return 4;
		}
	}

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
