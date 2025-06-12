import java.sql.*;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

public class Database {
    private String databaseName = "aluguel_quadras.db";
    private JdbcConnectionSource connection = null;
   
    public Database() {}
    
    public JdbcConnectionSource getConnection() throws SQLException {
        if (connection == null) {
            try {
                connection = new JdbcConnectionSource("jdbc:sqlite:" + databaseName);
                System.out.println("Conexão com o banco estabelecida!");
            } catch (Exception e) {
                System.err.println("Erro na conexão: " + e.getMessage());
            }
        }
        return connection;
    }
   
    public void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexão fechada!");
            } catch (java.lang.Exception e) {
                System.err.println(e);
            }
        }
    }
}