package main;

import db.Database;
import model.Locatario;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

public class Main {
    public static void main(String[] args) {
        try {
            Database db = new Database("quadras.db");
            var connection = db.getConnection();

            Dao<Locatario, Integer> locatarioDao = DaoManager.createDao(connection, Locatario.class);

            // CREATE
            Locatario novo = new Locatario("Danielly", "dani@email.com", "123456", "123.456.789-00");
            locatarioDao.create(novo);
            System.out.println("Locatário cadastrado!");

            // RETRIEVE
            Locatario recuperado = locatarioDao.queryForId(novo.getId());
            System.out.println("Recuperado: " + recuperado.getNome());

            // UPDATE
            recuperado.setNome("Danielly Khalil");
            locatarioDao.update(recuperado);
            System.out.println("Locatário atualizado!");

            // DELETE
            locatarioDao.delete(recuperado);
            System.out.println("Locatário deletado!");

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}