package spendingtracker.storage;

import spendingtracker.model.Transaction;
import java.util.List;

public interface Storage {
    List<Transaction> load();
    void save(List<Transaction> transactions);
}