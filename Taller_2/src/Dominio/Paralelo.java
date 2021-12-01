/**
 * 
 */
package Dominio;

import Logica.Estudiantes;

/**
 * @author Claudio Córtes M}
 *
 */
public class Paralelo {
	private int numero;
	private Asignatura asignatura;
	private Profesor profesor;
	private Estudiantes estudiantes;
	public Paralelo(int numero) {
		this.numero=numero;
	}
	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}
	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	/**
	 * @return the asignatura
	 */
	public Asignatura getAsignatura() {
		return asignatura;
	}
	/**
	 * @param asignatura the asignatura to set
	 */
	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}
	/**
	 * @return the profesor
	 */
	public Profesor getProfesor() {
		return profesor;
	}
	/**
	 * @param profesor the profesor to set
	 */
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	/**
	 * @return the estudiantes
	 */
	public Estudiantes getEstudiantes() {
		return estudiantes;
	}
	/**
	 * @param estudiantes the estudiantes to set
	 */
	public void setEstudiantes(Estudiantes estudiantes) {
		this.estudiantes = estudiantes;
	}
}
