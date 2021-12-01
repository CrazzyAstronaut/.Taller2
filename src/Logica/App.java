package Logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class App {
	
	private static Scanner s;

	public static void main(String[] args) throws IOException {
		UniversitySystem sistema = new UniversitySystemImpl();
		Lectura(sistema);
	}

	private static void Lectura(UniversitySystem sistema) throws FileNotFoundException {
		File file1 = new File("asignaturas.txt");
		Scanner arch1 = new Scanner(file1);
		while (arch1.hasNextLine()) {
			String linea = arch1.nextLine();
			String[] partes = linea.split(",");
			String codigo = partes[0];
			String nombre = partes[1];
			int creditos = Integer.parseInt(partes[2]);
			if(partes[3].equals("obligatoria")) {
				int nivel = Integer.parseInt(partes[4]);
				sistema.ingresarAsignaturaObligatoria(codigo, nombre, creditos, nivel);
				for(int i=0;i<Integer.parseInt(partes[5]);i++) {
					String precodigo = partes[7+i];
					sistema.setPreRequisito(codigo, precodigo);
				}
			}
			if(partes[3].equals("opcional")) {
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
		//lectura de estudiantes.txt
		File file4 = new File("paralelos.txt");
		Scanner arch4 = new Scanner(file4);
		while (arch4.hasNextLine()) {
			String linea = arch4.nextLine();
			String[] partes = linea.split(",");
			
		}
	}

}
