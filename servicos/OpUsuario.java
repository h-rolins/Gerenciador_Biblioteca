package servicos;

import modelos.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OpUsuario {
    private static final String FILE_NAME = "usuarios.txt";

    public static void saveUsuario(Usuario usuario) {
        List<Usuario> usuarios = loadUsuarios();
        usuarios.add(usuario);
        saveToFile(usuarios);
    }

    public static void updateUsuario(Usuario usuarioAtualizado) {
        List<Usuario> usuarios = loadUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == usuarioAtualizado.getId()) {
                usuario.setNome(usuarioAtualizado.getNome());
                usuario.setEmail(usuarioAtualizado.getEmail());
                usuario.setSenha(usuarioAtualizado.getSenha());
                saveToFile(usuarios);
                return;
            }
        }
    }

    public static void deleteUsuario(int id) {
        List<Usuario> usuarios = loadUsuarios();
        usuarios.removeIf(usuario -> usuario.getId() == id);
        saveToFile(usuarios);
    }

    public static List<Usuario> loadUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) return usuarios;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 4) { // 4 campos: ID, Nome, E-mail, Senha
                    Usuario usuario = new Usuario(
                            Integer.parseInt(fields[0]),
                            fields[1],
                            fields[2],
                            fields[3]
                    );
                    usuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    private static void saveToFile(List<Usuario> usuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Usuario usuario : usuarios) {
                bw.write(usuario.getId() + ","
                        + usuario.getNome() + ","
                        + usuario.getEmail() + ","
                        + usuario.getSenha());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
