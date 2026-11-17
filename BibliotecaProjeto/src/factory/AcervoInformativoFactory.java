package factory;

import modelo.Livro;
import modelo.LivroCiencia;
import modelo.LivroHistoria;
import modelo.LivroTecnico;
import modelo.Revista;

/** CRIADOR CONCRETO: fabrica livros informativos e revistas. É aqui que acontece o "new". */
public class AcervoInformativoFactory extends AcervoFactory {

    @Override
    protected Livro criarItem(String tipo, String titulo, String autor, String isbn) {
        if (tipo.equals("historia")) {
            return new LivroHistoria(titulo, autor, isbn, prazoLivro());
        } else if (tipo.equals("ciencia")) {
            return new LivroCiencia(titulo, autor, isbn, prazoLivro());
        } else if (tipo.equals("tecnico")) {
            return new LivroTecnico(titulo, autor, isbn, prazoLivro());
        } else if (tipo.equals("revista")) {
            return new Revista(titulo, autor, isbn, prazoRevista());
        } else {
            throw new IllegalArgumentException("Tipo não fabricado pelo acervo informativo: " + tipo);
        }
    }
}
