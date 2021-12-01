/**
 * 
 */
package Dominio;

import Logica.Paralelos;

/**
 * @author Claudio Córtes M}
 *
 */
public abstract class  Asignatura {
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
	private String nombre,codigo;
	private int creditos;
	private Paralelos paralelos;
	public Asignatura(String nombre,String codigo,int creditos) {
		this.nombre=nombre;
		this.codigo=codigo;
		this.creditos=creditos;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the paralelos
	 */
	public Paralelos getParalelos() {
		return paralelos;
	}
	/**
	 * @param paralelos the paralelos to set
	 */
	public void setParalelos(Paralelos paralelos) {
		this.paralelos = paralelos;
	}
	
}
