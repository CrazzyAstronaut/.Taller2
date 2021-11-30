package Dominio;

import Logica.ListaCursadas;
import Logica.ListaParalelos;

public class Estudiante extends Cuenta {
	private int Creditos;
	private int Nivel;
	private ListaParalelos AsignaturasActivas;
	private ListaCursadas AsignaturasCursadas;
	public Estudiante(String rut, String correo, String contraseña, int creditos, int nivel) {
		super(rut, correo, contraseña);
		Creditos = creditos;
		Nivel = nivel;
		AsignaturasActivas = new ListaParalelos(999);
		AsignaturasCursadas = new ListaCursadas(100);
	}
	public int getCreditos() {
		return Creditos;
	}
	public void setCreditos(int creditos) {
		Creditos = creditos;
	}
	public int getNivel() {
		return Nivel;
	}
	public void setNivel(int nivel) {
		Nivel = nivel;
	}
	public ListaParalelos getAsignaturasActivas() {
		return AsignaturasActivas;
	}
	public void setAsignaturasActivas(ListaParalelos asignaturasActivas) {
		AsignaturasActivas = asignaturasActivas;
	}
	public ListaCursadas getAsignaturasCursadas() {
		return AsignaturasCursadas;
	}
	public void setAsignaturasCursadas(ListaCursadas asignaturasCursadas) {
		AsignaturasCursadas = asignaturasCursadas;
	}	

}
