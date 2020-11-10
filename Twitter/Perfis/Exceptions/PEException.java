package perfis.exceptions;
public class PEException extends Exception {
	private String usuario;
	
	public PEException(String usuario) {
		super("Esse perfil já existe");
		this.usuario = usuario;
	}
	
	public String getMessage() {
		return "Esse usuário já existe. user = " + this.usuario + "";
	}
	
	public String getUsuario() {
		return this.usuario;
	}
}