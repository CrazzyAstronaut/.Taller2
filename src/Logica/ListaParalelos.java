package Logica;

import Dominio.Asignatura;
import Dominio.Paralelo;

public class ListaParalelos {
	private int cant;
	private int max;
	private Paralelo [] lista;
	
	public ListaParalelos(int max) {
		this.max = max;
		cant = 0;
		this.lista = new Paralelo [max];
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

	public Paralelo[] getLista() {
		return lista;
	}

	public void setLista(Paralelo[] lista) {
		this.lista = lista;
	}
	public boolean agregar(Paralelo o) {
		if (cant < max) {
			lista [cant] = o;
			cant++;
			return true;
		}
		return false;
	}
	public Paralelo getParalelo(int index) {
		return lista[index];
	}

	public Paralelo getParalelo(Asignatura asig) {
		for(int i = 0;i<cant;i++) {
			if(lista[i].getAsignatura().equals(asig)) {
				return lista[i];
			}
		}
		return null;
	}
	
	public Paralelo getParaleloNumero(int numero) {
		for (int i = 0; i < cant; i++) {
			if (lista[i].getNumero()==numero) {
				return lista[i];
			}
		}
		return null;
	}

	public boolean eliminar(int indice) {
		if(indice==-1) {return false;}
		for(int i=indice;i<cant-1;i++) {
			lista[i]=lista[i+1];
			lista[i+1]=null;
		}
		cant--;
		return true;
	}

	public boolean eliminar(Paralelo p) {
		int indice = index(p);
		if(indice==-1) {return false;}
		for(int i=indice;i<cant-1;i++) {
			lista[i]=lista[i+1];
			lista[i+1]=null;
		}
		cant--;
		return true;
	}
	public int index(Paralelo p) {
		for(int i = 0; i < cant;i++) {
			if(lista[i].equals(p)) {
				return i;
			}
		}
		return -1;
	}
	
}
