package modelo;

import configuracao.Configuracoes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** RECEPTOR (Receiver) do Command: sabe executar as tarefas de pegar e devolver livros. */
public class Usuario {

    private final String nome;
    private final List<Livro> emprestimos = new ArrayList<>();
    private double multaAcumulada = 0.0;

    public Usuario(String nome) {
        this.nome = nome;
    }

    /** Usa o limite definido no Singleton Configuracoes. */
    public boolean podeEmprestar() {
        return emprestimos.size() < Configuracoes.getInstance().getLimiteItensPorUsuario();
    }

    public boolean possui(Livro livro) {
        return emprestimos.contains(livro);
    }

    public void pegarEmprestado(Livro livro) {
        emprestimos.add(livro);
        System.out.println(nome + " pegou emprestado: " + livro.getTitulo()
                + " (prazo: " + livro.getPrazoEmprestimoDias() + " dias)");
    }

    public void devolver(Livro livro) {
        emprestimos.remove(livro);
        System.out.println(nome + " devolveu: " + livro.getTitulo());
    }

    public void cobrarMulta(double valor) {
        multaAcumulada += valor;
        System.out.println(nome + " recebeu multa de R$ " + String.format("%.2f", valor));
    }

    public void estornarMulta(double valor) {
        multaAcumulada -= valor;
        System.out.println("Multa de R$ " + String.format("%.2f", valor) + " estornada para " + nome);
    }

    public String getNome() { return nome; }
    public double getMultaAcumulada() { return multaAcumulada; }
    public List<Livro> getEmprestimos() { return Collections.unmodifiableList(emprestimos); }

    @Override
    public String toString() {
        return nome + " (itens: " + emprestimos.size() + ", multa: R$ " + String.format("%.2f", multaAcumulada) + ")";
    }
}
