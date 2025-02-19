package servicos;

import modelos.Livro;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OpLivro {

    private static final String FILE_NAME = "livros.txt";

    public static void saveLivro(Livro livro) {
        List<Livro> livros = loadLivros();
        livros.add(livro);
        saveToFile(livros);
    }

    public static void updateLivro(Livro livroAtualizado) {
        List<Livro> livros = loadLivros();
        for (Livro livro : livros) {
            if (livro.getId() == livroAtualizado.getId()) {
                livro.setTitulo(livroAtualizado.getTitulo());
                livro.setAutor(livroAtualizado.getAutor());
                livro.setEditora(livroAtualizado.getEditora());
                livro.setAnoPublicacao(livroAtualizado.getAnoPublicacao());
                livro.setNumPag(livroAtualizado.getNumPag());
                saveToFile(livros);
                return;
            }
        }
    }

    public static void deleteLivro(int id) {
        List<Livro> livros = loadLivros();
        livros.removeIf(livro -> livro.getId() == id);
        saveToFile(livros);
    }

    public static List<Livro> loadLivros() {
        List<Livro> livros = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) return livros;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 6) { // Corrigido para 6 campos
                    Livro livro = new Livro(
                            Integer.parseInt(fields[0]),   // ID
                            fields[1],                     // Título
                            fields[2],                     // Autor
                            fields[3],                     // Editora
                            fields[4],                     // Ano de Publicação
                            Integer.parseInt(fields[5])    // Número de Páginas
                    );
                    livros.add(livro);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return livros;
    }

    private static void saveToFile(List<Livro> livros) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Livro livro : livros) {
                // Salva com 6 campos: ID, Título, Autor, Editora, Ano de Publicação, Número de Páginas
                bw.write(livro.getId() + ","
                        + livro.getTitulo() + ","
                        + livro.getAutor() + ","
                        + livro.getEditora() + ","
                        + livro.getAnoPublicacao() + ","
                        + livro.getNumPag());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
