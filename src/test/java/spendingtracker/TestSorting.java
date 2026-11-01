package spendingtracker;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestSorting {

    @Test
    void testSortByAmount() {
        SpendingHistory history = new SpendingHistory(new JsonStorage());

        history.addTransaction(new Transaction(20, Category.GROCERIES, "2026-10-29 10:00"));
        history.addTransaction(new Transaction(5, Category.FUEL, "2026-10-29 11:00"));
        history.addTransaction(new Transaction(15, Category.ENERGY, "2026-10-29 11:30"));

        List<Transaction> sorted = history.sortByAmount();

        assertEquals(5, sorted.get(0).getAmount());
        assertEquals(15, sorted.get(1).getAmount());
        assertEquals(20, sorted.get(2).getAmount());
    }
}
