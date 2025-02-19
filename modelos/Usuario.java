package modelos;

import servicos.OpUsuario;

import java.util.List;
import java.util.Scanner;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;

    public Usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public String getDescricao() {
        return "ID: " + this.id + ", Nome: " + this.nome + ", Email: " + this.email;
    }

    public static void cadastrarUsuario(Scanner scanner) {
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o e-mail do usuário: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha do usuário: ");
        String senha = scanner.nextLine();

        int novoId = OpUsuario.loadUsuarios().size() + 1; // Gera um ID único
        Usuario novoUsuario = new Usuario(novoId, nome, email, senha);
        OpUsuario.saveUsuario(novoUsuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public static void listarUsuarios() {
        List<Usuario> usuarios = OpUsuario.loadUsuarios();

        if (usuarios.isEmpty()) {
            System.out.println("Não há usuários cadastrados.");
        } else {
            System.out.println("\n===== Lista de Usuários =====");
            for (Usuario usuario : usuarios) {
                System.out.println(usuario.getDescricao());
            }
        }
    }

    public static void alterarDadosUsuario(Scanner scanner) {
        System.out.print("Digite o ID do usuário que deseja alterar: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine(); // Consome o '\n' pendente após nextInt()

        List<Usuario> usuarios = OpUsuario.loadUsuarios();
        Usuario usuario = null;

        for (Usuario u : usuarios) {
            if (u.getId() == idUsuario) {
                usuario = u;
                break;
            }
        }

        if (usuario != null) {
            System.out.print("Digite o novo nome do usuário (atual: " + usuario.getNome() + "): ");
            String novoNome = scanner.nextLine();
            System.out.print("Digite o novo e-mail do usuário (atual: " + usuario.getEmail() + "): ");
            String novoEmail = scanner.nextLine();
            System.out.print("Digite a nova senha do usuário (atual: " + usuario.getSenha() + "): ");
            String novaSenha = scanner.nextLine();

            usuario = new Usuario(usuario.getId(), novoNome, novoEmail, novaSenha);
            OpUsuario.updateUsuario(usuario);
            System.out.println("Dados do usuário atualizados com sucesso!");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public static void removerUsuario(Scanner scanner) {
        System.out.print("Digite o ID do usuário que deseja remover: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine(); // Consome o '\n' pendente após nextInt()

        OpUsuario.deleteUsuario(idUsuario);
        System.out.println("Usuário removido com sucesso!");
    }
    public static void gerenciarUsuarios(Scanner scanner) {
        System.out.println("\n=== Gerenciamento de Usuários ===");
        System.out.println("1. Cadastrar usuário");
        System.out.println("2. Listar usuários");
        System.out.println("3. Alterar dados do usuário");
        System.out.println("4. Remover usuário");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                cadastrarUsuario(scanner);
                break;
            case 2:
                listarUsuarios();
                break;
            case 3:
                alterarDadosUsuario(scanner);
                break;
            case 4:
                removerUsuario(scanner);
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
}