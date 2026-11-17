package comando;

/** Objeto nulo: evita ter que lidar com null quando ainda não há comando (usado na inicialização). */
public class NoCommand implements Command {

    @Override
    public void execute() {
        // Não faz nada
    }

    @Override
    public void undo() {
        // Não faz nada
    }
}
