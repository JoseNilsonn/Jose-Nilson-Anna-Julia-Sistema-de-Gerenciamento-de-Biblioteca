import acervo.GerenciadorAcervo;
import comando.Balcao;
import comando.Command;
import comando.DevolverCommand;
import comando.EmprestarCommand;
import comando.MacroCommand;
import configuracao.Configuracoes;
import factory.AcervoFactory;
import factory.AcervoInformativoFactory;
import factory.AcervoLiterarioFactory;
import modelo.Livro;
import modelo.LivroFantasia;
import modelo.Usuario;
import notificacao.AvisoDisponibilidade;
import notificacao.RegistroDeEventos;

public class Main {

    private static Livro buscar(String titulo) {
        return GerenciadorAcervo.getInstance().buscarPorTitulo(titulo).get(0);
    }

    public static void main(String[] args) {

        // ===================== Singleton =====================
        Configuracoes c1 = Configuracoes.getInstance();

        // ===================== Factory Method =====================
        AcervoFactory literario = new AcervoLiterarioFactory();
        AcervoFactory informativo = new AcervoInformativoFactory();

        System.out.println("\n--- Cadastrando ---");
        literario.cadastrar("romance", "Dom Casmurro", "Machado de Assis", "978-85-359-0277-5");
        literario.cadastrar("terror", "It: A Coisa", "Stephen King", "978-85-8105-153-7");
        informativo.cadastrar("revista", "Superinteressante", "Editora Abril", "0104-1789");

        // Mudar a configuração afeta só os itens criados depois
        c1.setPrazoLivroDias(21);
        literario.cadastrar("fantasia", "O Hobbit", "J. R. R. Tolkien", "978-85-359-0000-0");
        literario.cadastrar("fantasia", "Senhor dos Anéis", "J. R. R. Tolkien", "978-85-123-0591-9");

        System.out.println("\n--- Todo o acervo ---");
        for (Livro item : GerenciadorAcervo.getInstance().listarTodos()) {
            System.out.println(item);
        }

        System.out.println("\n--- Só Fantasia (filtro pelo gênero) ---");
        for (Livro item : GerenciadorAcervo.getInstance().listarPorTipo(LivroFantasia.class)) {
            System.out.println(item);
        }

        System.out.println("\n--- Tipo inválido numa fábrica ---");
        try {
            informativo.cadastrar("romance", "Teste", "Fulano", "000");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }

        // ===================== Observer: um RegistroDeEventos em cada livro =====================
        for (Livro livro : GerenciadorAcervo.getInstance().listarTodos()) {
            new RegistroDeEventos(livro);
        }

        Livro domCasmurro = buscar("Dom Casmurro");
        Livro it = buscar("It");
        Livro revista = buscar("Superinteressante");
        Livro hobbit = buscar("Hobbit");

        Usuario maria = new Usuario("Maria");
        Usuario joao = new Usuario("João");

        // ===================== Command: o balcão é o invocador =====================
        Balcao balcao = new Balcao();

        System.out.println("\n=== 1) Maria empresta Dom Casmurro ===");
        balcao.setCommand(new EmprestarCommand(maria, domCasmurro));
        balcao.executarComando();

        System.out.println("\n=== 2) João tenta o mesmo livro e entra na lista de espera ===");
        try {
            balcao.setCommand(new EmprestarCommand(joao, domCasmurro));
            balcao.executarComando();
        } catch (IllegalStateException e) {
            System.out.println("Não foi possível: " + e.getMessage());
        }
        new AvisoDisponibilidade(domCasmurro, joao);

        System.out.println("\n=== 3) Maria devolve com 3 dias de atraso (multa vem de Configuracoes) ===");
        balcao.setCommand(new DevolverCommand(maria, domCasmurro, 3));
        balcao.executarComando();
        System.out.println(maria);

        System.out.println("\n=== 4) Desfazendo a devolução ===");
        balcao.desfazerUltimo();
        System.out.println(maria);

        System.out.println("\n=== 5) Maria leva mais dois itens de uma vez ===");
        Command kit = new MacroCommand(new Command[] {
                new EmprestarCommand(maria, it),
                new EmprestarCommand(maria, revista)
        });
        balcao.setCommand(kit);
        balcao.executarComando();
        System.out.println(maria);

        System.out.println("\n=== 6) Limite de itens (Configuracoes) ===");
        try {
            balcao.setCommand(new EmprestarCommand(maria, hobbit));
            balcao.executarComando();
        } catch (IllegalStateException e) {
            System.out.println("Não foi possível: " + e.getMessage());
        }

        System.out.println("\n=== 7) Desfazendo (ordem inversa) ===");
        balcao.desfazerUltimo();
        System.out.println(maria);

        System.out.println("\n=== 8) Empréstimo que estoura o limite: desfaz o que já tinha feito ===");
        Command kitGrande = new MacroCommand(new Command[] {
                new EmprestarCommand(maria, it),
                new EmprestarCommand(maria, revista),
                new EmprestarCommand(maria, hobbit)
        });
        try {
            balcao.setCommand(kitGrande);
            balcao.executarComando();
        } catch (IllegalStateException e) {
            System.out.println("Não foi possível: " + e.getMessage());
        }
        System.out.println(maria);

        System.out.println("\n--- Disponíveis no final ---");
        for (Livro item : GerenciadorAcervo.getInstance().listarDisponiveis()) {
            System.out.println(item);
        }
    }
}
