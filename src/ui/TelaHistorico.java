package ui;
import javax.swing.*;
import dao.GerenciadorTransacoes;
import Modelo.Transacao;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TelaHistorico extends JFrame {
    private GerenciadorTransacoes gerenciador;
    private JTextField categoriaText;
    private JTextField dataInicioText;
    private JTextField dataFimText;
    private JComboBox<String> tipoTransacaoCombo;
    private JTextArea historicoArea;

    public TelaHistorico(GerenciadorTransacoes gerenciador) {
        this.gerenciador = gerenciador;
        setTitle("Histórico de Transações");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel categoriaLabel = new JLabel("Categoria:");
        categoriaLabel.setBounds(10, 10, 100, 25);
        panel.add(categoriaLabel);

        categoriaText = new JTextField();
        categoriaText.setBounds(120, 10, 150, 25);
        panel.add(categoriaText);

        JLabel dataInicioLabel = new JLabel("Data Início:");
        dataInicioLabel.setBounds(10, 40, 100, 25);
        panel.add(dataInicioLabel);

        dataInicioText = new JTextField();
        dataInicioText.setBounds(120, 40, 150, 25);
        panel.add(dataInicioText);

        JLabel dataFimLabel = new JLabel("Data Fim:");
        dataFimLabel.setBounds(10, 70, 100, 25);
        panel.add(dataFimLabel);

        dataFimText = new JTextField();
        dataFimText.setBounds(120, 70, 150, 25);
        panel.add(dataFimText);

        JLabel tipoLabel = new JLabel("Tipo:");
        tipoLabel.setBounds(10, 100, 100, 25);
        panel.add(tipoLabel);

        tipoTransacaoCombo = new JComboBox<>(new String[]{"Todos", "Receita", "Despesa"});
        tipoTransacaoCombo.setBounds(120, 100, 150, 25);
        panel.add(tipoTransacaoCombo);

        JButton filtrarButton = new JButton("Filtrar");
        filtrarButton.setBounds(10, 130, 100, 25);
        panel.add(filtrarButton);

        historicoArea = new JTextArea();
        historicoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historicoArea);
        scrollPane.setBounds(10, 170, 460, 180);
        panel.add(scrollPane);

        filtrarButton.addActionListener(e -> filtrarTransacoes());

        add(panel);
    }

    private void filtrarTransacoes() {
        String categoria = categoriaText.getText().trim();
        String tipoSelecionado = (String) tipoTransacaoCombo.getSelectedItem();
        LocalDate dataInicio = dataInicioText.getText().isEmpty() ? null : LocalDate.parse(dataInicioText.getText());
        LocalDate dataFim = dataFimText.getText().isEmpty() ? null : LocalDate.parse(dataFimText.getText());

        List<Transacao> transacoesFiltradas = gerenciador.getTransacoes().stream()
                .filter(t -> (categoria.isEmpty() || t.getCategoria().equalsIgnoreCase(categoria)))
                .filter(t -> (dataInicio == null || !t.getData().isBefore(dataInicio)))
                .filter(t -> (dataFim == null || !t.getData().isAfter(dataFim)))
                .filter(t -> ("Todos".equals(tipoSelecionado) || 
                              ("Receita".equals(tipoSelecionado) && t.isReceita()) ||
                              ("Despesa".equals(tipoSelecionado) && !t.isReceita())))
                .collect(Collectors.toList());

        StringBuilder historicoTexto = new StringBuilder();
        for (Transacao t : transacoesFiltradas) {
            historicoTexto.append("Valor: R$ ").append(t.getValor())
                    .append(", Categoria: ").append(t.getCategoria())
                    .append(", Tipo: ").append(t.isReceita() ? "Receita" : "Despesa")
                    .append(", Data: ").append(t.getData())
                    .append("\n");
        }
        historicoArea.setText(historicoTexto.toString());
    }
}