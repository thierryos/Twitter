package perfis.exceptions;
import java.lang.String;
public class SIException extends Exception{
	private String usuario;
	public SIException(String usuario) {
		super("seguidor = seguido");
		this.usuario = usuario;
	}
	
	public String getMessage() {
		return "O user não pode seguir a se próprio";
	}
	
	public String getUsuario() {
		return this.usuario;
	}	
}