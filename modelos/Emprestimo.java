package modelos;

import servicos.OpEmprestimo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Emprestimo {
    private int idEmprestimo;
    private int idUsuario;
    private String tipoItem;
    private int idItem;
    private Date dataEmprestimo;
    private Date dataDevolucao;

    public Emprestimo(int idEmprestimo, int idUsuario, String tipoItem, int idItem, Date dataEmprestimo, Date dataDevolucao) {
        this.idEmprestimo = idEmprestimo;
        this.idUsuario = idUsuario;
        this.tipoItem = tipoItem;
        this.idItem = idItem;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getTipoItem() {
        return tipoItem;
    }

    public int getIdItem() {
        return idItem;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public static void gerenciarEmprestimos(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n===== Menu de Empréstimos =====");
            System.out.println("1. Registrar Empréstimo");
            System.out.println("2. Devolver Item");
            System.out.println("3. Listar Empréstimos");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida! Digite um número.");
                scanner.next();
            }
            opcao = scanner.nextInt();
            scanner.nextLine();

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
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 4);
    }

    private static void registrarEmprestimoMenu(Scanner scanner) {
        System.out.print("Digite o ID do Usuário: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o tipo de item (livro/revista): ");
        String tipoItem = scanner.nextLine();
        System.out.print("Digite o ID do Item: ");
        int idItem = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite a data de devolução (Formato: dd/MM/yyyy): ");
        String dataDevolucaoStr = scanner.nextLine();

        Date dataDevolucao = new Date();
        OpEmprestimo.registrarEmprestimo(idUsuario, tipoItem, idItem, dataDevolucao);
    }

    private static void devolverItemMenu(Scanner scanner) {
        System.out.print("Digite o ID do Empréstimo para devolução: ");
        int idEmprestimo = scanner.nextInt();
        scanner.nextLine();
        OpEmprestimo.devolverItem(idEmprestimo);
    }
}
