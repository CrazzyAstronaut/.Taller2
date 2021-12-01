/**
 * 
 */
package Logica;

import Dominio.Asignatura;

/**
 * @author Claudio Córtes M}
 *
 */
public class Asignaturas {
	private int max,cant;
	private Asignatura [] lista;
	
	public Asignaturas(int max) {
		this.max=max;
		this.lista=new Asignatura[max];
		this.cant=0;
	}
	public Asignatura getAsignatura(String codigo) {
		for(int i=0;i<cant;i++) {
			if(lista[i].getCodigo().equals(codigo)) {
				return lista[i];
			}
		}
		return null;
	}
	public boolean addAsignatura(Asignatura asignatura) {
		if(cant<max) {
			lista[cant]=asignatura;
			cant++;
			return true;
		}
	return false;
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
	 * @return the lista
	 */
	public Asignatura[] getLista() {
		return lista;
	}
	/**
	 * @param lista the lista to set
	 */
	public void setLista(Asignatura[] lista) {
		this.lista = lista;
	}
	
	
}	
