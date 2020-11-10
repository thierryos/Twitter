package twitter;
import java.util.Vector;
import perfis.Perfil;
import perfis.exceptions.PDException;
import perfis.exceptions.PEException;
import perfis.exceptions.PIException;
import perfis.exceptions.SIException;
import repositorio.RepositorioVector;
import repositorio.exceptions.UJCException;
import twitter.exceptions.MFPException;
public class MyTwitter implements ITwitter {
	RepositorioVector repositorio = new RepositorioVector();
	public void criarPerfil(Perfil usuario) throws PEException {
		try {
			this.repositorio.cadastrar(usuario);
			System.out.println("Usuário cadastrado com sucesso!");
		} catch (UJCException ujce) {
			throw new PEException(usuario.getUsuario());
		}
	}

  public boolean existe(String usuario){
    Perfil perfil = this.repositorio.buscar(usuario);
    if (perfil != null){
      return true;
    }
    else{
      return false;
    }
  }

	public void cancelarPerfil(String usuario) throws PIException, PDException {
		Perfil perfil = this.repositorio.buscar(usuario);
		if (perfil != null) {
			if (perfil.isAtivo()) {
				perfil.setAtivo(false);
				System.out.println("Perfil desativado com sucesso!");
			}
			else {
				throw new PDException(usuario); 
			}
		} else {
			throw new PIException(usuario); 
		}
	}

	public void tweetar(String usuario, String mensagem) throws PIException, MFPException, PDException{
		if(mensagem.length() < 150 && mensagem.length() > 1) { 
			Tweet tweet = new Tweet(usuario, mensagem); 
			Perfil perfil = this.repositorio.buscar(usuario); 
			if(perfil != null) {
				if(perfil.isAtivo()) {
					perfil.addTweet(tweet);
					Vector<String> seguidores = perfil.getSeguidores(); 
					for (String seguidor : seguidores) {
						this.repositorio.buscar(seguidor).addTweet(tweet); 
					}
					System.out.println("Mensagem tweetada com sucesso!");
				}
        else {
					throw new PDException(usuario); 
				}
			}
			else {
				throw new PIException(usuario); 
			}
		}
		else {
			throw new MFPException(mensagem); 
		}
	}

	public Vector<Tweet> timeline(String usuario) throws PIException, PDException {
		Perfil perfil = this.repositorio.buscar(usuario); 
		if (perfil != null) {
			if (perfil.isAtivo()) {
				return perfil.getTimeline(); 
			} else {
				throw new PDException(usuario); 
			}
		}
		else {
			throw new PIException(usuario);
		}
	}

	public Vector<Tweet> tweets(String usuario) throws PIException, PDException {
		Vector<Tweet> vetorTweets = new Vector<Tweet>();
		Perfil perfil = this.repositorio.buscar(usuario);
		if(perfil != null) {
			if(perfil.isAtivo()) {
				for (Tweet tweet : perfil.getTimeline()) {
					if (tweet.getUsuario().equals(usuario)) {
						vetorTweets.add(tweet);
					}
				}
				return vetorTweets;
			} else {
				throw new PDException(usuario); 
			}
		} else {
			throw new PIException(usuario); 
		}
	}

	public void seguir(String seguidor, String seguido) throws PIException, PDException, SIException {
	
			Perfil perfilSeguido = this.repositorio.buscar(seguido);
			Perfil perfilSeguidor = this.repositorio.buscar(seguidor);
			if (perfilSeguido != null && perfilSeguidor != null) {
				if (perfilSeguido.isAtivo() && perfilSeguidor.isAtivo()) {
					perfilSeguido.addSeguidor(seguidor);
          perfilSeguidor.addSeguido(seguido);
					for (Tweet tweet : tweets(seguido)) {
						perfilSeguidor.addTweet(tweet);
					}
					System.out.println("\nUsuário seguido com sucesso!");
					System.out.println("A partir de agora, você também pode ver o que foi tweetado por " + seguido + ".");
				} else {
					if (!perfilSeguido.isAtivo()) {
						throw new PDException(seguido);
					} else {
						throw new PDException(seguidor);
					}
				}
			} else {
				if (perfilSeguido == null) {
					throw new PIException(seguido);
				} else {
					throw new PIException(seguidor);
				}
			}
	
	}

	public int numeroSeguidores(String usuario) throws PIException, PDException {
		Perfil perfil = this.repositorio.buscar(usuario);
		if (perfil != null) {
			if (perfil.isAtivo()) {
				return perfil.getSeguidores().size();
			} else {
				throw new PDException(usuario);
			}
		} else {
			throw new PIException(usuario);
		}
	}

	public Vector<Perfil> seguidores(String usuario) throws PIException, PDException {
		Perfil perfil = this.repositorio.buscar(usuario);
		if (perfil != null) {
			if (perfil.isAtivo()) {
				Vector<Perfil> seguidores = new Vector<Perfil>();
				for (String seguidor : perfil.getSeguidores()) {
					seguidores.add(this.repositorio.buscar(seguidor));
				}
				return seguidores;
			} else {
				throw new PDException(usuario);
			}
		} else {
			throw new PIException(usuario);
		}
	}

  public Vector<Perfil> seguidos(String usuario) throws PIException, PDException {
		Perfil perfil = this.repositorio.buscar(usuario);
		if (perfil != null) {
			if (perfil.isAtivo()) {
				Vector<Perfil> seguidos = new Vector<Perfil>();
				for (String seguido : perfil.getSeguidos()) {
					seguidos.add(this.repositorio.buscar(seguido));
				}
				return seguidos;
			} else {
				throw new PDException(usuario);
			}
		} else {
			throw new PIException(usuario);
		}
	}
}