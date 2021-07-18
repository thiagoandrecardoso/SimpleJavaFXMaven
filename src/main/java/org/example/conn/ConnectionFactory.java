package main.java.org.example.conn;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
    private static final String DB_NAME = "javafx";
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory(DB_NAME);
    public EntityManager getConnection() {
        return factory.createEntityManager();
    }
}
