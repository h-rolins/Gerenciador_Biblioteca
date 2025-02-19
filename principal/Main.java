package principal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n==== MENU ====");
            System.out.println("1. Opção 1");
            System.out.println("2. Opção 2");
            System.out.println("3. Opção 3");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Você escolheu a Opção 1.");
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
