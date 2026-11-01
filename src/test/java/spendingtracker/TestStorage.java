package spendingtracker;

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