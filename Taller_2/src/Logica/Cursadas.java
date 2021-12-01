/**
 * 
 */
package Logica;

import Dominio.Cursada;

/**
 * @author Claudio Córtes M}
 *
 */
public class Cursadas {
	private int cant,max;
	private Cursada []lista;
	public Cursadas(int max) {
		this.max=max;
		this.lista= new Cursada[max];
		this.cant=0;
	}
	public boolean addCursada(Cursada cursada) {
		if(cant<max) {
			lista[cant]=cursada;
			cant++;
			return true;
		}
		return false;
	}
	/**
	 * @return the cant
	 */
	public int getCant() {
		return cant;
	}
	/**
	 * @param cant the cant to set
	 */
	public void setCant(int cant) {
		this.cant = cant;
	}
	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}
	/**
	 * @param max the max to set
	 */
	public void setMax(int max) {
		this.max = max;
	}
	/**
	 * @return the lista
	 */
	public Cursada[] getLista() {
		return lista;
	}
	/**
	 * @param lista the lista to set
	 */
	public void setLista(Cursada[] lista) {
		this.lista = lista;
	}
}
