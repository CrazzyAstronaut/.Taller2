/**
 * 
 */
package Dominio;

/**
 * @author Claudio C�rtes M}
 *
 */
public abstract class Cuenta {
	private String rut,correo,contrase�a;
	public Cuenta(String rut,String correo,String contrase�a) {
		this.rut=rut;
		this.correo=correo;
		this.contrase�a=contrase�a;
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
	 * @return the contrase�a
	 */
	public String getContrase�a() {
		return contrase�a;
	}
	/**
	 * @param contrase�a the contrase�a to set
	 */
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	
}
