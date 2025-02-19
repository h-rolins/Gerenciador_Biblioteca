package servicos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelos.Emprestimo;

public class OpEmprestimo {
    private static final List<Emprestimo> emprestimos = new ArrayList<>();

    public static void registrarEmprestimo(int idUsuario, String tipoItem, int idItem, Date dataDevolucao) {
        int idEmprestimo = emprestimos.size() + 1;
        Date dataEmprestimo = new Date();
        Emprestimo novoEmprestimo = new Emprestimo(idEmprestimo, idUsuario, tipoItem, idItem, dataEmprestimo, dataDevolucao);
        emprestimos.add(novoEmprestimo);
        System.out.println("Empréstimo registrado com sucesso!");
    }

    public static void devolverItem(int idEmprestimo) {
        Emprestimo emprestimo = emprestimos.stream()
                .filter(e -> e.getIdEmprestimo() == idEmprestimo)
                .findFirst()
                .orElse(null);

        if (emprestimo != null) {
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
                System.out.println("ID Empréstimo: " + emprestimo.getIdEmprestimo()
                        + ", ID Usuário: " + emprestimo.getIdUsuario()
                        + ", Tipo de Item: " + emprestimo.getTipoItem()
                        + ", ID do Item: " + emprestimo.getIdItem()
                        + ", Data do Empréstimo: " + emprestimo.getDataEmprestimo()
                        + ", Data de Devolução: " + emprestimo.getDataDevolucao());
            }
        }
    }
}
