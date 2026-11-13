package spendingtracker.storage;

import spendingtracker.model.Transaction;
import spendingtracker.storage.JsonStorage;

import java.util.List;

public class TestStorage implements Storage {
    @Override 
    public List<Transaction> load() {
        return List.of();
    }

    @Override 
    public void save (List<Transaction> transaction) {
        // do nothing
    }
}