package notificacao;

import observador.DisplayElement;
import observador.Observer;
import observador.Subject;

/** OBSERVADOR CONCRETO: registra cada empréstimo/devolução de um livro (como a StatisticsDisplay). */
public final class RegistroDeEventos implements Observer, DisplayElement {

    private final Subject livro;
    private String titulo;
    private boolean disponivel;
    private int emprestimos = 0;
    private int devolucoes = 0;

    public RegistroDeEventos(Subject livro) {
        this.livro = livro;
        livro.registerObserver(this);   // o observador se registra no sujeito
    }

    @Override
    public void update(String titulo, boolean disponivel) {
        this.titulo = titulo;
        this.disponivel = disponivel;
        if (disponivel) {
            devolucoes++;
        } else {
            emprestimos++;
        }
        display();
    }

    @Override
    public void display() {
        System.out.println("   [REGISTRO] '" + titulo + "' agora está "
                + (disponivel ? "Disponível" : "Emprestado")
                + " | empréstimos: " + emprestimos + " | devoluções: " + devolucoes);
    }
}
