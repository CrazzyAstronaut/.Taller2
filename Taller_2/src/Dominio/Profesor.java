/**
 * 
 */
package Dominio;

import Logica.Paralelos;

/**
 * @author Claudio Córtes M}
 *
 */
public class Profesor extends Cuenta {
	private int saldo;
	private Paralelos paralelosAsignados;
	public Profesor(String rut,String correo,String contraseña,int saldo ) {
		super(rut,correo,contraseña);
		this.saldo=saldo;
		this.paralelosAsignados= new Paralelos(1000);
	}
	/**
	 * @return the saldo
	 */
	public int getSaldo() {
		return saldo;
	}
	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	/**
	 * @return the paralelosAsignados
	 */
	public Paralelos getParalelosAsignados() {
		return paralelosAsignados;
	}
	/**
	 * @param paralelosAsignados the paralelosAsignados to set
	 */
	public void setParalelosAsignados(Paralelos paralelosAsignados) {
		this.paralelosAsignados = paralelosAsignados;
	}
	
}
