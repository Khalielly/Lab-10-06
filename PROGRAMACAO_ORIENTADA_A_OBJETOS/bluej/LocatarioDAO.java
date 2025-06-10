import java.util.ArrayList;
import java.util.List;

public class LocatarioDAO {
    private List<Locatario> banco = new ArrayList<>();

    // CREATE
    public void salvar(Locatario locatario) {
        banco.add(locatario);
        System.out.println("Locatário salvo: " + locatario);
    }

    // RETRIEVE
    public Locatario buscarPorId(int id) {
        for (Locatario l : banco) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }

    // UPDATE
    public boolean atualizar(int id, String novoNome, String novoEmail) {
        Locatario l = buscarPorId(id);
        if (l != null) {
            l.setNome(novoNome);
            l.setEmail(novoEmail);
            System.out.println("Locatário atualizado: " + l);
            return true;
        }
        return false;
    }

    // DELETE
    public boolean deletar(int id) {
        Locatario l = buscarPorId(id);
        if (l != null) {
            banco.remove(l);
            System.out.println("Locatário removido: " + l);
            return true;
        }
        return false;
    }

    // LISTAR TODOS
    public List<Locatario> listarTodos() {
        return banco;
    }
}