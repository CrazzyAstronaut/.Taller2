package Logica;

import Dominio.Cursada;

public class ListaCursadas {
	private int cant;
	private int max;
	private Cursada [] lista;
	
	public ListaCursadas(int max) {
		this.max = max;
		cant = 0;
		this.lista = new Cursada [max];
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

	public Cursada[] getLista() {
		return lista;
	}

	public void setLista(Cursada[] lista) {
		this.lista = lista;
	}
	public boolean agregar(Cursada o) {
		if (cant < max) {
			lista [cant] = o;
			cant++;
			return true;
		}
		return false;
	}
	public Cursada getCursada(int index) {
		return lista[index];
	}
}
