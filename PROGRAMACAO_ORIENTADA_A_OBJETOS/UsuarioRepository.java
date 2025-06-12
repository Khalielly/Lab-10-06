import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.List;

public class UsuarioRepository {
    private Dao<Usuario, Integer> usuarioDao;
    private Database database;
    
    public UsuarioRepository(Database database) {
        this.database = database;
        setupRepository();
    }
    
    private void setupRepository() {
        try {
            usuarioDao = DaoManager.createDao(database.getConnection(), Usuario.class);
            TableUtils.createTableIfNotExists(database.getConnection(), Usuario.class);
            System.out.println("Repositório configurado!");
        } catch (SQLException e) {
            System.err.println("Erro na configuração: " + e.getMessage());
        }
    }
    
    public void criar(Usuario usuario) {
        try {
            usuarioDao.create(usuario);
            System.out.println("SALVO no banco! ID: " + usuario.getId());
        } catch (SQLException e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
        }
    }
    
    public Usuario buscar(int id) {
        try {
            Usuario u = usuarioDao.queryForId(id);
            if(u != null) {
                System.out.println("ENCONTRADO: " + u);
            } else {
                System.out.println("Usuário não encontrado!");
            }
            return u;
        } catch (SQLException e) {
            System.err.println("Erro na busca: " + e.getMessage());
            return null;
        }
    }
    
    public void atualizar(Usuario usuario) {
        try {
            usuarioDao.update(usuario);
            System.out.println("ATUALIZADO com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro na atualização: " + e.getMessage());
        }
    }
    
    public void deletar(int id) {
        try {
            usuarioDao.deleteById(id);
            System.out.println("DELETADO com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar: " + e.getMessage());
        }
    }
    
    public void listarTodos() {
        try {
            List<Usuario> usuarios = usuarioDao.queryForAll();
            System.out.println("\n--- TODOS OS USUÁRIOS ---");
            for(Usuario u : usuarios) {
                System.out.println(u);
            }
            System.out.println("-------------------------");
        } catch (SQLException e) {
            System.err.println("Erro na listagem: " + e.getMessage());
        }
    }
}