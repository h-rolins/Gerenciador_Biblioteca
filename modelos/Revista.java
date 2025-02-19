package modelos;

public class Revista extends ItemBiblioteca {
    private int edicao;

    public Revista(int id, String titulo, String autor, String editora, String anoPublicacao, int edicao) {
        super(id, titulo, autor, editora, anoPublicacao);
        this.edicao = edicao;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + " | Edição nº " + edicao + ".";
    }
}
