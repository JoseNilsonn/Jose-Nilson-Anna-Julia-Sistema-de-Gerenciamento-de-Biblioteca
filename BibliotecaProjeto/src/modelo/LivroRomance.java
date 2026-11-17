package modelo;

/** PRODUTO CONCRETO: livro do gênero Romance. */
public class LivroRomance extends Livro {

    public LivroRomance(String titulo, String autor, String isbn, int prazoEmprestimoDias) {
        super(titulo, autor, isbn, prazoEmprestimoDias);
    }

    @Override
    public String getCategoria() {
        return "Romance";
    }
}
