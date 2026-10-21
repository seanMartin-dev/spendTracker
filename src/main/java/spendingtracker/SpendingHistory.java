package spendingtracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores and manages all recorded transactions.
 * Acts as the application's in‑memory "database".
 */
public class SpendingHistory {

    /**
     * Internal list holding all transactions.
     * Kept private to protect data integrity.
     */
    private final List<Transaction> transactions = new ArrayList<>();

    /**
     * Adds a new transaction to the history.
     *
     * @param t the transaction to store
     */
    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    /**
     * Returns a copy of all stored transactions.
     * A defensive copy is used to prevent external modification
     * of the internal list.
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

    /**
     * Calculates the total amount spent across all transactions.
     *
     * @return the sum of all transaction amounts
     */
    public double getTotalSpend() {
        double total = 0;
        for (Transaction t : transactions) {
            total += t.getAmount();
        }
        return total;
    }
}
