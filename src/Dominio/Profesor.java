package Dominio;

import Logica.ListaParalelos;

public class Profesor extends Cuenta {
	private int Saldo;
	private ListaParalelos ParalelosAsignados;

	public Profesor(String rut, String correo, String contraseña, int saldo) {
		super(rut, correo, contraseña);
		Saldo = saldo;
		ParalelosAsignados = new ListaParalelos(4);
	}

	public int getSaldo() {
		return Saldo;
	}

	public void setSaldo(int saldo) {
		Saldo = saldo;
	}

	public ListaParalelos getParalelosAsignados() {
		return ParalelosAsignados;
	}

	public void setParalelosAsignados(ListaParalelos paralelosAsignados) {
		ParalelosAsignados = paralelosAsignados;
	}

}
