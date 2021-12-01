/**
 * 
 */
package Dominio;

/**
 * @author Claudio Córtes M}
 *
 */
public class Cursada {
	private Asignatura asignatura;
	private double nota;
	private boolean aprobada;
	public Cursada (double nota) {
		this.nota=nota;
		this.aprobada=false;
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
	 * @return the nota
	 */
	public double getNota() {
		return nota;
	}
	/**
	 * @param nota the nota to set
	 */
	public void setNota(double nota) {
		this.nota = nota;
	}
	/**
	 * @return the aprobada
	 */
	public boolean isAprobada() {
		return aprobada;
	}
	/**
	 * @param aprobada the aprobada to set
	 */
	public void setAprobada(boolean aprobada) {
		this.aprobada = aprobada;
	}
}
