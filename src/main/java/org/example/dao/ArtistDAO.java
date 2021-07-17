package main.java.org.example.dao;

import main.java.org.example.conn.ConnectionFactory;
import main.java.org.example.model.Artist;

import javax.persistence.EntityManager;

public class ArtistDAO implements DAO<Artist> {

    private final EntityManager entityManager = new ConnectionFactory().getConnection();

    @Override
    public Artist save(Artist object) {
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
    public Artist update(Artist object) {
        Artist artistUp = null;
        try {
            this.entityManager.getTransaction().begin();
            if (object.getId() == null) {
                this.entityManager.persist(object);
            } else {
                artistUp = this.entityManager.merge(object);
            }
            this.entityManager.getTransaction().commit();
        } catch (Exception exception) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return artistUp;
    }

    @Override
    public Artist delete(Long id) {
        Artist artist = null;
        try {
            artist = entityManager.find(Artist.class, id);
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(artist);
            this.entityManager.getTransaction().commit();
        } catch (Exception exception) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return artist;
    }

    @Override
    public Artist findById(Long id) {
        Artist artist = null;
        try {
            artist = entityManager.find(Artist.class, id);
        } catch (Exception e) {
            System.out.println("error find id\n" + e);
        }
        return artist;
    }
}
