package Logica;

import java.io.FileWriter;

import Dominio.Estudiante;
import Dominio.Obligatoria;

public interface UniversitySystem {
	/**
	 * A teacher is entered into the system
	 * 
	 * @param rut
	 * @param correo
	 * @param contraseña
	 * @param saldo
	 * @return
	 */
	public boolean ingresarProfesor(String rut, String correo, String contraseña, int saldo);

	/**
	 * A obligatory course is entered into the system
	 * 
	 * @param codigo
	 * @param nombre
	 * @param credito
	 * @param nivel
	 * @return
	 */
	public boolean ingresarAsignaturaObligatoria(String codigo, String nombre, int credito, int nivel);

	/**
	 * A optional course is entered into the system
	 * 
	 * @param codigo
	 * @param nombre
	 * @param credito
	 * @param creditoMin
	 * @return
	 */
	public boolean ingresarAsignaturaOpcional(String codigo, String nombre, int credito, int creditoMin);

	/**
	 * A parallel is entered into the system
	 * 
	 * @param numeroParalelo
	 * @param codigo
	 * @param rutProfesor
	 * @return
	 */
	public boolean ingresarParalelo(int numeroParalelo, String codigo, String rutProfesor);

	/**
	 * A student is entered into the system
	 * 
	 * @param rut
	 * @param correo
	 * @param nivel
	 * @param contraseña
	 * @return
	 */
	public boolean ingresarEstudiante(String rut, String correo, int nivel, String contraseña);

	/**
	 * A taken course is entered into the system
	 * 
	 * @param rut
	 * @param codigoAsignatura
	 * @param nota
	 * @return
	 */
	public boolean ingresarAsignaturaCursada(String rut, String codigoAsignatura, double nota);

	/**
	 * A prerequisite course is added to another
	 * 
	 * @param codigoRamoActual
	 * @param codigoRamoPre
	 * @return
	 */
	public boolean setPreRequisito(String codigoRamoActual, String codigoRamoPre);

	/**
	 * Depending on the type of account that is found, a menu option will be given
	 * 
	 * @param correo
	 * @param contraseña
	 * @return
	 */
	public int iniciarSesion(String correo, String contraseña);

	/**
	 * Returns the rut that is associated with the mail
	 * 
	 * @param correo
	 * @return
	 */
	public String getRut(String correo);

	/**
	 * Displays the subjects that the Student can enter
	 * 
	 * @param correo
	 */
	public void desplegarAsignaturasInscribir(String correo);

	/**
	 * Displays the parallel of a course
	 * 
	 * @param codigo
	 */
	public void desplegarParalelosAsignatura(String codigo);

	/**
	 * Register a course that will be studied in the semester
	 * 
	 * @param rut
	 * @param codigo
	 * @param paralelo
	 * @return
	 */
	public boolean inscribirAsignatura(String rut, String codigo, int paralelo);

	/**
	 * Displays the student's enrolled courses
	 * 
	 * @param correo
	 * @return
	 */
	public boolean desplegarAsignaturasInscritas(String correo);

	/**
	 * Eliminate the registered course from the list of active subjects
	 * 
	 * @param correo
	 * @param codigo
	 * @return
	 */
	public boolean eliminarAsignatura(String correo, String codigo);

	/**
	 * Displays the parallels that the teacher dictates
	 * @param correo
	 */
	public void desplegarParalelosProfesor(String correo);

	/**
	 * Displays the students of the parallel
	 * @param correo
	 * @param p
	 * @return
	 */
	public boolean desplegarEstudiantes(String correo, int p);

	/**
	 * Displays the students who attend the parallel
	 * 
	 * @param correoPro
	 * @param rutEst
	 * @param nota
	 * @param indexParalelo
	 * @return
	 */
	public boolean introducirNotaAsignatura(String correoPro, String rutEst, double nota, int indexParalelo);

	/**
	 * Check if the student meets the requirements to enroll in the course
	 * 
	 * @param correo
	 * @param codigo
	 * @return
	 */
	public boolean comprobarAsignatura(String correo, String codigo);

	/**
	 * Check if a course is being taken
	 * 
	 * @param correo
	 * @param codigo
	 * @return
	 */
	public boolean comprobarAsignaturaCursando(String correo, String codigo);

	/**
	 * Check if the prerequisites of the obligatory course
	 * 
	 * @param est
	 * @param obl
	 * @return
	 */
	public boolean prerequisitosAprovados(Estudiante est, Obligatoria obl);

	/**
	 * Updates the level of the Student with respect to the courses he has taken
	 * 
	 * @param rut
	 */
	public void actualizarNivel(String rut);

	/**
	 * Saves in a text file the data of the students that have been modified
	 * 
	 * @param file
	 * @return
	 */
	public FileWriter guardarEstudiantes(FileWriter file);

	/**
	 * Saves graduated students in a text file and removes them from the system
	 * 
	 * @param file
	 * @return
	 */
	public FileWriter guardarEstudiantesEgresados(FileWriter file);
}
