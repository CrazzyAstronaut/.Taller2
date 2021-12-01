package Logica;

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

	public Paralelo getParaleloNumero(int numero) {
		for (int i = 0; i < cant; i++) {
			if (lista[i].getNumero()==numero) {
				return lista[i];
			}
		}
		return null;
	}
}
