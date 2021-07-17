package main.java.org.example.dao;

public interface DAO<T> {
    T save(T object);

    T update(T object);

    T delete(Long id);

    T findById(Long id);
}
