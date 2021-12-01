/**
 * 
 */
package Logica;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import ucn.ArchivoEntrada;
import ucn.Registro;
import ucn.StdOut;

/**
 * @author Claudio Córtes M}
 *
 */
public class App {
	public static void lectura(UniversitySystem sistema) throws IOException {
		File file3= new File("Profesores.txt");
		Scanner arch3 = new Scanner(file3);
		while(arch3.hasNextLine()) {
			String linea= arch3.nextLine();
			String []partes= linea.split(",");
			String rut=partes[0];
			String correo= partes[1];
			String contraseña=partes[2];
			int saldo= Integer.parseInt(partes[3]);
			sistema.addProfesor(rut, correo, contraseña, saldo);
		}
		
		ArchivoEntrada arch2= new ArchivoEntrada("asignaturas.txt");
		while(!arch2.isEndFile()) {
			Registro r= arch2.getRegistro();
			String codigo= r.getString();
			String nombre= r.getString();
			int creditos= r.getInt();
			String tipo= r.getString();
			if(tipo.equals("opcional")) {
				int creditosPr=r.getInt();
				sistema.addAsignaturasOp(nombre, codigo, creditos, creditosPr);

			}else if(tipo.equals("obligatoria")) {
				int nivel= r.getInt();
				sistema.addAsignaturaOb(nombre, codigo, nivel, creditos);
				int cantAsig=r.getInt();
				for(int i= 0;i<cantAsig;i++){
					String codigoAP= r.getString();
					try {
						sistema.asociarAignaturas(codigo, codigoAP);
					}catch(NullPointerException ex){
						StdOut.println(ex.getMessage());
					}
				}
			}
		}
		
		File file4= new File("paralelos.txt");
		Scanner arch4=new Scanner(file4);
		while(arch4.hasNextLine()) {
			String linea= arch4.nextLine();
			String []partes= linea.split(",");
			int numero= Integer.parseInt(partes[0]);
			String codigoAs=partes[1];
			String rutPro= partes[2];
			
			try {
				sistema.addParalelo(numero);
				sistema.asociarProfesorYAsignaturaConParalelo(numero, codigoAs, rutPro);
			}catch(NullPointerException ex) {
				StdOut.println(ex.getMessage());
			}
		}
	
		ArchivoEntrada arch= new ArchivoEntrada("estudiantes.txt");
		while(!arch.isEndFile()){
			Registro r= arch.getRegistro();
			String rut= r.getString();
			String correo = r.getString();
			int nivel=r.getInt();
			String contraseña=r.getString();
			sistema.addEstudiante(rut, correo, contraseña, nivel);
			Registro r2=arch.getRegistro();
			int cantAsigCur=r2.getInt();
			StdOut.println(cantAsigCur);
			for(int i=0;i<cantAsigCur;i++) {
				Registro r3=arch.getRegistro();
				String cod=r3.getString();
				double nota=Double.parseDouble(r3.getString());
				try {
					sistema.asociarAsigCursConEstudiante(rut, cod, nota);
				}catch(NullPointerException ex) {
					StdOut.println(ex.getMessage());
				}
			}
			Registro r4=arch.getRegistro();
			int cantAsigIns=r4.getInt();
			for(int j=0;j<cantAsigIns;j++) {
				Registro r5=arch.getRegistro();
				String codigoAsigIns=r5.getString();
				int numeroPaAsig= r5.getInt();
				try {
					sistema.asociarAsigCursConEstudiante(rut, codigoAsigIns, numeroPaAsig);
				}catch(NullPointerException ex) {
					StdOut.println(ex.getMessage());
				}
			}
		}
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		UniversitySystem sistema= new UniversitySystemImpl();
		lectura(sistema);
	}

}
