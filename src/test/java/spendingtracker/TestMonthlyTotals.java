package spendingtracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import spendingtracker.SpendingHistory;
import spendingtracker.TestStorage;
import spendingtracker.Transaction;

import static org.junit.jupiter.api.Assertions.*;

public class TestMonthlyTotals {

    private SpendingHistory history;

    @BeforeEach
    void setup() {
        history = new SpendingHistory(new TestStorage());
    }

    @Test
    void testMonthlyTotalEmpty() {
        assertEquals(0.0, history.getMonthlyTotal(3, 2026));
    }

    @Test
    void testMonthlyTotalSingleMonth () {
        SpendingHistory history = new SpendingHistory(new TestStorage());

        history.addTransaction(new Transaction(20, Category.GROCERIES, "10-10-2026 10:00"));
        history.addTransaction(new Transaction(15, Category.FUEL, "12-10-2026 12:00"));
        history.addTransaction(new Transaction(5, Category.ENERGY, "01-11-2026 09:00"));

        double total = history.getMonthlyTotal(10, 2026);

        assertEquals(35.0, total);
    }

    @Test
    void testMontlyTotalMultipleMonths() {
        SpendingHistory history = new SpendingHistory(new TestStorage());

        history.addTransaction(new Transaction(10, Category.GROCERIES, "01-10-2026 10:00"));
        history.addTransaction(new Transaction(20, Category.FUEL, "15-10-2026 11:00"));
        history.addTransaction(new Transaction(30, Category.ENERGY, "01-11-2026 10:00"));

        double total = history.getMonthlyTotal(10, 2026);

        assertEquals(30.0, total);
    }

    @Test
    void testMonthlyTotalDiffernetYears() {
        SpendingHistory history = new SpendingHistory(new TestStorage());

        history.addTransaction(new Transaction(10, Category.GROCERIES, "01-10-2026 10:00"));
        history.addTransaction(new Transaction(20, Category.FUEL, "01-11-2027 10:00"));

        double total = history.getMonthlyTotal(10, 2026);

        assertEquals(10.0, total);
    }
}