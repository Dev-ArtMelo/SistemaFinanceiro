package ui;
import javax.swing.*;
import dao.GerenciadorTransacoes;
import Modelo.Transacao;
import java.awt.*;
import java.time.LocalDate;

public class TelaCadastroTransacao extends JFrame {
    private GerenciadorTransacoes gerenciador;
    private JTextField valorText, categoriaText, descricaoText;
    private JRadioButton receitaButton, despesaButton;

    public TelaCadastroTransacao(GerenciadorTransacoes gerenciador) {
        this.gerenciador = gerenciador;
        setTitle("Cadastro de Transação");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        colocarComponentes(panel);
        add(panel);
    }

    private void colocarComponentes(JPanel panel) {
        panel.setLayout(null);

        JLabel valorLabel = new JLabel("Valor:");
        valorLabel.setBounds(10, 20, 80, 25);
        panel.add(valorLabel);

        valorText = new JTextField(20);
        valorText.setBounds(100, 20, 165, 25);
        panel.add(valorText);

        JLabel categoriaLabel = new JLabel("Categoria:");
        categoriaLabel.setBounds(10, 50, 80, 25);
        panel.add(categoriaLabel);

        categoriaText = new JTextField(20);
        categoriaText.setBounds(100, 50, 165, 25);
        panel.add(categoriaText);

        JLabel descricaoLabel = new JLabel("Descrição:");
        descricaoLabel.setBounds(10, 80, 80, 25);
        panel.add(descricaoLabel);

        descricaoText = new JTextField(20);
        descricaoText.setBounds(100, 80, 165, 25);
        panel.add(descricaoText);

        receitaButton = new JRadioButton("Receita");
        despesaButton = new JRadioButton("Despesa");

        receitaButton.setBounds(10, 110, 80, 25);
        despesaButton.setBounds(100, 110, 80, 25);
        
        panel.add(receitaButton);
        panel.add(despesaButton);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(receitaButton);
        grupo.add(despesaButton);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.setBounds(10, 140, 100, 25);
        panel.add(salvarButton);

        JButton historicoButton = new JButton("Histórico");
        historicoButton.setBounds(120, 140, 100, 25);
        panel.add(historicoButton);

        JButton resumoButton = new JButton("Resumo Financeiro");
        resumoButton.setBounds(230, 140, 150, 25);
        panel.add(resumoButton);

        JButton categoriasButton = new JButton("Gerenciar Categorias");
        categoriasButton.setBounds(10, 180, 150, 25);
        panel.add(categoriasButton);

        salvarButton.addActionListener(e -> salvarTransacao());
        historicoButton.addActionListener(e -> new TelaHistorico(gerenciador).setVisible(true));
        resumoButton.addActionListener(e -> new TelaResumoFinanceiro(gerenciador).setVisible(true));
        categoriasButton.addActionListener(e -> new TelaGerenciamentoCategorias(new dao.GerenciadorCategorias()).setVisible(true));
    }

    private void salvarTransacao() {
        try {
            double valor = Double.parseDouble(valorText.getText());
            String categoria = categoriaText.getText();
            String descricao = descricaoText.getText();
            LocalDate data = LocalDate.now();
            boolean receita = receitaButton.isSelected();

            Transacao transacao = new Transacao(valor, categoria, data, descricao, receita);
            gerenciador.adicionarTransacao(transacao);

            JOptionPane.showMessageDialog(this, "Transação cadastrada com sucesso!");
            limparCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um valor válido.");
        }
    }

    private void limparCampos() {
        valorText.setText("");
        categoriaText.setText("");
        descricaoText.setText("");
        receitaButton.setSelected(false);
        despesaButton.setSelected(false);
    }
}