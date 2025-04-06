package dao;
import Modelo.Transacao;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorTransacoes {
    private List<Transacao> transacoes = new ArrayList<>();

    public void adicionarTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }

    public List<Transacao> buscarPorCategoria(String categoria) {
        List<Transacao> resultado = new ArrayList<>();
        for (Transacao t : transacoes) {
            if (t.getCategoria().equalsIgnoreCase(categoria)) {
                resultado.add(t);
            }
        }
        return resultado;
    }

    public double calcularSaldo() {
        double saldo = 0;
        for (Transacao t : transacoes) {
            saldo += t.isReceita() ? t.getValor() : -t.getValor();
        }
        return saldo;
    }

    public double calcularReceitas() {
        return transacoes.stream()
                .filter(Transacao::isReceita)
                .mapToDouble(Transacao::getValor)
                .sum();
    }

    public double calcularDespesas() {
        return transacoes.stream()
                .filter(t -> !t.isReceita())
                .mapToDouble(Transacao::getValor)
                .sum();
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }
}