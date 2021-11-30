package Logica;

import Dominio.Cuenta;

public class ListaCuentas {
	private int cant;
	private int max;
	private Cuenta [] lista;
	
	public ListaCuentas(int max) {
		this.max = max;
		cant = 0;
		this.lista = new Cuenta [max];
	}

	public int getCant() {
		return cant;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public Cuenta[] getLista() {
		return lista;
	}

	public void setLista(Cuenta[] lista) {
		this.lista = lista;
	}
	public boolean agregar(Cuenta o) {
		if (cant < max) {
			lista [cant] = o;
			cant++;
			return true;
		}
		return false;
	}
	public Cuenta getCuenta(int index) {
		return lista[index];
	}
	public Cuenta getCuentaRut(String Rut) {
		for(int i = 0; i < cant ; i++) {
			if(lista[i].getRut().equals(Rut)) {
				return lista[i];
			}
		}
		return null;
	}
}
