/**
 * 
 */
package Logica;

import Dominio.Paralelo;

/**
 * @author Claudio Córtes M}
 *
 */
public class Paralelos {
	private int max,cant;
	private Paralelo []lista;
	public Paralelos(int max) {
		this.max=max;
		this.lista= new Paralelo[max];
		this.cant=0;
	}
	public Paralelo getParalelo(int numero){
		/**if(i<0 || i>cant || i>max) {
			return null;
		}*/
		int i;
		for(i=0;i<cant;i++) {
			if(lista[i].getNumero()== numero) {
				return lista[i];
			}
		}
		return null;
	
	}
	
	public boolean addParalelo(Paralelo paralelo) {
		if(cant<max) {
			lista[cant]=paralelo;
			cant++;
			return true;
		}
		return false;
	}
	public boolean delParalelo(String codigo,int numero) {
		 	for(int i=0;i<cant;i++) {
		 		if(lista[i].getNumero()==numero) {
		 			for(int k=i;k<cant;k++) {
		 				lista[k]=lista[k+1];
		 				cant--;
		 				return true;
		 			}
		 		}
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
	public Paralelo[] getLista() {
		return lista;
	}
	/**
	 * @param lista the lista to set
	 */
	public void setLista(Paralelo[] lista) {
		this.lista = lista;
	}
	

}
