package servicos;

import modelos.Livro;
import modelos.Revista;
import servicos.Emprestimo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OpEmprestimo {
    private static final List<Emprestimo> emprestimos = new ArrayList<>();

    public static void registrarEmprestimo(int idUsuario, String tipoItem, int idItem, Date dataDevolucao) {
        int idEmprestimo = emprestimos.size() + 1; // Gera um ID único
        Date dataEmprestimo = new Date(); // Data atual
        Emprestimo novoEmprestimo = new Emprestimo(idEmprestimo, idUsuario, tipoItem, idItem, dataEmprestimo, dataDevolucao);
        emprestimos.add(novoEmprestimo);

        // Marca o item como emprestado
        if (tipoItem.equalsIgnoreCase("livro")) {
            Livro livro = Livro.getId(idItem);
            livro.setDisponibilidade(false);
        } else if (tipoItem.equalsIgnoreCase("revista")) {
            Revista revista = Revista.getRevistaById(idItem);
            revista.setDisponibilidade(false);
        }

        System.out.println("Empréstimo registrado com sucesso!");
    }

    public static void devolverItem(int idEmprestimo) {
        Emprestimo emprestimo = null;
        for (Emprestimo e : emprestimos) {
            if (e.idEmprestimo == idEmprestimo) {
                emprestimo = e;
                break;
            }
        }

        if (emprestimo != null) {
            if (emprestimo.tipoItem.equalsIgnoreCase("livro")) {
                Livro livro = Livro.getID(emprestimo.idItem);
                livro.setDisponibilidade(true);
            } else if (emprestimo.tipoItem.equalsIgnoreCase("revista")) {
                Revista revista = Revista.getID(emprestimo.idItem);
                revista.setDisponibilidade(true);
            }
            emprestimos.remove(emprestimo);
            System.out.println("Item devolvido com sucesso!");
        } else {
            System.out.println("Empréstimo não encontrado!");
        }
    }

    public static void listarEmprestimos() {
        if (emprestimos.isEmpty()) {
            System.out.println("Não há empréstimos registrados.");
        } else {
            System.out.println("\n===== Lista de Empréstimos =====");
            for (Emprestimo emprestimo : emprestimos) {
                System.out.println("ID Empréstimo: " + emprestimo.idEmprestimo
                        + ", ID Usuário: " + emprestimo.idUsuario
                        + ", Tipo de Item: " + emprestimo.tipoItem
                        + ", ID do Item: " + emprestimo.idItem
                        + ", Data do Empréstimo: " + emprestimo.dataEmprestimo
                        + ", Data de Devolução: " + emprestimo.dataDevolucao);
            }
        }
    }

    public static boolean verificarDisponibilidade(String tipoItem, int idItem) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.tipoItem.equalsIgnoreCase(tipoItem) && emprestimo.idItem == idItem) {
                return false; // Item emprestado
            }
        }
        return true; // Item disponível
    }
}
