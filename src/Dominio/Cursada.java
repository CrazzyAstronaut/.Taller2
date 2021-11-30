package Dominio;

public class Cursada {
	private Asignatura asignatura;
	private double nota;
	private boolean aprobada;

	public Cursada(Asignatura asignatura, double nota, boolean aprobada) {
		super();
		this.asignatura = asignatura;
		this.nota = nota;
		this.aprobada = aprobada;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public boolean isAprobada() {
		return aprobada;
	}

	public void setAprobada(boolean aprobada) {
		this.aprobada = aprobada;
	}

}
