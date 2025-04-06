package ui;
import javax.swing.*;
import dao.GerenciadorUsuarios;
import dao.GerenciadorTransacoes;
import dao.GerenciadorCategorias;

public class TelaLogin extends JFrame {
    private GerenciadorUsuarios gerenciadorUsuarios;
    private GerenciadorTransacoes gerenciadorTransacoes;
    private GerenciadorCategorias gerenciadorCategorias;
    private JTextField usuarioText;
    private JPasswordField senhaText;

    public TelaLogin(GerenciadorUsuarios gerenciadorUsuarios, GerenciadorTransacoes gerenciadorTransacoes, GerenciadorCategorias gerenciadorCategorias) {
        this.gerenciadorUsuarios = gerenciadorUsuarios;
        this.gerenciadorTransacoes = gerenciadorTransacoes;
        this.gerenciadorCategorias = gerenciadorCategorias;

        setTitle("Login");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        colocarComponentes(panel);
        add(panel);
    }

    private void colocarComponentes(JPanel panel) {
        panel.setLayout(null);

        JLabel usuarioLabel = new JLabel("E-mail:");
        usuarioLabel.setBounds(10, 20, 80, 25);
        panel.add(usuarioLabel);

        usuarioText = new JTextField(20);
        usuarioText.setBounds(100, 20, 165, 25);
        panel.add(usuarioText);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(10, 50, 80, 25);
        panel.add(senhaLabel);

        senhaText = new JPasswordField(20);
        senhaText.setBounds(100, 50, 165, 25);
        panel.add(senhaText);

        JButton loginButton = new JButton("Entrar");
        loginButton.setBounds(10, 80, 100, 25);
        panel.add(loginButton);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(120, 80, 100, 25);
        panel.add(cadastrarButton);

        loginButton.addActionListener(e -> validarLogin());
        cadastrarButton.addActionListener(e -> new TelaCadastroUsuario(gerenciadorUsuarios).setVisible(true));
    }

    private void validarLogin() {
        String email = usuarioText.getText();
        String senha = new String(senhaText.getPassword());

        if (gerenciadorUsuarios.autenticarUsuario(email, senha)) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
            new TelaCadastroTransacao(gerenciadorTransacoes, gerenciadorCategorias).setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos.");
        }
    }
}