package Dominio;

public abstract class Cuenta {
	private String Rut;
	private String Correo;
	private String Contraseña;

	public Cuenta(String rut, String correo, String contraseña) {
		super();
		Rut = rut;
		Correo = correo;
		Contraseña = contraseña;
	}

	public String getRut() {
		return Rut;
	}

	public void setRut(String rut) {
		Rut = rut;
	}

	public String getCorreo() {
		return Correo;
	}

	public void setCorreo(String correo) {
		Correo = correo;
	}

	public String getContraseña() {
		return Contraseña;
	}

	public void setContraseña(String contraseña) {
		Contraseña = contraseña;
	}

}
