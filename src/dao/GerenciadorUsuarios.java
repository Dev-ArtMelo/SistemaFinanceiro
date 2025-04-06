package dao;
import java.util.HashMap;
import java.util.Map;

public class GerenciadorUsuarios {
    private Map<String, String> usuarios = new HashMap<>();

    public boolean cadastrarUsuario(String email, String senha) {
        if (usuarios.containsKey(email)) {
            return false; // Usuário já existe
        }
        usuarios.put(email, senha);
        return true;
    }

    public boolean autenticarUsuario(String email, String senha) {
        return usuarios.containsKey(email) && usuarios.get(email).equals(senha);
    }
}