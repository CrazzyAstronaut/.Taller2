/**
 * 
 */
package Logica;

import Dominio.Asignatura;
import Dominio.AsignaturaOB;
import Dominio.AsignaturaOP;
import Dominio.Cuenta;
import Dominio.Cursada;
import Dominio.Estudiante;
import Dominio.Paralelo;
import Dominio.Profesor;

/**
 * @author Claudio Córtes M}
 *
 */
public class UniversitySystemImpl implements UniversitySystem {
	private Cuentas cuentas;
	private Asignaturas asignaturas;
	private Paralelos paralelos;
	public UniversitySystemImpl() {
		this.asignaturas= new Asignaturas(100000);
		this.cuentas= new Cuentas(10000);
		this.paralelos= new Paralelos(10000);
	}
	@Override
	public boolean addProfesor(String rut, String correo, String contraseña, int saldo) {
		Profesor pro= new Profesor(rut,correo,contraseña,saldo);
		if (cuentas.addCuenta(pro)) {
			return true;
		}
		return false;
	}
	public boolean addParalelo(int numeroParalelo) {
		Paralelo paralelo= new Paralelo(numeroParalelo);
		if(paralelos.addParalelo(paralelo)) {
			return true;
		}
		return false;
	}
	public void asociarProfesorYAsignaturaConParalelo(int numero, String codigAs, String rutPro) {
		Paralelo pa= paralelos.getParalelo(numero);
		Asignatura asig= asignaturas.getAsignatura(codigAs);
		Profesor pro= (Profesor)cuentas.getCuentar(rutPro);
		if(pa==null) {
			throw new NullPointerException("No existe paralelo");
		}else if(asig ==null) {
			throw new NullPointerException("No existe Asigntura");
		}else if(pro ==null) {
			throw new NullPointerException("No existe profesor");
		}else {
			pa.setAsignatura(asig);
			pa.setProfesor(pro);
			
		}
		
	}
	public boolean addEstudiante(String rut, String correo, String contraseña, int nivel) {
		Estudiante estu=new Estudiante(rut,correo,contraseña,nivel);
		if(cuentas.addCuenta(estu)) {
			return true;
		}
		return false;
	}
	public boolean addAsignaturaOb(String nombre, String codigo, int nivel, int creditos) {
		AsignaturaOB a= new AsignaturaOB(nombre,codigo,nivel,creditos);
		if(asignaturas.addAsignatura(a)) {
			return true;
		}
		return false;
	}
	public boolean addAsignaturasOp(String nombre, String codigo, int creditos,int creditosNecesarios) {
		AsignaturaOP a= new AsignaturaOP(nombre,codigo,creditos,creditosNecesarios);
		if(asignaturas.addAsignatura(a)) {
			return true;
		}
		return false;
	}
	public void asociarAignaturas(String codigoA, String codigoAP) {
		Asignatura AB= asignaturas.getAsignatura(codigoA);
		Asignatura AP=asignaturas.getAsignatura(codigoAP);
		if(AB== null) {
			throw new NullPointerException("No exite asignatura1");
		}else if(AP==null) {
			throw new NullPointerException("No exite asignatura2");
		
		}else if(AB instanceof AsignaturaOB) {
			AsignaturaOB a= (AsignaturaOB)AB;
			
			if( AP instanceof AsignaturaOB) {
				a.getAsignaturas().addAsignatura(AP);
			}
			else if( AP instanceof AsignaturaOP) {
				a.getAsignaturas().addAsignatura(AP);
			}
			
		}
		
	}
	public void asociarAsigCursConEstudiante(String rut, String codigo, double nota) {
		Estudiante estu=(Estudiante)cuentas.getCuentar(rut);
		Asignatura asig=asignaturas.getAsignatura(codigo);
		Cursada cursa= new Cursada(nota);
		if(estu==null) {
			throw new NullPointerException("No existe estudiante");
		}else if(asig==null) {
			throw new NullPointerException("No existe asignatura");
		}
		cursa.setAsignatura(asig);
		estu.getAsignaturasCursadas().addCursada(cursa);
		
		
	}
	public void asociarEstudianteConParalelo(String rut, String codigoAsig, int numeroPara) {
		Estudiante estu= (Estudiante)cuentas.getCuentar(rut);
		Paralelo paralelo= paralelos.getParalelo(numeroPara);
		if(estu==null) {
			throw new NullPointerException("El estudiante no exite");
		}else if(paralelo==null && !paralelo.getAsignatura().getCodigo().equals(codigoAsig)) {
			throw new NullPointerException("El paralelo no exite");
		}else {
			estu.getAsignaturasActivas().addParalelo(paralelo);
		}
		
	}
	

}
