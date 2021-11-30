package Dominio;

import Logica.ListaParalelos;

public abstract class Asignatura {

	private String Nombre;
	private String Codigo;
	private int Creditos;
	private ListaParalelos paralelos;

	public Asignatura(String nombre, String codigo, int creditos) {

		Nombre = nombre;
		Codigo = codigo;
		Creditos = creditos;
		paralelos = new ListaParalelos(999);
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getCodigo() {
		return Codigo;
	}

	public void setCodigo(String codigo) {
		Codigo = codigo;
	}

	public ListaParalelos getParalelos() {
		return paralelos;
	}

	public void setParalelos(ListaParalelos paralelos) {
		this.paralelos = paralelos;
	}

	public int getCreditos() {
		return Creditos;
	}

	public void setCreditos(int creditos) {
		Creditos = creditos;
	}

}
