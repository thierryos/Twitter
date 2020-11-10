package repositorio.exceptions;
public class UJCException extends Exception {
	String usuario;
	
	public UJCException(String usuario) {
		super("Esse usuário já existe");
		this.usuario = usuario;
	}
	
	public String getMessage() {
		return "Esse user, " + this.usuario + ", está no cadastrado!";
	}
	
	public String getUsuario() {
		return this.usuario;
	}
}