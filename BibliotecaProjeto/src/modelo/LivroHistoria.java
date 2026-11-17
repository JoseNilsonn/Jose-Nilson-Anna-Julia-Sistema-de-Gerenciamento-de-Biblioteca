package modelo;

/** PRODUTO CONCRETO: livro do gênero História. */
public class LivroHistoria extends Livro {

    public LivroHistoria(String titulo, String autor, String isbn, int prazoEmprestimoDias) {
        super(titulo, autor, isbn, prazoEmprestimoDias);
    }

    @Override
    public String getCategoria() {
        return "História";
    }
}
