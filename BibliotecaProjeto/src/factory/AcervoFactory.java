package factory;

import acervo.GerenciadorAcervo;
import configuracao.Configuracoes;
import modelo.Livro;

/**
 * CRIADOR (classe abstrata)
 *
 * - cadastrar() implementa todo o processo e NÃO usa nenhum "new": ela só diz
 *   "alguém vai criar um item para mim" chamando criarItem().
 * - criarItem() é o FACTORY METHOD, abstrato: cada criador concreto decide o que instanciar.
 *
 * Ligação com os Singletons: os prazos vêm de Configuracoes e o item criado
 * é registrado no GerenciadorAcervo.
 */
public abstract class AcervoFactory {

    public Livro cadastrar(String tipo, String titulo, String autor, String isbn) {
        Livro livro = criarItem(tipo, titulo, autor, isbn);

        livro.catalogar();
        livro.etiquetar();
        GerenciadorAcervo.getInstance().adicionar(livro);

        return livro;
    }

    /** FACTORY METHOD: as subclasses implementam e instanciam o produto que sabem criar. */
    protected abstract Livro criarItem(String tipo, String titulo, String autor, String isbn);

    // Prazos vindos do Singleton, lidos no momento da criação
    protected int prazoLivro() {
        return Configuracoes.getInstance().getPrazoLivroDias();
    }

    protected int prazoRevista() {
        return Configuracoes.getInstance().getPrazoRevistaDias();
    }
}
