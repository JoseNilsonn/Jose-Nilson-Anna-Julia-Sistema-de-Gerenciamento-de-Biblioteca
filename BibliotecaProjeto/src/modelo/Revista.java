package modelo;

/** PRODUTO CONCRETO: a Revista é uma categoria de Livro (autor = editora, isbn = ISSN). */
public class Revista extends Livro {

    public Revista(String titulo, String editora, String issn, int prazoEmprestimoDias) {
        super(titulo, editora, issn, prazoEmprestimoDias);
    }

    @Override
    public String getCategoria() {
        return "Variedades";
    }

    @Override
    public String getTipo() {
        return "Revista";
    }

    @Override
    public void etiquetar() {
        System.out.println("Colando etiqueta de revista em " + getTitulo());
    }

    @Override
    protected String rotuloAutor() { return "Editora"; }

    @Override
    protected String rotuloCodigo() { return "ISSN"; }
}
