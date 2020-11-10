package perfis.exceptions;

public class PIException extends Exception {
	private String usuario;
	
	public PIException(String usuario) {
		super("Não há usuário");
		this.usuario = usuario;
	}
	
	public String getMessage() {
		return "Este usuário não existe. user= " + this.usuario + "";
	}
	
	public String getUsuario() {
		return this.usuario;
	}
}