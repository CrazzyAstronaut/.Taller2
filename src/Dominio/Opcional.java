package Dominio;

public class Opcional extends Asignatura {
	private int CreditosNecesarios;

	public Opcional(String nombre, String codigo, int creditos, int creditosNecesarios) {
		super(nombre, codigo,creditos);
		CreditosNecesarios = creditosNecesarios;
	}

	public int getCreditosNecesarios() {
		return CreditosNecesarios;
	}

	public void setCreditosNecesarios(int creditosNecesarios) {
		CreditosNecesarios = creditosNecesarios;
	}

}
