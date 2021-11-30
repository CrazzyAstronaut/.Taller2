package Dominio;

public abstract class Cuenta {
	private String Rut;
	private String Correo;
	private String Contrase�a;

	public Cuenta(String rut, String correo, String contrase�a) {
		super();
		Rut = rut;
		Correo = correo;
		Contrase�a = contrase�a;
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

	public String getContrase�a() {
		return Contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		Contrase�a = contrase�a;
	}

}
