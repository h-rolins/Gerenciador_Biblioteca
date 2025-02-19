package modelos;
public class Livro extends ItemBiblioteca {
    private int numPag;

    public Livro(int id, String titulo, String autor, String editora, String anoPublicacao, int numPag) {
        super(id, titulo, autor, editora, anoPublicacao);
        this.numPag = numPag;
    }

    public int getNumPag() {
        return numPag;
    }

    public void setNumPag(int numPag) {
        this.numPag = numPag;
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + " | " + numPag + " páginas.";
    }
}
