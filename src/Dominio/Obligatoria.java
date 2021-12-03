package Dominio;

import Logica.ListaObligatorias;

public class Obligatoria extends Asignatura {
	private int NivelRequerido;
	private ListaObligatorias prerequisitos;

	public Obligatoria(String nombre, String codigo, int creditos, int nivelRequerido) {
		super(nombre, codigo, creditos);
		NivelRequerido = nivelRequerido;
		this.prerequisitos = new ListaObligatorias(999);
	}

	public int getNivelRequerido() {
		return NivelRequerido;
	}

	public void setNivelRequerido(int nivelRequerido) {
		NivelRequerido = nivelRequerido;
	}

	public ListaObligatorias getPrerequisitos() {
		return prerequisitos;
	}

	public void setPrerequisitos(ListaObligatorias prerequisitos) {
		this.prerequisitos = prerequisitos;
	}

}
