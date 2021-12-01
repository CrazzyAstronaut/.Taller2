package Logica;

public interface UniversitySystem {
		
	public boolean addProfesor(String rut,String correo,String contraseña,int saldo );
	public boolean addParalelo(int numeroParalelo);
	public boolean addEstudiante(String rut,String correo,String contraseña,int nivel);
	public boolean addAsignaturaOb(String nombre,String codigo,int nivel,int creditos);
	public boolean addAsignaturasOp(String nombre,String codigo,int creditos,int creditosNecesarios);
	public void asociarAignaturas(String codigoA,String codigoAP);
	public void asociarProfesorYAsignaturaConParalelo(int numero,String codigAs,String rutPro);
	public void asociarAsigCursConEstudiante(String rut,String codigo,double nota);
	public void asociarEstudianteConParalelo(String rut,String codigoAsig,int numeroPara);
	
	
	
	
	
	
	
}
