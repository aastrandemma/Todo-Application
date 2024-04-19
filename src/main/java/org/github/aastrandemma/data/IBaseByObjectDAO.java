package org.github.aastrandemma.data;

import java.util.List;

public interface IBaseByObjectDAO<T> {
    T persist(T t);
    List<T> findAll();
}