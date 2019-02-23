package com.mentalmachines.han.data.repository.base;

import java.util.List;

public interface Repository<T> {
    void add(T item);

    void add(Iterable<T> items);
    void update(T item);
    void remove(T item);
    void remove(int id);
    List<T> query(int id);
}
