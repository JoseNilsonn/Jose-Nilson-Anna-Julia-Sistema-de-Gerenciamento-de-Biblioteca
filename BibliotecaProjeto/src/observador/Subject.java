package observador;

/** SUJEITO (interface): objetos se registram para ser avisados e podem sair quando quiserem. */
public interface Subject {
    void registerObserver(Observer o);   // observador a ser registrado
    void removeObserver(Observer o);     // observador a ser removido
    void notifyObservers();              // avisa todos quando o estado do Subject muda
}
