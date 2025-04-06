package Modelo;
import java.time.LocalDate;

public class Transacao {
    private double valor;
    private String categoria;
    private LocalDate data;
    private String descricao;
    private boolean receita;

    public Transacao(double valor, String categoria, LocalDate data, String descricao, boolean receita) {
        this.valor = valor;
        this.categoria = categoria;
        this.data = data;
        this.descricao = descricao;
        this.receita = receita;
    }

    public double getValor() { return valor; }
    public String getCategoria() { return categoria; }
    public LocalDate getData() { return data; }
    public String getDescricao() { return descricao; }
    public boolean isReceita() { return receita; }
}