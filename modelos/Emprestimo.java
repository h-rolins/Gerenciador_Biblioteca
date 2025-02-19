package servicos;

import modelos.Livro;
import modelos.Revista;

import java.util.Date;
import java.util.Scanner;

public class Emprestimo {

    int idEmprestimo;
    int idUsuario;
    String tipoItem;
    int idItem;
    Date dataEmprestimo;
    Date dataDevolucao;

    public Emprestimo(int idEmprestimo, int idUsuario, String tipoItem, int idItem, Date dataEmprestimo, Date dataDevolucao) {
        this.idEmprestimo = idEmprestimo;
        this.idUsuario = idUsuario;
        this.tipoItem = tipoItem;
        this.idItem = idItem;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Menu de Empréstimos =====");
            System.out.println("1. Registrar Empréstimo");
            System.out.println("2. Devolver Item");
            System.out.println("3. Listar Emprestimos");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    registrarEmprestimoMenu(scanner);
                    break;
                case 2:
                    devolverItemMenu(scanner);
                    break;
                case 3:
                    OpEmprestimo.listarEmprestimos();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    // Registrar Empréstimo pelo menu
    private static void registrarEmprestimoMenu(Scanner scanner) {
        System.out.print("Digite o ID do Usuário: ");
        int idUsuario = scanner.nextInt();
        System.out.print("Digite o tipo de item (livro/revista): ");
        String tipoItem = scanner.next();
        System.out.print("Digite o ID do Item: ");
        int idItem = scanner.nextInt();

        if (OpEmprestimo.verificarDisponibilidade(tipoItem, idItem)) {
            System.out.print("Digite a data de devolução (Formato: dd/MM/yyyy): ");
            String dataDevolucaoStr = scanner.next();
            Date dataDevolucao = new Date();
            OpEmprestimo.registrarEmprestimo(idUsuario, tipoItem, idItem, dataDevolucao);
        } else {
            System.out.println("O item não está disponível para empréstimo.");
        }
    }

    // Devolver Item pelo menu
    private static void devolverItemMenu(Scanner scanner) {
        System.out.print("Digite o ID do Empréstimo para devolução: ");
        int idEmprestimo = scanner.nextInt();
        OpEmprestimo.devolverItem(idEmprestimo);
    }

    public static void main(String[] args) {
        // Iniciar o menu
        menu();
    }
}
