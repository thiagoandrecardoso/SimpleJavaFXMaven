package main.java.org.example.dao;

import main.java.org.example.conn.ConnectionFactory;
import main.java.org.example.model.Events;

import javax.persistence.EntityManager;

public class EventsDAO implements DAO<Events> {

    private final EntityManager entityManager = new ConnectionFactory().getConnection();

    @Override
    public Events save(Events object) {
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
    public Events update(Events object) {
        Events eventsUp = null;
        try {
            this.entityManager.getTransaction().begin();
            if (object.getId() == null) {
                this.entityManager.persist(object);
            } else {
                eventsUp = this.entityManager.merge(object);
            }
            this.entityManager.getTransaction().commit();
        } catch (Exception exception) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return eventsUp;
    }

    @Override
    public Events delete(Long id) {
        Events events = null;
        try {
            events = entityManager.find(Events.class, id);
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(events);
            this.entityManager.getTransaction().commit();
        } catch (Exception exception) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return events;
    }

    @Override
    public Events findById(Long id) {
        Events events = null;
        try {
            events = entityManager.find(Events.class, id);
        } catch (Exception e) {
            System.out.println("error find id\n" + e);
        }
        return events;
    }
}
