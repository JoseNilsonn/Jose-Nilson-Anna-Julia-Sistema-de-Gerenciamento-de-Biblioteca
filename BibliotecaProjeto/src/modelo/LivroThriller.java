package modelo;

/** PRODUTO CONCRETO: livro do gênero Suspense/Thriller. */
public class LivroThriller extends Livro {

    public LivroThriller(String titulo, String autor, String isbn, int prazoEmprestimoDias) {
        super(titulo, autor, isbn, prazoEmprestimoDias);
    }

    @Override
    public String getCategoria() {
        return "Suspense/Thriller";
    }
}
