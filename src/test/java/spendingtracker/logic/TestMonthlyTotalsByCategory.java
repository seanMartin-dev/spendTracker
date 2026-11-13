package spendingtracker.logic;

import spendingtracker.model.Transaction;
import spendingtracker.model.Category;
import spendingtracker.logic.SpendingHistory;
import spendingtracker.storage.TestStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMonthlyTotalsByCategory {

    private SpendingHistory history;

    @BeforeEach
    void setup() {
        history = new SpendingHistory(new TestStorage());
    }

    @Test
    void testEmptyCategoryTotal() {
        double total = history.getMonthlyTotalByCategory(10, 2026, Category.GROCERIES);
        assertEquals(0.0, total);
    }

    @Test
    void testSingleCategoryTotal() {
        history.addTransaction(new Transaction(50, Category.GROCERIES, "10-10-2026 12:00"));
        double total = history.getMonthlyTotalByCategory(10, 2026, Category.GROCERIES);
        assertEquals(50.0, total); 
    }

    @Test
    void testMultipleCategoriesSameMonth() {
        history.addTransaction(new Transaction(50, Category.GROCERIES, "10-10-2026 12:00"));
        history.addTransaction(new Transaction(20, Category.FUEL, "10-10-2026 13:00"));
        history.addTransaction(new Transaction(30, Category.GROCERIES, "11-10-2026 13:00"));

        double total = history.getMonthlyTotalByCategory(10, 2026, Category.GROCERIES);
        assertEquals(80.0, total);
    }

    @Test
    void testDifferentMonthsIgnored() {
        history.addTransaction(new Transaction(50, Category.GROCERIES, "10-10-2026 12:00"));
        history.addTransaction(new Transaction(30, Category.GROCERIES, "10-11-2026 12:00"));

        double total = history.getMonthlyTotalByCategory(10, 2026, Category.GROCERIES);
        assertEquals(50.0, total);
    }


}