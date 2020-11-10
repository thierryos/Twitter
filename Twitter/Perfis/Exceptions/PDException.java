package perfis.exceptions;
public class PDException extends Exception {
	private String usuario;
	
	public PDException(String usuario) {
		super("Perfil desativado");
		this.usuario = usuario;
	}
	
	public String getMessage() {
		return "Usuário desativado. user = " + this.usuario + "";
	}
	
	public String getUsuario() {
		return this.usuario;
	}
}