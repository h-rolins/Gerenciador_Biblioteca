package servicos;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import modelos.Livro;

public class OpLivro {
    private static final String FILE_NAME = "livros.dat";

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
                livro.setAutor(livroAtualizado.getAutor());;
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

    @SuppressWarnings("unchecked")
    public static List<Livro> loadLivros() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Livro>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static void saveToFile(List<Livro> livros) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(livros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
