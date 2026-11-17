package modelo;

/** PRODUTO CONCRETO: livro do gênero Ficção Científica. */
public class LivroFiccaoCientifica extends Livro {

    public LivroFiccaoCientifica(String titulo, String autor, String isbn, int prazoEmprestimoDias) {
        super(titulo, autor, isbn, prazoEmprestimoDias);
    }

    @Override
    public String getCategoria() {
        return "Ficção Científica";
    }
}
