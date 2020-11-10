package repositorio;

import perfis.Perfil;
import java.util.Vector;
import repositorio.exceptions.UJCException;
import repositorio.exceptions.UNCException;

public class RepositorioVector implements IRepositorioUsuario {
	private Vector<Perfil> usersRepo;
	
	public RepositorioVector() { 
		usersRepo = new Vector<Perfil>();
	}
	
	public Perfil buscar(String usuario) { 
		for (int i = 0; i < usersRepo.size(); i++) {
			if (usersRepo.get(i).getUsuario().equals(usuario)) {
				return usersRepo.get(i);
			}
		}
		return null;
	}

	public void cadastrar(Perfil usuario) throws UJCException{
		if (buscar(usuario.getUsuario()) == null) { 
			this.usersRepo.add(usuario); 
		}
		else {
			throw new UJCException(usuario.getUsuario()); 
		}
	}

	
	public void atualizar(Perfil usuario) throws UNCException{
		Perfil usuarioVelho = buscar(usuario.getUsuario());
		if (usuarioVelho == null) {
			throw new UNCException(usuario.getUsuario()); 
		} else {
			usuarioVelho = usuario;
		}
	}
}