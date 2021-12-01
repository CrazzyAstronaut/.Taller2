/**
 * 
 */
package Dominio;

/**
 * @author Claudio Córtes M}
 *
 */
public abstract class Cuenta {
	private String rut,correo,contraseña;
	public Cuenta(String rut,String correo,String contraseña) {
		this.rut=rut;
		this.correo=correo;
		this.contraseña=contraseña;
	}
	/**
	 * @return the rut
	 */
	public String getRut() {
		return rut;
	}
	/**
	 * @param rut the rut to set
	 */
	public void setRut(String rut) {
		this.rut = rut;
	}
	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}
	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	/**
	 * @return the contraseña
	 */
	public String getContraseña() {
		return contraseña;
	}
	/**
	 * @param contraseña the contraseña to set
	 */
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
}
