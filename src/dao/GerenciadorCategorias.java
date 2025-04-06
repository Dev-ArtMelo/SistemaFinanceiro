package dao;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorCategorias {
    private List<String> categorias = new ArrayList<>();

    public void adicionarCategoria(String categoria) {
        if (!categorias.contains(categoria)) {
            categorias.add(categoria);
        }
    }

    public void editarCategoria(String categoriaAntiga, String novaCategoria) {
        int index = categorias.indexOf(categoriaAntiga);
        if (index != -1) {
            categorias.set(index, novaCategoria);
        }
    }

    public void excluirCategoria(String categoria) {
        categorias.remove(categoria);
    }

    public List<String> listarCategorias() {
        return categorias;
    }
}