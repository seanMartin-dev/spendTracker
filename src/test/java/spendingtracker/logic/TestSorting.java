package spendingtracker.logic;

import spendingtracker.logic.SpendingHistory;
import spendingtracker.model.Category;
import spendingtracker.model.Transaction;
import spendingtracker.storage.TestStorage;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSorting {

    @Test
    void testSortByAmount() {
        SpendingHistory history = new SpendingHistory(new TestStorage());

        history.addTransaction(new Transaction(20, Category.GROCERIES, "29-10-2026 10:00"));
        history.addTransaction(new Transaction(5, Category.FUEL, "11-10-2026 10:00"));
        history.addTransaction(new Transaction(15, Category.ENERGY, "19-10-2026 11:30"));

        List<Transaction> sorted = history.sortByAmount();

        assertEquals(5, sorted.get(0).getAmount());
        assertEquals(15, sorted.get(1).getAmount());
        assertEquals(20, sorted.get(2).getAmount());
    }

    @Test
    void testSortByDate() {
        SpendingHistory history = new SpendingHistory(new TestStorage());

        history.addTransaction(new Transaction(20, Category.GROCERIES, "29-10-2026 10:00"));
        history.addTransaction(new Transaction(5, Category.FUEL, "28-10-2026 10:00"));
        history.addTransaction(new Transaction(15, Category.ENERGY, "29-10-2026 09:00"));

        List<Transaction> sorted = history.sortByDate();

        assertEquals("28-10-2026 10:00", sorted.get(0).getTimestamp());
        assertEquals("29-10-2026 09:00", sorted.get(1).getTimestamp());
        assertEquals("29-10-2026 10:00", sorted.get(2).getTimestamp());
    }
}
