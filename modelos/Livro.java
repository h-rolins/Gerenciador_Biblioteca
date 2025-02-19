package modelos;

import servicos.OpLivro;

import java.util.List;
import java.util.Scanner;

public class Livro extends ItemBiblioteca {
    private int numPag;

    public Livro(int id, String titulo, String autor, String editora, String anoPublicacao, int numPag) {
        super(id, titulo, autor, editora, anoPublicacao);
        this.numPag = numPag;
    }

    public int getNumPag() {
        return numPag;
    }

    public void setNumPag(int numPag) {
        this.numPag = numPag;
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + " | " + numPag + " páginas.";
    }

    private static void salvarLivro(Scanner scanner) {
        System.out.print("Digite o ID do livro: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite o autor do livro: ");
        String autor = scanner.nextLine();
        System.out.print("Digite a editora do livro: ");
        String editora = scanner.nextLine();
        System.out.print("Digite o ano de publicação do livro: ");
        String anoPublicacao = scanner.nextLine();
        System.out.print("Digite o número de páginas do livro: ");
        int numPag = scanner.nextInt();
        scanner.nextLine();

        Livro livro = new Livro(id, titulo, autor, editora, anoPublicacao, numPag);
        OpLivro.saveLivro(livro);
        System.out.println("Livro salvo com sucesso!");
    }

    private static void listarLivros() {
        List<Livro> livros = OpLivro.loadLivros();

        if (livros.isEmpty()) {
            System.out.println("Não há livros cadastrados.");
        } else {
            for (Livro livro : livros) {
                System.out.println("ID: " + livro.getId() + ", Título: " + livro.getTitulo() +
                        ", Autor: " + livro.getAutor() + ", Editora: " + livro.getEditora() +
                        ", Ano de Publicação: " + livro.getAnoPublicacao() + ", Páginas: " + livro.getNumPag());
            }
        }
    }

    private static void atualizarLivro(Scanner scanner) {
        System.out.print("Digite o ID do livro que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        List<Livro> livros = OpLivro.loadLivros();
        boolean encontrado = false;

        for (Livro livro : livros) {
            if (livro.getId() == id) {
                encontrado = true;
                System.out.println("Livro encontrado! Agora, digite os novos dados.");

                System.out.print("Digite o novo título: ");
                livro.setTitulo(scanner.nextLine());
                System.out.print("Digite o novo autor: ");
                livro.setAutor(scanner.nextLine());
                System.out.print("Digite a nova editora: ");
                livro.setEditora(scanner.nextLine());
                System.out.print("Digite o novo ano de publicação: ");
                livro.setAnoPublicacao(scanner.nextLine());
                System.out.print("Digite o novo número de páginas: ");
                livro.setNumPag(scanner.nextInt());
                scanner.nextLine();

                OpLivro.updateLivro(livro);
                System.out.println("Livro atualizado com sucesso!");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Livro não encontrado com o ID informado.");
        }
    }

    private static void excluirLivro(Scanner scanner) {
        System.out.println("Você escolheu a opção 4 (Excluir Livro).");

        // Solicita o ID do livro a ser excluído
        System.out.print("Digite o ID do livro que deseja excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpa o buffer

        List<Livro> livros = OpLivro.loadLivros();
        boolean encontrado = false;

        for (Livro livro : livros) {
            if (livro.getId() == id) {
                encontrado = true;
                // Exclui o livro
                OpLivro.deleteLivro(id);
                System.out.println("Livro excluído com sucesso!");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Livro não encontrado com o ID informado.");
        }
    }

    public static void gerenciarLivros(Scanner scanner) {
        int opcao;

        do {
            System.out.println("==== MENU ====");
            System.out.println("1. Salvar Livro");
            System.out.println("2. Listar Livros");
            System.out.println("3. Atualizar Livro");
            System.out.println("4. Excluir Livro");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    salvarLivro(scanner);
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    atualizarLivro(scanner);
                    break;
                case 4:
                    excluirLivro(scanner);
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
