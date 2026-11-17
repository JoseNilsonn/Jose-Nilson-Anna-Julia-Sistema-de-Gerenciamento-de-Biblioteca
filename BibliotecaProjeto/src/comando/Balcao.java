package comando;

/**
 * INVOCADOR (Invoker)
 * Guarda um comando e, em algum momento, pede a ele que atenda a solicitação chamando execute().
 * Não sabe qual ação nem qual receptor estão por trás do comando.
 */
public class Balcao {

    private Command comando;
    private Command ultimoComando;   // usado pelo undo (como o undoCommand do slide)

    public Balcao() {
        Command semComando = new NoCommand();
        comando = semComando;
        ultimoComando = semComando;
    }

    public void setCommand(Command comando) {
        this.comando = comando;
    }

    public void executarComando() {
        comando.execute();
        ultimoComando = comando;   // só chega aqui se o comando não lançou exceção
    }

    public void desfazerUltimo() {
        ultimoComando.undo();
        ultimoComando = new NoCommand();   // evita desfazer duas vezes o mesmo comando
    }
}
