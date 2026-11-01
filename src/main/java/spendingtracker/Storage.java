package spendingtracker;

import java.util.List;

public interface Storage {
    List<Transaction> load();
    void save(List<Transaction> transactions);
}