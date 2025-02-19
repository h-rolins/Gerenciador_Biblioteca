package principal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Usuario usuario = new Usuario();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n==== MENU ====");
            System.out.println("1. Gerenciar usuário");
            System.out.println("2. Gerenciar Livros/Revistas");
            System.out.println("3. Empréstimos e Devoluções");
            System.out.println("4. Gerenciamento de Arquivo");
            System.out.println("5. Sair");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    //Usuario.gerenciarUsuario(scanner);
                    break;
                case 2:
                    System.out.println("Você escolheu a Opção 2.");
                    break;
                case 3:
                    System.out.println("Você escolheu a Opção 3.");
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
