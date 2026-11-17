package modelo;

/** PRODUTO CONCRETO: livro do gênero Fantasia. */
public class LivroFantasia extends Livro {

    public LivroFantasia(String titulo, String autor, String isbn, int prazoEmprestimoDias) {
        super(titulo, autor, isbn, prazoEmprestimoDias);
    }

    @Override
    public String getCategoria() {
        return "Fantasia";
    }
}
