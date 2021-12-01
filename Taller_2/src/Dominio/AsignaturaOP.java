/**
 * 
 */
package Dominio;

/**
 * @author Claudio Córtes M}
 *
 */
public class AsignaturaOP extends Asignatura{
	private int creditosNecesarios;
	
	public AsignaturaOP(String nombre,String codigo,int creditos,int creditosNecesarios) {
		super(nombre,codigo,creditos);
		this.creditosNecesarios=creditosNecesarios;
	}
	/**
	 * @return the creditosNecesarios
	 */
	public int getCreditosNecesarios() {
		return creditosNecesarios;
	}
	/**
	 * @param creditosNecesarios the creditosNecesarios to set
	 */
	public void setCreditosNecesarios(int creditosNecesarios) {
		this.creditosNecesarios = creditosNecesarios;
	}
	
	
}
