package modelo;

import observador.Observer;
import observador.Subject;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * PRODUTO (classe abstrata) do Factory Method.
 * Concentra o comportamento comum; cada gênero (e a Revista) é um produto concreto.
 *
 * Também é o SUJEITO (Subject) do Observer: quando a disponibilidade muda, avisa os observadores.
 * E é um RECEPTOR (Receiver) do Command: emprestar() e devolver() são as ações que os comandos chamam.
 */
public abstract class Livro implements Subject {

    private final String titulo;
    private final String autor;
    private final String isbn;
    private final int prazoEmprestimoDias;
    private boolean disponivel = true;

    // CopyOnWriteArrayList permite um observador se remover enquanto é notificado
    private final List<Observer> observers = new CopyOnWriteArrayList<>();

    protected Livro(String titulo, String autor, String isbn, int prazoEmprestimoDias) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.prazoEmprestimoDias = prazoEmprestimoDias;
    }

    /** Cada produto concreto informa seu gênero/categoria. */
    public abstract String getCategoria();

    public String getTipo() {
        return "Livro";
    }

    // Etapas do "processo de fabricação"
    public void catalogar() {
        System.out.println("Catalogando " + titulo + " [" + getCategoria() + "]");
    }

    public void etiquetar() {
        System.out.println("Colando etiqueta de livro em " + titulo);
    }

    // ---------------- Ações do RECEPTOR (chamadas pelos comandos) ----------------
    public void emprestar() {
        mudarDisponibilidade(false);
    }

    public void devolver() {
        mudarDisponibilidade(true);
    }

    // ---------------- SUJEITO (Subject) ----------------
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(titulo, disponivel);
        }
    }

    public void disponibilidadeAlterada() {
        notifyObservers();
    }

    private void mudarDisponibilidade(boolean disponivel) {
        this.disponivel = disponivel;
        disponibilidadeAlterada();
    }

    /** Rótulos que a Revista sobrescreve (editora/ISSN em vez de autor/ISBN). */
    protected String rotuloAutor() { return "Autor"; }
    protected String rotuloCodigo() { return "ISBN"; }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn; }
    public int getPrazoEmprestimoDias() { return prazoEmprestimoDias; }
    public boolean isDisponivel() { return disponivel; }

    @Override
    public String toString() {
        return String.format("[%s | %s] %s | %s: %s | %s: %s | Prazo: %d dias | %s",
                getTipo(), getCategoria(), titulo, rotuloAutor(), autor, rotuloCodigo(), isbn,
                prazoEmprestimoDias, disponivel ? "Disponível" : "Emprestado");
    }
}
