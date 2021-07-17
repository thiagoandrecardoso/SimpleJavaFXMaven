package main.java.org.example.dao;

import main.java.org.example.conn.ConnectionFactory;
import main.java.org.example.model.Cities;

import javax.persistence.EntityManager;

public class CitiesDAO implements DAO<Cities> {

    private final EntityManager entityManager = new ConnectionFactory().getConnection();

    @Override
    public Cities save(Cities object) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(object);
            this.entityManager.getTransaction().commit();
        } catch (Exception error) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return object;
    }

    @Override
    public Cities update(Cities object) {
        Cities citiesUp = null;
        try {
            this.entityManager.getTransaction().begin();
            if (object.getId() == null) {
                this.entityManager.persist(object);
            } else {
                citiesUp = this.entityManager.merge(object);
            }
            this.entityManager.getTransaction().commit();
        } catch (Exception exception) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return citiesUp;
    }

    @Override
    public Cities delete(Long id) {
        Cities cities = null;
        try {
            cities = entityManager.find(Cities.class, id);
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(cities);
            this.entityManager.getTransaction().commit();
        } catch (Exception exception) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return cities;
    }

    @Override
    public Cities findById(Long id) {
        Cities cities = null;
        try {
            cities = entityManager.find(Cities.class, id);
        } catch (Exception e) {
            System.out.println("error find id\n" + e);
        }
        return cities;
    }
}
