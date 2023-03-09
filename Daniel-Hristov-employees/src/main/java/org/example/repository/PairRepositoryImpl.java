package org.example.repository;

import org.example.model.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PairRepositoryImpl implements PairRepository{

    private List<Pair> data = new ArrayList<>();

    @Override
    public int size() {
        return this.data.size();
    }

    @Override
    public void save(Pair pair) {
        this.data.add(pair);
    }

    @Override
    public void saveAll(Collection<Pair> pairs) {
        this.data.addAll(pairs);
    }

    @Override
    public void remove(Pair pair) {
        this.data.remove(pair);
    }

    @Override
    public void removeAll(Collection<Pair> pairs) {
        this.data.removeAll(pairs);
    }

    @Override
    public List<Pair> getAllPairs() {
        return Collections.unmodifiableList(this.data);
    }
}
