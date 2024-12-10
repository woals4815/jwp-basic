package next.dao;

import java.util.List;

public interface Dao<T> {
    public void insert(T object);
    public List<T> findAll();
    public T findById(int id);
}
