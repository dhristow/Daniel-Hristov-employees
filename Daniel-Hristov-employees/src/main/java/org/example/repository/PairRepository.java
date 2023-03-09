package org.example.repository;

import org.example.model.Employee;
import org.example.model.Pair;

import java.util.Collection;
import java.util.List;

public interface PairRepository {

    int size();

    void save(Pair pair);

    void saveAll(Collection<Pair> pairs);

    void remove(Pair pair);

    void removeAll(Collection<Pair> pairs);

    List<Pair> getAllPairs();
}
