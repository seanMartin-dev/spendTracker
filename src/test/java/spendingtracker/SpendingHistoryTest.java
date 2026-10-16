package spendingtracker;

import spendingtracker.Category;
import spendingtracker.Transaction;
import spendingtracker.SpendingHistory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SpendingHistoryTest {

    @Test
    public void filterByCategory_returnsCorrectMatches() {
        SpendingHistory history = new SpendingHistory();

        Transaction t1 = new Transaction(10, Category.GROCERIES, "01-01-2026");
        Transaction t2 = new Transaction(20, Category.FUEL, "01-01-2026 13:00");
        Transaction t3 = new Transaction(5, Category.GROCERIES, "01-01-2026 14:00");

        history.addTransaction(t1);
        history.addTransaction(t2);
        history.addTransaction(t3);

        var groceries = history.filterByCategory(Category.GROCERIES);

        assertEquals(2, groceries.size());
        assertTrue(groceries.contains(t1));
        assertTrue(groceries.contains(t3));
    }

    @Test
    public void filterByCategory_returnEmptyListWhenNoMatches() {
        SpendingHistory history = new SpendingHistory();

        Transaction t1 = new Transaction(10, Category.GROCERIES, "01-01-2026");
        Transaction t2 = new Transaction(20, Category.TAXI, "01-01-2026 13:00");

        history.addTransaction(t1);
        history.addTransaction(t2);

        var fuel = history.filterByCategory(Category.FUEL);

        assertEquals(0, fuel.size());
    }
}
