package main.java.org.example.dao;

import main.java.org.example.conn.ConnectionFactory;
import main.java.org.example.model.EventType;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EventTypeDAO implements DAO<EventType> {

    private final EntityManager entityManager = new ConnectionFactory().getConnection();

    @Override
    public EventType save(EventType object) {
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
    public EventType update(EventType object) {
        EventType eventTypeUp = null;
        try {
            this.entityManager.getTransaction().begin();
            if (object.getId() == null) {
                this.entityManager.persist(object);
            } else {
                eventTypeUp = this.entityManager.merge(object);
            }
            this.entityManager.getTransaction().commit();
        } catch (Exception exception) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return eventTypeUp;
    }

    @Override
    public EventType delete(Long id) {
        EventType eventType = null;
        try {
            eventType = entityManager.find(EventType.class, id);
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(eventType);
            this.entityManager.getTransaction().commit();
        } catch (Exception exception) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return eventType;
    }

    @Override
    public EventType findById(Long id) {
        EventType eventType = null;
        try {
            eventType = entityManager.find(EventType.class, id);
        } catch (Exception e) {
            System.out.println("error find id\n" + e);
        }
        return eventType;
    }

    public List<EventType> getList() {
        Query query = this.entityManager.createQuery("SELECT p FROM EventType as p");
        return query.getResultList();
    }

}
