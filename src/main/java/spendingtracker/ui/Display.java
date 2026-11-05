package spendingtracker.ui;

import spendingtracker.model.Transaction;
import java.util.List;

/**
 * Handles all console output for the CLI.
 * Responsible only for displaying information, never for logic.
 */
public class Display {
    
    public void showMenu() {
        System.out.println("1. Add transaction");
        System.out.println("2. View all tranasactions");
        System.out.println("3. Filter by category");
        System.out.println("4. View total spend");
        System.out.println("5. Sort transactions (choose type)");
        System.out.println("6. View monthly total");
        System.out.println("7. Exit");
    }

    /**
     * Display sortby menu
     */
    public void sortByMenu() {
        System.out.println("1. Sort transaction by amount");
        System.out.println("2. Sort transaction by date"); 
    }

    public void showTransactions(List<Transaction> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (Transaction t : list) {
            System.out.println(t);
        }
    }

    public void showTotal(double total) {
        System.out.println("Total spend: £" + total);
    }
}
