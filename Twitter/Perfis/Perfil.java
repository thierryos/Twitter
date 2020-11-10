package perfis;

import java.lang.String;
import java.util.Vector;
import perfis.exceptions.SIException;
import twitter.Tweet;

public abstract class Perfil {
	private String usuario;
	private Vector<String> seguidores;
  private Vector<String> seguidos;
	private Vector<Tweet> timeline;
	private boolean ativo = false;
	
	public Perfil(String usuario) { 
		this.setUsuario(usuario);
		this.seguidores = new Vector<String>();
    this.seguidos = new Vector<String>();
		this.timeline = new Vector<Tweet>();
		this.setAtivo(true);
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	} 

	public void setAtivo(boolean valor) {
		this.ativo = valor;
	} 
	
	public void addSeguidor(String usuario) throws SIException {
		if (!this.usuario.equals(usuario)) {
			seguidores.add(usuario); 
		} else { 
			throw new SIException(usuario);
		}
	}

   public void addSeguido(String usuario) throws SIException {
		if (!this.usuario.equals(usuario)) {
			seguidos.add(usuario);
		} else {
			throw new SIException(usuario);
		}
	}
	
	public void addTweet(Tweet tweet) { 
		this.timeline.add(tweet);
	}
	
	
	public String getUsuario() {
		return this.usuario;
	}
	
	public Vector<String> getSeguidores() {
		return this.seguidores;
	}

  public Vector<String> getSeguidos() {
		return this.seguidos;
	}
	
	public Vector<Tweet> getTimeline() {
		return this.timeline;
	}
	
	public boolean isAtivo() {
		return this.ativo;
	}
}