package modelos;

import servicos.OpLivro;
import servicos.OpRevista;

import java.util.List;
import java.util.Scanner;

public class Revista extends ItemBiblioteca {
    private int edicao;

    public Revista(int id, String titulo, String autor, String editora, String anoPublicacao, int edicao) {
        super(id, titulo, autor, editora, anoPublicacao);
        this.edicao = edicao;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + " | Edição nº " + edicao + ".";
    }

    private static void salvarRevista(Scanner scanner) {
        System.out.print("Digite o ID da Revista: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o título: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite o autor: ");
        String autor = scanner.nextLine();
        System.out.print("Digite a editora: ");
        String editora = scanner.nextLine();
        System.out.print("Digite o ano de publicação: ");
        String anoPublicacao = scanner.nextLine();
        System.out.print("Número da edição: ");
        int edicao = scanner.nextInt();
        scanner.nextLine();


        Revista revista = new Revista(id, titulo, autor, editora, anoPublicacao, edicao);
        OpRevista.saveRevista(revista);
        System.out.println("Revista salva com sucesso!");
    }

    private static void listarRevistas() {
        List<Revista> revistas = OpRevista.loadRevistas();

        if (revistas.isEmpty()) {
            System.out.println("Não há livros cadastrados.");
        } else {
            for (Revista revista : revistas) {
                System.out.println("ID: " + revista.getId() + ", Título: " + revista.getTitulo() +
                        ", Autor: " + revista.getAutor() + ", Editora: " + revista.getEditora() +
                        ", Ano de Publicação: " + revista.getAnoPublicacao() + ", Edição: " + revista.getEdicao());
            }
        }
    }

    private static void atualizarRevista(Scanner scanner) {
        System.out.print("Digite o ID da revista que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        List<Revista> revistas = OpRevista.loadRevistas();
        boolean encontrado = false;

        for (Revista revista : revistas) {
            if (revista.getId() == id) {
                encontrado = true;
                System.out.println("Revista encontrada! Agora, digite os novos dados.");

                System.out.print("Digite o novo título: ");
                revista.setTitulo(scanner.nextLine());
                System.out.print("Digite o novo autor: ");
                revista.setAutor(scanner.nextLine());
                System.out.print("Digite a nova editora: ");
                revista.setEditora(scanner.nextLine());
                System.out.print("Digite o novo ano de publicação: ");
                revista.setAnoPublicacao(scanner.nextLine());
                System.out.print("Digite a nova edição: ");
                revista.setEdicao(scanner.nextInt());
                scanner.nextLine();

                OpRevista.updateRevista(revista);
                System.out.println("Revista atualizada com sucesso!");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Livro não encontrado com o ID informado.");
        }
    }

    private static void excluirRevista(Scanner scanner) {
        System.out.print("Digite o ID da revista que deseja excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        List<Revista> revistas = OpRevista.loadRevistas();
        boolean encontrado = false;

        for (Revista revista : revistas) {
            if (revista.getId() == id) {
                encontrado = true;
                OpRevista.deleteRevista(id);
                System.out.println("Revista excluída com sucesso!");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Revista não encontrada com o ID informado.");
        }
    }

    public static void gerenciarRevistas(Scanner scanner) {
        int opcao;
        do {
            System.out.println("==== MENU ====");
            System.out.println("1. Salvar Revista");
            System.out.println("2. Listar Revistas");
            System.out.println("3. Atualizar Revista");
            System.out.println("4. Excluir Revista");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    salvarRevista(scanner);
                    break;
                case 2:
                    listarRevistas();
                    break;
                case 3:
                    atualizarRevista(scanner);
                    break;
                case 4:
                    excluirRevista(scanner);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }while (opcao != 0);
    }
}
