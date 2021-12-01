/**
 * 
 */
package Logica;

import Dominio.Estudiante;

/**
 * @author Claudio Córtes M}
 *
 */
public class Estudiantes {
	private int max,cant;
	private Estudiante []lista;
	public Estudiantes(int max) {
		this.max=max;
		this.lista=new Estudiante[max];
		this.cant=0;
	}
	public Estudiante getEstudiante(String rut) {
		for(int i=0;i<cant;i++) {
			if(lista[i].getRut().equals(rut)) {
				return lista[i];
			}
		}
		return null;
	}
	public Estudiante getEsudiante(int i) {
		if(i<0 || i>cant || i>max  ) {
			return null;
		}
		return lista[i];
	}
	public boolean addEstudiante(Estudiante estudiante) {
		if(cant<max) {
			lista[cant]=estudiante;
			cant++;
			return true;
		}
		return false;
	}

}
