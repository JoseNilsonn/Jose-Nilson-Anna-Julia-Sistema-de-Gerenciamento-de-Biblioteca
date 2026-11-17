package modelo;

/** PRODUTO CONCRETO: livro do gênero Técnico. */
public class LivroTecnico extends Livro {

    public LivroTecnico(String titulo, String autor, String isbn, int prazoEmprestimoDias) {
        super(titulo, autor, isbn, prazoEmprestimoDias);
    }

    @Override
    public String getCategoria() {
        return "Técnico";
    }
}
