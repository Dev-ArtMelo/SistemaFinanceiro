package ui;
import javax.swing.*;
import dao.GerenciadorCategorias;
import java.awt.*;

public class TelaGerenciamentoCategorias extends JFrame {
    private GerenciadorCategorias gerenciador;
    private JTextField categoriaText;
    private JList<String> listaCategorias;
    private DefaultListModel<String> modeloLista;

    public TelaGerenciamentoCategorias(GerenciadorCategorias gerenciador) {
        this.gerenciador = gerenciador;
        setTitle("Gerenciamento de Categorias");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel categoriaLabel = new JLabel("Categoria:");
        categoriaLabel.setBounds(10, 20, 80, 25);
        panel.add(categoriaLabel);

        categoriaText = new JTextField();
        categoriaText.setBounds(100, 20, 165, 25);
        panel.add(categoriaText);

        JButton adicionarButton = new JButton("Adicionar");
        adicionarButton.setBounds(10, 60, 100, 25);
        panel.add(adicionarButton);

        JButton editarButton = new JButton("Editar");
        editarButton.setBounds(120, 60, 100, 25);
        panel.add(editarButton);

        JButton excluirButton = new JButton("Excluir");
        excluirButton.setBounds(230, 60, 100, 25);
        panel.add(excluirButton);

        modeloLista = new DefaultListModel<>();
        listaCategorias = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaCategorias);
        scrollPane.setBounds(10, 100, 360, 150);
        panel.add(scrollPane);

        adicionarButton.addActionListener(e -> adicionarCategoria());
        editarButton.addActionListener(e -> editarCategoria());
        excluirButton.addActionListener(e -> excluirCategoria());

        atualizarLista();
        add(panel);
    }

    private void adicionarCategoria() {
        String categoria = categoriaText.getText();
        if (!categoria.isEmpty()) {
            gerenciador.adicionarCategoria(categoria);
            atualizarLista();
        }
    }

    private void editarCategoria() {
        String categoriaSelecionada = listaCategorias.getSelectedValue();
        if (categoriaSelecionada != null) {
            String novaCategoria = JOptionPane.showInputDialog("Novo nome para a categoria:", categoriaSelecionada);
            if (novaCategoria != null && !novaCategoria.isEmpty()) {
                gerenciador.editarCategoria(categoriaSelecionada, novaCategoria);
                atualizarLista();
            }
        }
    }

    private void excluirCategoria() {
        String categoriaSelecionada = listaCategorias.getSelectedValue();
        if (categoriaSelecionada != null) {
            gerenciador.excluirCategoria(categoriaSelecionada);
            atualizarLista();
        }
    }

    private void atualizarLista() {
        modeloLista.clear();
        for (String categoria : gerenciador.listarCategorias()) {
            modeloLista.addElement(categoria);
        }
    }
}