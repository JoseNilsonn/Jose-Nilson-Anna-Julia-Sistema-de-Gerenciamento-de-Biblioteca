package comando;

/**
 * Executa uma sequência de comandos e desfaz na ordem inversa.
 * Acréscimo: se um comando falhar no meio, os já executados são desfeitos (não fica pela metade).
 */
public class MacroCommand implements Command {

    private final Command[] commands;

    public MacroCommand(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        int executados = 0;
        try {
            for (Command command : commands) {
                command.execute();
                executados++;
            }
        } catch (RuntimeException e) {
            System.out.println("   (um comando falhou: desfazendo os " + executados + " já executados)");
            for (int i = executados - 1; i >= 0; i--) {
                commands[i].undo();
            }
            throw e;
        }
    }

    @Override
    public void undo() {
        for (int i = commands.length - 1; i >= 0; i--) {
            commands[i].undo();
        }
    }
}
