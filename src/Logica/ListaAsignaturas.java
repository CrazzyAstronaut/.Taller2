package Logica;

import Dominio.Asignatura;

public class ListaAsignaturas {
	private int cant;
	private int max;
	private Asignatura[] lista;

	public ListaAsignaturas(int max) {
		this.max = max;
		cant = 0;
		this.lista = new Asignatura[max];
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

	public Asignatura[] getLista() {
		return lista;
	}

	public void setLista(Asignatura[] lista) {
		this.lista = lista;
	}

	public boolean agregar(Asignatura o) {
		if (cant < max) {
			lista[cant] = o;
			cant++;
			return true;
		}
		return false;
	}

	public Asignatura getAsignatura(int index) {
		return lista[index];
	}
	public Asignatura getAsignatura(String codigo) {
		for(int i = 0 ; i < cant ; i++ ) {
			if(lista[i].getCodigo().equals(codigo)) {
				return lista[i];
			}
		}
		return null;
	}
}
