package ui;
import javax.swing.*;
import dao.GerenciadorTransacoes;
import Modelo.Transacao;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TelaResumoFinanceiro extends JFrame {
    private GerenciadorTransacoes gerenciador;
    private JTextField dataInicioText, dataFimText;
    private JLabel saldoValor, receitaValor, despesaValor;

    public TelaResumoFinanceiro(GerenciadorTransacoes gerenciador) {
        this.gerenciador = gerenciador;
        setTitle("Resumo Financeiro");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel dataInicioLabel = new JLabel("Data Início:");
        dataInicioLabel.setBounds(10, 10, 100, 25);
        panel.add(dataInicioLabel);

        dataInicioText = new JTextField();
        dataInicioText.setBounds(120, 10, 150, 25);
        panel.add(dataInicioText);

        JLabel dataFimLabel = new JLabel("Data Fim:");
        dataFimLabel.setBounds(10, 40, 100, 25);
        panel.add(dataFimLabel);

        dataFimText = new JTextField();
        dataFimText.setBounds(120, 40, 150, 25);
        panel.add(dataFimText);

        JButton calcularButton = new JButton("Calcular");
        calcularButton.setBounds(10, 70, 100, 25);
        panel.add(calcularButton);

        JLabel saldoLabel = new JLabel("Saldo Total:");
        saldoLabel.setBounds(10, 100, 100, 25);
        panel.add(saldoLabel);

        saldoValor = new JLabel("R$ 0,00");
        saldoValor.setBounds(120, 100, 100, 25);
        panel.add(saldoValor);

        JLabel receitaLabel = new JLabel("Receitas:");
        receitaLabel.setBounds(10, 130, 100, 25);
        panel.add(receitaLabel);

        receitaValor = new JLabel("R$ 0,00");
        receitaValor.setBounds(120, 130, 100, 25);
        panel.add(receitaValor);

        JLabel despesaLabel = new JLabel("Despesas:");
        despesaLabel.setBounds(10, 160, 100, 25);
        panel.add(despesaLabel);

        despesaValor = new JLabel("R$ 0,00");
        despesaValor.setBounds(120, 160, 100, 25);
        panel.add(despesaValor);

        calcularButton.addActionListener(e -> calcularResumo());

        add(panel);
    }

    private void calcularResumo() {
        LocalDate dataInicio = dataInicioText.getText().isEmpty() ? null : LocalDate.parse(dataInicioText.getText());
        LocalDate dataFim = dataFimText.getText().isEmpty() ? null : LocalDate.parse(dataFimText.getText());

        List<Transacao> transacoesFiltradas = gerenciador.getTransacoes().stream()
                .filter(t -> (dataInicio == null || !t.getData().isBefore(dataInicio)))
                .filter(t -> (dataFim == null || !t.getData().isAfter(dataFim)))
                .collect(Collectors.toList());

        double saldo = transacoesFiltradas.stream().mapToDouble(t -> t.isReceita() ? t.getValor() : -t.getValor()).sum();
        double totalReceitas = transacoesFiltradas.stream().filter(Transacao::isReceita).mapToDouble(Transacao::getValor).sum();
        double totalDespesas = transacoesFiltradas.stream().filter(t -> !t.isReceita()).mapToDouble(Transacao::getValor).sum();

        saldoValor.setText("R$ " + saldo);
        receitaValor.setText("R$ " + totalReceitas);
        despesaValor.setText("R$ " + totalDespesas);
    }
}