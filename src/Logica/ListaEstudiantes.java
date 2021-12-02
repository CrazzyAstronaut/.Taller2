package Logica;

import Dominio.Estudiante;

public class ListaEstudiantes {
	private int cant;
	private int max;
	private Estudiante [] lista;
	
	public ListaEstudiantes(int max) {
		this.max = max;
		cant = 0;
		this.lista = new Estudiante [max];
	}

	public int getCant() {
		return cant;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public Estudiante[] getLista() {
		return lista;
	}

	public void setLista(Estudiante[] lista) {
		this.lista = lista;
	}
	public boolean agregar(Estudiante o) {
		if (cant < max) {
			lista [cant] = o;
			cant++;
			return true;
		}
		return false;
	}
	public Estudiante getEstudiante(int index) {
		return lista[index];
	}

	public int index(Estudiante est) {
		for(int i = 0; i < cant;i++) {
			if(lista[i].equals(est)) {
				return i;
			}
		}
		return -1;
	}
	public boolean eliminar(Estudiante est) {
		int indice = index(est);
		if(indice==-1) {return false;}
		for(int i=indice;i<cant-1;i++) {
			lista[i]=lista[i+1];
		}
		return true;
	}
}
