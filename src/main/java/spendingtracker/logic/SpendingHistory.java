package spendingtracker.logic;

import spendingtracker.model.Category;
import spendingtracker.storage.Storage;
import spendingtracker.model.Transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
/**
 * Stores and manages all recorded transactions.
 * Acts as the application's in‑memory "database".
 */
public class SpendingHistory {

    private final Storage storage;
    private final List<Transaction> transactions;

    /**
     * Creates a SpendingHistory that loads existing data at startup.
     *
     * @param storage the JSON storage handler
     */
    public SpendingHistory(Storage storage) {
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

    public double getMonthlyTotalByCategory(int month, int year, Category category) {
        double total = 0.0;

        // filter by year then month
        List<Transaction> sorted = sortByDate();

        for (Transaction t : sorted) {
            LocalDateTime dt = t.getDateTime();
            
            int tYear = dt.getYear();
            int tMonth = dt.getMonthValue();

            // Year filtering
            if(tYear < year) continue;
            if(tYear > year) break;

            // Month filtering
            if(tMonth < month) continue;
            if(tMonth > month) break;

            // Category filtering
            if(t.getCategory() == category) {
                total += t.getAmount();
            }
        }

        return total;
    }

    public double getMonthlyTotal(int month, int year) {
        double total = 0.0;

        for (Transaction t : transactions) {
            LocalDateTime dt = t.getDateTime();

            boolean sameMonth = dt.getMonthValue() == month;
            boolean sameYear = dt.getYear() == year;

            if (sameMonth && sameYear) {
                total += t.getAmount();
            }
        }
        return total;
    }

    public List<Transaction> sortByAmount(){
        return transactions.stream()
        .sorted(Comparator.comparing(Transaction::getAmount))
        .toList();
    }

    public List<Transaction> sortByDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm");

        return transactions.stream()
                .sorted(Comparator.comparing(t ->
                    LocalDateTime.parse(t.getTimestamp(), formatter)))
                .toList();
    }   
    
}
