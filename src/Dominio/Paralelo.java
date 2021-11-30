package Dominio;

import Logica.ListaEstudiantes;

public class Paralelo {
	private int Numero;
	private Asignatura asignatura;
	private Profesor profesor;
	private ListaEstudiantes estudiantes;

	public Paralelo(int numero) {
		super();
		Numero = numero;
		this.asignatura = null;
		this.profesor = null;
		this.estudiantes = new ListaEstudiantes(100);
	}

	public int getNumero() {
		return Numero;
	}

	public void setNumero(int numero) {
		Numero = numero;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public ListaEstudiantes getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(ListaEstudiantes estudiantes) {
		this.estudiantes = estudiantes;
	}

}
