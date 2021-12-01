package Logica;

import java.io.FileWriter;

public interface UniversitySystem {
	public boolean ingresarProfesor(String rut, String correo, String contraseña, int saldo);

	public boolean ingresarAsignaturaObligatoria(String codigo, String nombre, int credito, int nivel);

	public boolean ingresarAsignaturaOpcional(String codigo, String nombre, int credito, int creditoMin);

	public boolean setPreRequisito(String codigoRamoActual, String codigoRamoPre);

	public boolean ingresarParalelo(int numeroParalelo, String codigo, String rutProfesor);

	public boolean ingresarEstudiante(String rut, String correo, int nivel, String contraseña);

	public boolean ingresarAsignaturaCursada(String rut, String codigoAsignatura, double nota);

	public boolean inscribirAsignatura(String rut, String codigo, int paralelo);

	public boolean guardarEstudiantes(FileWriter file);

	public boolean iniciarSesion(String correo, String contraseña);

	public void ingresarFecha(String fecha);

	public void desplegarAsignaturasInscribir(String correo);

	public void desplegarParalelosAsignatura(String codigo);

	public void desplegarAsignaturasInscritas(String correo);

	public boolean eliminarAsignatura(String correo, String codigo);

	public void desplegarParalelosProfesor(String correo);

	public boolean introducirNotaAsignatura(String correoPro, String rutEst, double nota, String codigo);

	public boolean guardarEstudiantesEgresados(FileWriter file);
}
