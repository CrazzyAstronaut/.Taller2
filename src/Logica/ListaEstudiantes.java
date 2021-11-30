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
}
