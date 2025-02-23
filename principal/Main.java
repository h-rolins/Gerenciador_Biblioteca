package principal;

import java.util.Scanner;

import static modelos.Emprestimo.gerenciarEmprestimos;
import static modelos.Livro.gerenciarLivros;
import static modelos.Revista.gerenciarRevistas;
import static modelos.Usuario.gerenciarUsuarios;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n==== MENU ====");
            System.out.println("1. Gerenciar usuário");
            System.out.println("2. Gerenciar Livros");
            System.out.println("3. Gerenciar Revistas");
            System.out.println("4. Empréstimos e Devoluções");
            System.out.println("5. Sair");
            System.out.println("Insira a opção desejada: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    gerenciarUsuarios(scanner);
                    break;
                case 2:
                    gerenciarLivros(scanner);
                    break;
                case 3:
                    gerenciarRevistas(scanner);
                    break;
                case 4:
                   gerenciarEmprestimos(scanner);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 5);
    }

}
