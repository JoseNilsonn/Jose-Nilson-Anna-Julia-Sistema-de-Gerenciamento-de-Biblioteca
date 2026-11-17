package modelo;

/** PRODUTO CONCRETO: livro do gênero Infantil. */
public class LivroInfantil extends Livro {

    public LivroInfantil(String titulo, String autor, String isbn, int prazoEmprestimoDias) {
        super(titulo, autor, isbn, prazoEmprestimoDias);
    }

    @Override
    public String getCategoria() {
        return "Infantil";
    }
}
