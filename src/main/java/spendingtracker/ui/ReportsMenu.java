package spendingtracker.ui;

import spendingtracker.logic.SpendingHistory;
import spendingtracker.model.Category;
import spendingtracker.ui.InputHandler;

import java.util.Scanner;

public class ReportsMenu {

    private final SpendingHistory history;
    private final InputHandler input;

    public ReportsMenu(SpendingHistory history, InputHandler input) {
        this.history = history;
        this.input = input;
    }

    public void show() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Reports Menu === ");
            System.out.println("1. View total spending for a month");
            System.out.println("2. View total spending for a month by category");
            System.out.println("3. View category breakdown for a month");
            System.out.println("4. View all transactions for a month");
            System.out.println("5. Back to main menu");
            System.out.print("Choose an option: ");

            int choice = input.readMenuChoice();

            switch (choice) {
                case 1 -> viewMonthlyTotal();
                case 2 -> viewMonthlyTotalByCategory();
                case 3 -> viewCategoryBreakdown();
                case 4 -> viewTransactionsForMonth();
                case 5 -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void viewMonthlyTotal() {
        int month = input.readIntRange("Enter month (1-12)", 1, 12);
        int year = input.readIntRange("Enter year:", 2026,  2027);

        double total = history.getMonthlyTotal(month, year);
        System.out.printf("Total spending for %d/%d: £%.2f%n", month, year, total);
    }

    private void viewMonthlyTotalByCategory() {
        int month = input.readIntRange("Enter month (1-12)", 1, 12);
        int year = input.readIntRange("Enter year", 2026, 2027);
        Category category = askCategory();

        double total = history.getMonthlyTotalByCategory(month, year, category);
        System.out.printf("%s spending for %d/%d: £%.2%n",
                    category, month, year, total);
    }

    private void viewCategoryBreakdown() {
        int month = input.readIntRange("Enter month (1-12)", 1, 12);
        int year = input.readIntRange("Enter year", 2026, 2027);

        System.out.println("\nCategory Breakdown:");
        for (Category c : Category.values()) {
            double total = history.getMonthlyTotalByCategory(month, year, c);
            System.out.printf("%-15s £%.2f%n", c, total);
        }
    }

    private void viewTransactionsForMonth() {
        int month = input.readIntRange("Enter month (1-12)", 1, 12);
        int year = input.readIntRange("Enter year", 2026, 2027);

        System.out.println("\nTransactions for " + month + "/" + year + ":");

        history.sortByDate().stream()
                .filter(t -> {
                    var dt = t.getDateTime();
                    return dt.getYear() == year && dt.getMonthValue() == month;
                })
                .forEach(t -> System.out.println(t));
    }

    private Category askCategory() {
        System.out.println("Choose category:");
        int i = 1;
        for (Category c : Category.values()) {
            System.out.println(i++ + ". " + c);
        }
        int choice = input.readIntRange("Enter choice: ", 1, i);
        
        return Category.values()[choice -1];
    }
}