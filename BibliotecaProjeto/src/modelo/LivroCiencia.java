package modelo;

/** PRODUTO CONCRETO: livro do gênero Ciência. */
public class LivroCiencia extends Livro {

    public LivroCiencia(String titulo, String autor, String isbn, int prazoEmprestimoDias) {
        super(titulo, autor, isbn, prazoEmprestimoDias);
    }

    @Override
    public String getCategoria() {
        return "Ciência";
    }
}
