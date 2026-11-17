package factory;

import modelo.Livro;
import modelo.LivroFantasia;
import modelo.LivroFiccaoCientifica;
import modelo.LivroInfantil;
import modelo.LivroRomance;
import modelo.LivroTerror;
import modelo.LivroThriller;

/** CRIADOR CONCRETO: fabrica os livros de literatura (ficção). É aqui que acontece o "new". */
public class AcervoLiterarioFactory extends AcervoFactory {

    @Override
    protected Livro criarItem(String tipo, String titulo, String autor, String isbn) {
        if (tipo.equals("romance")) {
            return new LivroRomance(titulo, autor, isbn, prazoLivro());
        } else if (tipo.equals("terror")) {
            return new LivroTerror(titulo, autor, isbn, prazoLivro());
        } else if (tipo.equals("ficcao")) {
            return new LivroFiccaoCientifica(titulo, autor, isbn, prazoLivro());
        } else if (tipo.equals("fantasia")) {
            return new LivroFantasia(titulo, autor, isbn, prazoLivro());
        } else if (tipo.equals("thriller")) {
            return new LivroThriller(titulo, autor, isbn, prazoLivro());
        } else if (tipo.equals("infantil")) {
            return new LivroInfantil(titulo, autor, isbn, prazoLivro());
        } else {
            throw new IllegalArgumentException("Tipo não fabricado pelo acervo literário: " + tipo);
        }
    }
}
