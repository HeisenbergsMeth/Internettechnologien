package de.falkmarinov.Internettechnologien.repository;

import java.util.Collection;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(int id);

    Collection<T> getAll();

    void save(T t);

    int update(T t);

    int delete(T t);
}
