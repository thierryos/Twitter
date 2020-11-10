import java.util.Scanner;

import perfis.*;
import perfis.exceptions.PEException;
import twitter.*;
import repositorio.exceptions.UNCException;

public class Main {
	public static void telaInicial() {
		System.out.print("\n");
		System.out.println("▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪");
		System.out.println("   Painel Twitter    ");
		System.out.println("▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪");
		System.out.print(" |1)Criar um perfil \n |2)Desativar um perfil \n |3)Acessar seu perfil \n |4)Fechar Twitter \n\nDigite sua opção: ");
	}
  public static void painel_User(String usuario) {
		System.out.println(usuario);
		System.out.println("\n▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪");
		System.out.println("   Painel Usuário    ");
		System.out.println("▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪");
		System.out.println("|1)Tweetar\n|2)Ver sua timeline\n|3)Ver seus tweets\n|4)Seguir\n|5)Verificar número de seguidores\n|6)Listar seus seguidores\n|7)Ver quem te segue\n|8)Sair\n|9)Fechar\n");
		System.out.print("Escolha uma opção: ");
	}
	public static void main(String[] args) {
		String usuario;
		MyTwitter twitter = new MyTwitter();
		Scanner ler = new Scanner(System.in); 
		String entrada = "5";
		while(entrada != "4") { 
			telaInicial(); 
			entrada = ler.nextLine(); 
			switch (entrada) { 
        case "1": 
          String personType = "0";
          boolean opInvalida = false;
          while(personType.charAt(0) != '1' && personType.charAt(0) != '2') {
            if (opInvalida) {
              System.out.println("Error - Essa opção não é válida!");
            } 
            System.out.println("Escolha uma opção: \n 1)Pessoa Física \n 2)Pessoa Jurídica");
            personType = ler.nextLine();
            opInvalida = true; 
          }
          System.out.print("Usuário: ");
          usuario = ler.nextLine();
          Perfil pessoa;
          if (personType.charAt(0) == '1') { 
            System.out.print("Digite seu CPF: "); 
            long cpf = ler.nextLong();
            ler.nextLine();
            pessoa = new PessoaFisica(usuario, cpf);
            
          } else { 
            System.out.print("Digite seu CNPJ: ");
            long cnpj = ler.nextLong(); 
            ler.nextLine();
            pessoa = new PessoaJuridica(usuario, cnpj);
          }
          try { 
            twitter.criarPerfil(pessoa);
          } catch (PEException pee) { 
            System.out.println("Erro! " + pee.getMessage());
          } 
          break;

        case "2": 
          System.out.print("Usuário: ");
          usuario = ler.nextLine();
          try {
            twitter.cancelarPerfil(usuario);
          } catch (Exception e) { 
            System.out.println("Error! " + e.getMessage());
          }
          break;
        case "3":
            System.out.print("Usuário: ");
            usuario = ler.nextLine();
            boolean sair = false;
            if(twitter.existe(usuario) == true){
              while (entrada != "7" && sair == false) { 
                painel_User(usuario);
                entrada = ler.nextLine();
                switch (entrada) {
                  case "1": 
                    String mensagem;
                    System.out.println("Digite seu tweet:");
                    mensagem = ler.nextLine();
                    try { 
                      twitter.tweetar(usuario, mensagem);
                    } catch (Exception e) {
                      System.out.println("");
                      System.out.println("Error! " + e.getMessage());
                    } 
                    break;
                  case "2": 
                    try {
                      for (Tweet tweet : twitter.timeline(usuario)) {
                        System.out.println(tweet.getMensagem());
                      }
                    } catch (Exception e) {
                      System.out.println("");
                      System.out.println("Error! " + e.getMessage());
                    }
                    break;
                  case "3": 
                    try {
                      for (Tweet tweet : twitter.tweets(usuario)) {
                        System.out.println(tweet.getMensagem());
                      }
                    } catch(Exception e) {
                      System.out.println("Error! " + e.getMessage());
                    }
                    break;
                  case "4":
                    String usuarioSeguido;
                    System.out.println("Seguir:");
                    usuarioSeguido = ler.nextLine();
                    try {
                      twitter.seguir(usuario, usuarioSeguido);
                    } catch (Exception e) {
                      System.out.println("Error! " + e.getMessage());
                    }			
                    break;
                  case "5":
                    try {
                      System.out.println("Você tem " + twitter.numeroSeguidores(usuario) + " seguidor(es).");
                    } catch (Exception e) {
                      System.out.println("");
                      System.out.println("Error! " + e.getMessage());
                    }
                    break;
                  case "6": 
                    try {
                      for (Perfil seguidor : twitter.seguidores(usuario)) {
                        System.out.println(seguidor.getUsuario());
                      }
                    } catch (Exception e) {
                      System.out.println("Error! " + e.getMessage());
                    }
                    break;
                  case "7": 
                    try {
                    System.out.println("\nSeus seguidos são");
                    for (Perfil seguido : twitter.seguidos(usuario)) {
                      System.out.println(seguido.getUsuario());
                    }
                  } catch (Exception e) {
                    System.out.println("\nError! " + e.getMessage());
                  }
                  break;
                  case "8": 
                    sair = true;
                    break;
                  case "9":
                    System.exit(0);
                    break;
                  default:
                    System.out.println("Error - Opção Invalida!");
                    break;
                }
              }
            }
            else{
              System.out.println("Usuário não existe!");
            }
            break;
        case "4": 
          System.exit(0);
          break;
        default: 
          System.out.println("Opção Invalida!");
          break;
			}
		}
		ler.close();
	}
}