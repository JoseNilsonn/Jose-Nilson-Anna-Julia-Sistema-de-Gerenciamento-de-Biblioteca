package comando;

import configuracao.Configuracoes;
import modelo.Livro;
import modelo.Usuario;

/** COMANDO CONCRETO: vincula a ação "devolver" aos receptores. A multa vem do Singleton Configuracoes. */
public class DevolverCommand implements Command {

    private final Usuario usuario;
    private final Livro livro;
    private final int diasAtraso;
    private double multaCobrada = 0.0;

    public DevolverCommand(Usuario usuario, Livro livro, int diasAtraso) {
        this.usuario = usuario;
        this.livro = livro;
        this.diasAtraso = diasAtraso;
    }

    @Override
    public void execute() {
        if (!usuario.possui(livro)) {
            throw new IllegalStateException(usuario.getNome() + " não está com '" + livro.getTitulo() + "'");
        }
        multaCobrada = diasAtraso * Configuracoes.getInstance().getMultaPorDiaAtraso();
        if (multaCobrada > 0) {
            usuario.cobrarMulta(multaCobrada);
        }
        usuario.devolver(livro);
        livro.devolver();   // muda o estado e notifica os observadores
    }

    @Override
    public void undo() {
        if (multaCobrada > 0) {
            usuario.estornarMulta(multaCobrada);
        }
        usuario.pegarEmprestado(livro);
        livro.emprestar();
    }
}
