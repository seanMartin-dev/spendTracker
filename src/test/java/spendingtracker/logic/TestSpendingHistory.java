package spendingtracker.logic;

import spendingtracker.logic.SpendingHistory;
import spendingtracker.model.Category;
import spendingtracker.model.Transaction;
import spendingtracker.storage.TestStorage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSpendingHistory {

    @Test
    void testAddTransaction() {
        SpendingHistory history = new SpendingHistory(new TestStorage());

        Transaction t = new Transaction(10.50, Category.FUEL, "21-10-2026 13:00");
        history.addTransaction(t);

        assertEquals(1, history.getAll().size());
        assertEquals(t, history.getAll().get(0));
    }

    @Test
    void testTotalSpend() {
        SpendingHistory history = new SpendingHistory(new TestStorage());

        history.addTransaction(new Transaction(10, Category.FUEL, "21-10-2026 13:00"));
        history.addTransaction(new Transaction(5, Category.TAXI, "21-10-2026 14:00"));

        assertEquals(15, history.getTotalSpend());
    }

    @Test
    void testFilterByCategory() {
        SpendingHistory history = new SpendingHistory(new TestStorage());

        history.addTransaction(new Transaction(10, Category.TAXI, "21-10-2026 13:00"));
        history.addTransaction(new Transaction(5, Category.TAKEAWAY, "21-10-2026 14:00"));
        history.addTransaction(new Transaction(7, Category.TAXI, "21-10-2026 15:00"));

        var taxi = history.filterByCategory(Category.TAXI);

        assertEquals(2, taxi.size());
        assertEquals(Category.TAXI, taxi.get(0).getCategory());
        assertEquals(Category.TAXI, taxi.get(1).getCategory());
    }
}

