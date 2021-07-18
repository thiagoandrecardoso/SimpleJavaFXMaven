package main.java.org.example.conn;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("javafx");
    public EntityManager getConnection() {
        return factory.createEntityManager();
    }
}
