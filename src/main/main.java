package main;
import ui.TelaLogin;
import dao.GerenciadorUsuarios;
import dao.GerenciadorTransacoes;
import dao.GerenciadorCategorias;
import javax.swing.SwingUtilities;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
         
            GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios();
            GerenciadorTransacoes gerenciadorTransacoes = new GerenciadorTransacoes();
            GerenciadorCategorias gerenciadorCategorias = new GerenciadorCategorias();

            TelaLogin telaLogin = new TelaLogin(gerenciadorUsuarios, gerenciadorTransacoes, gerenciadorCategorias);
            telaLogin.setVisible(true);
        });
    }
}