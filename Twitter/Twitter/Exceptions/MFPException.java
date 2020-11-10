package twitter.exceptions;
public class MFPException extends Exception {
	private String mensagem;
	public MFPException(String mensagem) {
		super("Mensagem não está dentro dos padrões");
		this.mensagem = mensagem;
	}
	
	public String getMessage() {
		if (mensagem.length() < 1) {
			return "Mensagem está muito curta. O tweet deve, no mínimo, possuir 1-150 carácteres.";
		} else {
			return "Mensagem muito longa. O tweet deve, no máximo, possuir 1-150 carácteres.";
		}
	}
	
	public String getMensagemEnviada() {
		return this.mensagem;
	}
}