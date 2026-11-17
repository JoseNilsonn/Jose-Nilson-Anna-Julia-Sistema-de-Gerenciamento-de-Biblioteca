package notificacao;

import modelo.Usuario;
import observador.DisplayElement;
import observador.Observer;
import observador.Subject;

/**
 * OBSERVADOR CONCRETO: lista de espera. Avisa o usuário quando o livro que ele quer
 * voltar a ficar disponível e, depois disso, sai da lista (removeObserver).
 */
public final class AvisoDisponibilidade implements Observer, DisplayElement {

    private final Subject livro;
    private final Usuario usuario;
    private String titulo;

    public AvisoDisponibilidade(Subject livro, Usuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
        livro.registerObserver(this);
        System.out.println("   [AVISO] " + usuario.getNome() + " entrou na lista de espera");
    }

    @Override
    public void update(String titulo, boolean disponivel) {
        this.titulo = titulo;
        if (disponivel) {
            display();
            livro.removeObserver(this);   // já foi avisado, não precisa mais observar
        }
    }

    @Override
    public void display() {
        System.out.println("   [AVISO] " + usuario.getNome() + ", o livro '" + titulo
                + "' que você esperava está disponível!");
    }
}
