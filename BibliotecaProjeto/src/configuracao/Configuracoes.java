package configuracao;

/**
 * SINGLETON: configurações globais do sistema (prazos, multa e limite de itens).
 * Os campos são volatile para que alterações feitas por uma thread sejam vistas pelas outras.
 */
public class Configuracoes {

    private volatile int prazoLivroDias = 14;
    private volatile int prazoRevistaDias = 7;
    private volatile double multaPorDiaAtraso = 1.50;
    private volatile int limiteItensPorUsuario = 3;

    private Configuracoes() {
    }

    private static class Holder {
        private static final Configuracoes INSTANCIA = new Configuracoes();
    }

    public static Configuracoes getInstance() {
        return Holder.INSTANCIA;
    }

    public int getPrazoLivroDias() { return prazoLivroDias; }
    public void setPrazoLivroDias(int prazoLivroDias) { this.prazoLivroDias = prazoLivroDias; }

    public int getPrazoRevistaDias() { return prazoRevistaDias; }
    public void setPrazoRevistaDias(int prazoRevistaDias) { this.prazoRevistaDias = prazoRevistaDias; }

    public double getMultaPorDiaAtraso() { return multaPorDiaAtraso; }
    public void setMultaPorDiaAtraso(double multaPorDiaAtraso) { this.multaPorDiaAtraso = multaPorDiaAtraso; }

    public int getLimiteItensPorUsuario() { return limiteItensPorUsuario; }
    public void setLimiteItensPorUsuario(int limiteItensPorUsuario) { this.limiteItensPorUsuario = limiteItensPorUsuario; }
}
