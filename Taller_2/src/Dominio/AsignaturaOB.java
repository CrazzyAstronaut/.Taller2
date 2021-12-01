/**
 * 
 */
package Dominio;

import Logica.Asignaturas;

/**
 * @author Claudio Córtes M}
 *
 */
public class AsignaturaOB extends Asignatura {
	private int nivel;
	private Asignaturas asignaturas;
	public AsignaturaOB(String nombre,String codigo,int nivel,int creditos) {
		super(nombre,codigo,creditos);
		this.nivel=nivel;
		
		this.asignaturas= new Asignaturas(1000);
		
	}
	/**
	 * @return the asignaturas
	 */
	public Asignaturas getAsignaturas() {
		return asignaturas;
	}
	/**
	 * @param asignaturas the asignaturas to set
	 */
	public void setAsignaturas(Asignaturas asignaturas) {
		this.asignaturas = asignaturas;
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
	 * @return the creditos
	 */
	
	
}
