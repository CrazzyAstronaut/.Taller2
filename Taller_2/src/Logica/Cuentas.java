/**
 * 
 */
package Logica;

import Dominio.Cuenta;

/**
 * @author Claudio Córtes M}
 *
 */
public class Cuentas {
	private int max,cant;
	private Cuenta []lista;
	public Cuentas(int max) {
		this.max=max;
		this.lista= new Cuenta[max];
		this.cant=cant;
	}
	public boolean addCuenta(Cuenta cuenta) {
		if(cant<max) {
			lista[cant]=cuenta;
			cant++;
			return true;
		}
		return false;
	}
	public Cuenta getCuenta(String correo) {
		int i;
		for(i=0;i<cant;i++) {
			if(lista[i].getCorreo().equals(correo)) {
				return lista[i];
			}
		}
		return null;
	}
	public Cuenta getCuentar(String rut) {
		int i;
		for(i=0;i<cant;i++) {
			if(lista[i].getRut().equals(rut)) {
				return lista[i];
			}
		}
		return null;
	}
	public Cuenta getCuenta(int i) {
		if(i<0 || i>cant ||i>max) {
			return null;
		}
		return lista[i];
	}
	public boolean loginCuenta(String correo,String contraseña) {
		int i;
		for(i=0;i<cant;i++) {
			if(lista[i].getCorreo().equals(correo) & lista[i].getContraseña().equals(contraseña)) {
				return true;
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
	public Cuenta[] getLista() {
		return lista;
	}
	/**
	 * @param lista the lista to set
	 */
	public void setLista(Cuenta[] lista) {
		this.lista = lista;
	}
	
}
