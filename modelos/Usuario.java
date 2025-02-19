import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuario {
    private static List<Usuario> usuarios = new ArrayList<>(); // Lista para armazenar os usuários
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

    public static List<Usuario> getUsuarios() {
        return usuarios;
    }

    public static void cadastrarUsuario(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        int novoId = usuarios.size() + 1; // Gera um ID único para o novo usuário
        Usuario novoUsuario = new Usuario(novoId, nome, email, senha);
        usuarios.add(novoUsuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public static void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Não há usuários cadastrados.");
        } else {
            System.out.println("\n===== Lista de Usuários =====");
            for (Usuario usuario : usuarios) {
                System.out.println("ID: " + usuario.getId() + " | Nome: " + usuario.getNome() + " | E-mail: " + usuario.getEmail());
            }
        }
    }

    public static void alterarDadosUsuario(Scanner scanner) {
        System.out.print("Digite o ID do usuário que deseja alterar: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine(); // Consome o '\n' pendente após nextInt()

        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (usuario != null) {
            System.out.print("Digite o novo nome do usuário (atual: " + usuario.getNome() + "): ");
            String novoNome = scanner.nextLine();
            System.out.print("Digite o novo e-mail do usuário (atual: " + usuario.getEmail() + "): ");
            String novoEmail = scanner.nextLine();
            System.out.print("Digite a nova senha do usuário (atual: " + usuario.getSenha() + "): ");
            String novaSenha = scanner.nextLine();

            usuario.nome = novoNome;
            usuario.email = novoEmail;
            usuario.senha = novaSenha;

            System.out.println("Dados do usuário atualizados com sucesso!");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public static void removerUsuario(Scanner scanner) {
        System.out.print("Digite o ID do usuário que deseja remover: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine(); // Consome o '\n' pendente após nextInt()

        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (usuario != null) {
            usuarios.remove(usuario);
            System.out.println("Usuário removido com sucesso!");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    private static Usuario buscarUsuarioPorId(int idUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == idUsuario) {
                return usuario;
            }
        }
        return null;
    }

    public static void gerenciarUsuarios(Scanner scanner) {
        System.out.println("\n=== Gerenciamento de Usuários ===");
        System.out.println("1. Cadastrar usuário");
        System.out.println("2. Listar usuários");
        System.out.println("3. Alterar dados do usuário");
        System.out.println("4. Remover usuário");

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
