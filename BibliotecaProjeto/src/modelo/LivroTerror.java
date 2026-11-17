package modelo;

/** PRODUTO CONCRETO: livro do gênero Terror. */
public class LivroTerror extends Livro {

    public LivroTerror(String titulo, String autor, String isbn, int prazoEmprestimoDias) {
        super(titulo, autor, isbn, prazoEmprestimoDias);
    }

    @Override
    public String getCategoria() {
        return "Terror";
    }
}
