package Logica;

import java.io.FileWriter;

public interface UniversitySystem {
	public boolean ingresarProfesor(String rut, String correo, String contraseņa, int saldo);

	public boolean ingresarAsignaturaObligatoria(String codigo, String nombre, int credito, int nivel);

	public boolean ingresarAsignaturaOpcional(String codigo, String nombre, int credito, int creditoMin);

	public boolean ingresarParalelo(int numeroParalelo, String codigo, String rutProfesor);

	public boolean ingresarEstudiante(String rut, String correo, int nivel, String contraseņa);

	public boolean ingresarAsignaturaCursada(String rut, String codigoAsignatura, double nota);

	public boolean setPreRequisito(String codigoRamoActual, String codigoRamoPre);

	public int iniciarSesion(String correo, String contraseņa);

	public String getRut(String correo);

	public void desplegarAsignaturasInscribir(String correo);

	public void desplegarParalelosAsignatura(String codigo);

	public boolean inscribirAsignatura(String rut, String codigo, int paralelo);

	public boolean desplegarAsignaturasInscritas(String correo);

	public boolean eliminarAsignatura(String correo, String codigo);

	public void desplegarParalelosProfesor(String correo);

	public boolean desplegarEstudiantes(String correo, int p);

	public boolean introducirNotaAsignatura(String correoPro, String rutEst, double nota, int indexParalelo);

	public boolean comprobarAsignatura(String correo, String codigo);

	public boolean comprobarAsignaturaCursando(String correo, String codigo);

	public void actualizarNivel(String rut);

	public FileWriter guardarEstudiantes(FileWriter file);

	public FileWriter guardarEstudiantesEgresados(FileWriter file);
}
