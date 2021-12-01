/**
 * 
 */
package Dominio;

import Logica.Cursadas;
import Logica.Paralelos;

/**
 * @author Claudio Córtes M}
 *
 */
public class Estudiante extends Cuenta {
	private int nivel;
	private static int creditos=40;
	private Paralelos asignaturasActivas;
	private Cursadas AsignaturasCursadas;
	public Estudiante(String rut,String correo,String contraseña, int nivel) {
		super(rut,correo,contraseña);
		this.nivel=nivel;
		this.asignaturasActivas=new Paralelos(10000);
		this.AsignaturasCursadas=new Cursadas(10000);
	}
	/**
	 * @return the creditos
	 */
	public int getCreditos() {
		return creditos;
	}
	/**
	 * @param creditos the creditos to set
	 */
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	/**
	 * @return the nivel
	 */
	public int getNivel() {
		return nivel;
	}
	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	/**
	 * @return the asignaturasActivas
	 */
	public Paralelos getAsignaturasActivas() {
		return asignaturasActivas;
	}
	/**
	 * @param asignaturasActivas the asignaturasActivas to set
	 */
	public void setAsignaturasActivas(Paralelos asignaturasActivas) {
		this.asignaturasActivas = asignaturasActivas;
	}
	/**
	 * @return the asignaturasCursadas
	 */
	/**
	 * @return the asignaturasCursadas
	 */
	public Cursadas getAsignaturasCursadas() {
		return AsignaturasCursadas;
	}
	/**
	 * @param asignaturasCursadas the asignaturasCursadas to set
	 */
	public void setAsignaturasCursadas(Cursadas asignaturasCursadas) {
		AsignaturasCursadas = asignaturasCursadas;
	}
	
	
}
