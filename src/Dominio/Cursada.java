package Dominio;

public class Cursada {
	private Asignatura asignatura;
	private double nota;
	private boolean aprobada;

	public Cursada(Asignatura asignatura, double nota) {
		super();
		this.asignatura = asignatura;
		if(nota>=3.95) {
			this.aprobada = true;
		}
		else {
			this.aprobada = false;
		}
		this.nota = Math.round(nota*10.0)/10.0;
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
		if(nota>=3.95) {
			this.aprobada = true;
		}
		else {
			this.aprobada = false;
		}
		this.nota = Math.round(nota*10.0)/10.0;
	}

	public boolean isAprobada() {
		return aprobada;
	}

	public void setAprobada(boolean aprobada) {
		this.aprobada = aprobada;
	}

}
