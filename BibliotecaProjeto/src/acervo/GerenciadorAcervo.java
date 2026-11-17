package acervo;

import modelo.Livro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** SINGLETON: guarda a lista de itens do acervo e oferece as buscas. */
public class GerenciadorAcervo {

    private final List<Livro> itens = new ArrayList<>();

    private GerenciadorAcervo() {
    }

    private static class Holder {
        private static final GerenciadorAcervo INSTANCIA = new GerenciadorAcervo();
    }

    public static GerenciadorAcervo getInstance() {
        return Holder.INSTANCIA;
    }

    public synchronized void adicionar(Livro item) {
        itens.add(item);
    }

    public synchronized List<Livro> listarTodos() {
        return Collections.unmodifiableList(new ArrayList<>(itens));
    }

    public synchronized List<Livro> buscarPorTitulo(String trecho) {
        List<Livro> resultado = new ArrayList<>();
        for (Livro item : itens) {
            if (item.getTitulo().toLowerCase().contains(trecho.toLowerCase())) {
                resultado.add(item);
            }
        }
        return resultado;
    }

    /** Filtra pela classe concreta, ex.: listarPorTipo(LivroFantasia.class). */
    public synchronized List<Livro> listarPorTipo(Class<? extends Livro> tipo) {
        List<Livro> resultado = new ArrayList<>();
        for (Livro item : itens) {
            if (tipo.isInstance(item)) {
                resultado.add(item);
            }
        }
        return resultado;
    }

    public synchronized List<Livro> listarDisponiveis() {
        List<Livro> resultado = new ArrayList<>();
        for (Livro item : itens) {
            if (item.isDisponivel()) {
                resultado.add(item);
            }
        }
        return resultado;
    }
}
