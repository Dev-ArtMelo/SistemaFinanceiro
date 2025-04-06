package main;
import ui.TelaLogin;
import dao.GerenciadorUsuarios;
import dao.GerenciadorTransacoes;
import javax.swing.SwingUtilities;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Criando instâncias de gerenciamento
            GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios();
            GerenciadorTransacoes gerenciadorTransacoes = new GerenciadorTransacoes();

            // Corrigindo a chamada da TelaLogin
            TelaLogin telaLogin = new TelaLogin(gerenciadorUsuarios, gerenciadorTransacoes);
            telaLogin.setVisible(true);
        });
    }
}