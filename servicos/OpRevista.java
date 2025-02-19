package servicos;

import modelos.Revista;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OpRevista {

    private static final String FILE_NAME = "revistas.txt";

    public static void saveRevista(Revista revista) {
        List<Revista> revistas = loadRevistas();
        revistas.add(revista);
        saveToFile(revistas);
    }

    public static void updateRevista(Revista revistaAtualizada) {
        List<modelos.Revista> revistas = loadRevistas();
        for (modelos.Revista revista : revistas) {
            if (revista.getId() == revistaAtualizada.getId()) {
                revista.setTitulo(revistaAtualizada.getTitulo());
                revista.setAutor(revistaAtualizada.getAutor());
                revista.setEditora(revistaAtualizada.getEditora());
                revista.setAnoPublicacao(revistaAtualizada.getAnoPublicacao());
                revista.setEdicao(revistaAtualizada.getEdicao());
                saveToFile(revistas);
                return;
            }
        }
    }

    public static void deleteRevista(int id) {
        List<Revista> revistas = loadRevistas();
        revistas.removeIf(revista -> revista.getId() == id);
        saveToFile(revistas);
    }

    public static List<Revista> loadRevistas() {
        List<Revista> revistas = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) return revistas;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 6) {
                    Revista revista = new Revista(
                            Integer.parseInt(fields[0]),   // ID
                            fields[1],                     // Título
                            fields[2],                     // Autor
                            fields[3],                     // Editora
                            fields[4],                     // Ano de Publicação
                            Integer.parseInt(fields[5])                      // Edição
                    );
                    revistas.add(revista);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return revistas;
    }

    private static void saveToFile(List<Revista> revistas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Revista revista : revistas) {
                bw.write(revista.getId() + ","
                        + revista.getTitulo() + ","
                        + revista.getAutor() + ","
                        + revista.getEditora() + ","
                        + revista.getAnoPublicacao() + ","
                        + revista.getEdicao());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
