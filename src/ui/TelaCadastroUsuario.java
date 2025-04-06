package ui;
import javax.swing.*;
import dao.GerenciadorUsuarios;

public class TelaCadastroUsuario extends JFrame {
    private GerenciadorUsuarios gerenciador;
    private JTextField emailText;
    private JPasswordField senhaText;

    public TelaCadastroUsuario(GerenciadorUsuarios gerenciador) {
        this.gerenciador = gerenciador;
        setTitle("Cadastro de Usuário");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        colocarComponentes(panel);
        add(panel);
    }

    private void colocarComponentes(JPanel panel) {
        panel.setLayout(null);

        JLabel emailLabel = new JLabel("E-mail:");
        emailLabel.setBounds(10, 20, 80, 25);
        panel.add(emailLabel);

        emailText = new JTextField(20);
        emailText.setBounds(100, 20, 165, 25);
        panel.add(emailText);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(10, 50, 80, 25);
        panel.add(senhaLabel);

        senhaText = new JPasswordField(20);
        senhaText.setBounds(100, 50, 165, 25);
        panel.add(senhaText);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(10, 80, 100, 25);
        panel.add(cadastrarButton);

        cadastrarButton.addActionListener(e -> cadastrarUsuario());
    }

    private void cadastrarUsuario() {
        String email = emailText.getText();
        String senha = new String(senhaText.getPassword());
        boolean sucesso = gerenciador.cadastrarUsuario(email, senha);

        String mensagem = sucesso ? "Usuário cadastrado com sucesso!" : "E-mail já cadastrado!";
        JOptionPane.showMessageDialog(this, mensagem);

        if (sucesso) {
            this.dispose(); // Fecha a tela após o cadastro
        }
    }
}