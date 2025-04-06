package dao;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorCategorias {
    private List<String> categorias = new ArrayList<>();
    private final String ARQUIVO_CATEGORIAS = "categorias.txt";

    public GerenciadorCategorias() {
        carregarCategorias();
    }

    public void adicionarCategoria(String categoria) {
        if (!categorias.contains(categoria)) {
            categorias.add(categoria);
            salvarCategorias();
        }
    }

    public void editarCategoria(String categoriaAntiga, String novaCategoria) {
        int index = categorias.indexOf(categoriaAntiga);
        if (index != -1) {
            categorias.set(index, novaCategoria);
            salvarCategorias();
        }
    }

    public void excluirCategoria(String categoria) {
        categorias.remove(categoria);
        salvarCategorias();
    }

    public List<String> listarCategorias() {
        return categorias;
    }

    private void salvarCategorias() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_CATEGORIAS))) {
            for (String categoria : categorias) {
                writer.write(categoria);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar categorias: " + e.getMessage());
        }
    }

    private void carregarCategorias() {
        categorias.clear(); // Limpa a lista antes de recarregar
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_CATEGORIAS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                categorias.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Nenhuma categoria encontrada.");
        }
    }
}