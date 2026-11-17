package comando;

import modelo.Livro;
import modelo.Usuario;

/** COMANDO CONCRETO: vincula a ação "emprestar" aos receptores (Usuario e Livro). */
public class EmprestarCommand implements Command {

    private final Usuario usuario;
    private final Livro livro;

    public EmprestarCommand(Usuario usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
    }

    @Override
    public void execute() {
        if (!livro.isDisponivel()) {
            throw new IllegalStateException("'" + livro.getTitulo() + "' já está emprestado");
        }
        if (!usuario.podeEmprestar()) {
            throw new IllegalStateException(usuario.getNome() + " atingiu o limite de itens emprestados");
        }
        usuario.pegarEmprestado(livro);
        livro.emprestar();   // muda o estado e notifica os observadores
    }

    @Override
    public void undo() {
        usuario.devolver(livro);
        livro.devolver();
    }
}
