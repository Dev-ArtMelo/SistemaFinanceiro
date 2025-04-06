package ui;
import javax.swing.*;
import dao.GerenciadorTransacoes;
import dao.GerenciadorCategorias;
import Modelo.Transacao;
import java.awt.*;
import java.time.LocalDate;

public class TelaCadastroTransacao extends JFrame {
    private GerenciadorTransacoes gerenciadorTransacoes;
    private GerenciadorCategorias gerenciadorCategorias;
    private JTextField valorText, descricaoText;
    private JComboBox<String> categoriaComboBox;
    private JRadioButton receitaButton, despesaButton;
    private static TelaCadastroTransacao instancia;

    public static TelaCadastroTransacao getInstance() {
        return instancia;
    }

    public TelaCadastroTransacao(GerenciadorTransacoes gerenciadorTransacoes, GerenciadorCategorias gerenciadorCategorias) {
        instancia = this;
        this.gerenciadorTransacoes = gerenciadorTransacoes;
        this.gerenciadorCategorias = gerenciadorCategorias;
        setTitle("Cadastro de Transação");
        setSize(400, 400);
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

        categoriaComboBox = new JComboBox<>(gerenciadorCategorias.listarCategorias().toArray(new String[0]));
        categoriaComboBox.setBounds(100, 50, 165, 25);
        panel.add(categoriaComboBox);

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
        historicoButton.addActionListener(e -> new TelaHistorico(gerenciadorTransacoes).setVisible(true));
        resumoButton.addActionListener(e -> new TelaResumoFinanceiro(gerenciadorTransacoes).setVisible(true));
        categoriasButton.addActionListener(e -> new TelaGerenciamentoCategorias(gerenciadorCategorias, this).setVisible(true));
    }

    private void salvarTransacao() {
        try {
            double valor = Double.parseDouble(valorText.getText());
            String categoria = (String) categoriaComboBox.getSelectedItem();
            String descricao = descricaoText.getText();
            LocalDate data = LocalDate.now();
            boolean receita = receitaButton.isSelected();

            if (categoria == null || categoria.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, selecione uma categoria válida.");
                return;
            }

            Transacao transacao = new Transacao(valor, categoria, data, descricao, receita);
            gerenciadorTransacoes.adicionarTransacao(transacao);

            JOptionPane.showMessageDialog(this, "Transação cadastrada com sucesso!");
            limparCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um valor válido.");
        }
    }

    private void limparCampos() {
        valorText.setText("");
        descricaoText.setText("");
        receitaButton.setSelected(false);
        despesaButton.setSelected(false);
    }

    public void atualizarCategorias() {
        categoriaComboBox.setModel(new DefaultComboBoxModel<>(gerenciadorCategorias.listarCategorias().toArray(new String[0])));
    }
}