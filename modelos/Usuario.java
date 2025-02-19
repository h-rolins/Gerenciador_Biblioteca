package modelos;

import servicos.OpUsuario;

import java.util.List;
import java.util.Scanner;

public class Usuario {
    private int id;
    private String nome;
    private String email;

    public Usuario(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getDescricao() {
        return "ID: " + this.id + ", Nome: " + this.nome + ", Email: " + this.email;
    }

    private static void salvarUsuario(Scanner scanner) {
        System.out.print("Digite o ID do usuario: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nome do usuario: ");
        String nome = scanner.nextLine();
        System.out.print("Email do usuario: ");
        String email = scanner.nextLine();

        Usuario usuario = new Usuario(id, nome, email);
        OpUsuario.saveUsuario(usuario);
        System.out.println("Usuário salvo com sucesso!");
    }

    private static void listarUsuarios() {
        List<Usuario> usuarios = OpUsuario.loadUsuarios();

        if (usuarios.isEmpty()) {
            System.out.println("Não há usuários cadastrados.");
        } else {
            for (Usuario usuario : usuarios) {
                System.out.println("ID: " + usuario.getId() + ", Nome: " + usuario.getNome() +  ", Email: " + usuario.getEmail());
            }
        }
    }

    private static void atualizarUsuario(Scanner scanner) {
        System.out.print("Digite o ID do usuário que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        List<Usuario> usuarios = OpUsuario.loadUsuarios();
        boolean encontrado = false;

        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                encontrado = true;
                System.out.println("Usuário encontrado! Agora, digite os novos dados.");

                System.out.print("Digite o novo nome: ");
                usuario.setNome(scanner.nextLine());
                System.out.print("Digite o novo email: ");
                usuario.setEmail(scanner.nextLine());

                OpUsuario.updateUsuario(usuario);
                System.out.println("Usuário atualizado com sucesso!");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Usuário não encontrado com o ID informado.");
        }
    }

    private static void excluirUsuario(Scanner scanner) {
        System.out.print("Digite o ID do usuário que deseja excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        List<Usuario> usuarios = OpUsuario.loadUsuarios();
        boolean encontrado = false;

        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                encontrado = true;
                OpUsuario.deleteUsuario(id);
                System.out.println("Usuário excluído com sucesso!");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Usuário não encontrado com o ID informado.");
        }
    }

    public static void gerenciarUsuarios(Scanner scanner) {
        int opcao;

        do {
            System.out.println("==== MENU ====");
            System.out.println("1. Salvar Usuário");
            System.out.println("2. Listar Usuário");
            System.out.println("3. Atualizar Usuário");
            System.out.println("4. Excluir Usuário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    salvarUsuario(scanner);
                    break;
                case 2:
                    listarUsuarios();
                    break;
                case 3:
                    atualizarUsuario(scanner);
                    break;
                case 4:
                    excluirUsuario(scanner);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

    }
}