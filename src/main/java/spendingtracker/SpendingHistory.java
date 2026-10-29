package spendingtracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores and manages all recorded transactions.
 * Acts as the application's in‑memory "database".
 */
public class SpendingHistory {

    private final JsonStorage storage;
    private final List<Transaction> transactions;

    /**
     * Creates a SpendingHistory that loads existing data at startup.
     *
     * @param storage the JSON storage handler
     */
    public SpendingHistory(JsonStorage storage) {
        this.storage = storage;

        // Load existing data (missing/corrupted file → empty list)
        this.transactions = new ArrayList<>(storage.load());
    }

    /**
     * Adds a new transaction and immediately persists the updated list.
     *
     * @param t the transaction to store
     */
    public void addTransaction(Transaction t) {
        transactions.add(t);
        storage.save(transactions);   // Auto‑save after every change
    }

    /**
     * Returns a defensive copy of all stored transactions.
     *
     * @return a list containing all transactions
     */
    public List<Transaction> getAll() {
        return new ArrayList<>(transactions);
    }

    /**
     * Filters transactions by a specific category.
     *
     * @param category the category to filter by
     * @return a list of transactions matching the category
     */
    public List<Transaction> filterByCategory(Category category) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getCategory() == category) {
                result.add(t);
            }
        }
        return result;
    }

    public double getTotalSpend() {
        double total = 0;
        for (Transaction t : transactions) {
            total += t.getAmount();
        }
        return total;
    }
}
