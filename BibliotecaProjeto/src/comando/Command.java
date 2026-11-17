package comando;

/** COMMAND (interface): declara a interface para todos os comandos. */
public interface Command {
    void execute();
    void undo();
}
