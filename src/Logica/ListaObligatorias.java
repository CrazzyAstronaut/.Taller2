package Logica;

import Dominio.Obligatoria;

public class ListaObligatorias {
	private int cant;
	private int max;
	private Obligatoria[] lista;

	public ListaObligatorias(int max) {
		this.max = max;
		cant = 0;
		this.lista = new Obligatoria[max];
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

	public Obligatoria[] getLista() {
		return lista;
	}

	public void setLista(Obligatoria[] lista) {
		this.lista = lista;
	}

	public boolean agregar(Obligatoria o) {
		if (cant < max) {
			lista[cant] = o;
			cant++;
			return true;
		}
		return false;
	}

	public Obligatoria getObligatoria(int index) {
		return lista[index];
	}
}
