package observador;

/**
 * OBSERVADOR (interface): único método update(), chamado quando o estado do Subject muda.
 * Estilo "push": o sujeito já envia os valores de estado (título e disponibilidade).
 */
public interface Observer {
    void update(String titulo, boolean disponivel);
}
