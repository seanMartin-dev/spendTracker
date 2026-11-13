package spendingtracker.ui;

import spendingtracker.logic.SpendingHistory;
import spendingtracker.model.Category;
import spendingtracker.ui.InputHandler;
import spendingtracker.ui.Display;

public class ReportsMenu {

    private final SpendingHistory history;
    private final InputHandler input;
    private final Display display;

    public ReportsMenu(SpendingHistory history, InputHandler input) {
        this.history = history;
        this.input = input;
        this.display = new Display();
    }

    public void show() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Reports Menu === ");
            System.out.println("1. View total spending for a month");
            System.out.println("2. View total spending for a month by category");
            System.out.println("3. View category breakdown for a month");
            System.out.println("4. View all transactions for a month");
            System.out.println("5. View all transactions");
            System.out.println("6. Filter by category");
            System.out.println("7. View total spend");
            System.out.println("8. Back to main menu");
            System.out.print("Choose an option: ");

            int choice = input.readMenuChoice();

            switch (choice) {
                case 1 -> viewMonthlyTotal();
                case 2 -> viewMonthlyTotalByCategory();
                case 3 -> viewCategoryBreakdown();
                case 4 -> viewTransactionsForMonth();
                case 5 -> display.showTransactions(history.getAll());
                case 6 -> filter();
                case 7 -> display.showTotal(history.getTotalSpend());
                case 8 -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void viewMonthlyTotal() {
        int month = input.readIntRange("Enter month (1-12)", 1, 12);
        int year = input.readIntRange("Enter year:", 2000,  2100);

        double total = history.getMonthlyTotal(month, year);
        System.out.printf("Total spending for %d/%d: £%.2f%n", month, year, total);
    }

    private void viewMonthlyTotalByCategory() {
        int month = input.readIntRange("Enter month (1-12)", 1, 12);
        int year = input.readIntRange("Enter year", 2026, 2027);
        Category category = askCategory();

        double total = history.getMonthlyTotalByCategory(month, year, category);
        
        if (total == 0) {
            System.out.printf("No transactions found for %s in %d/%d.%n",
                    category, month, year);
            return;
        }
        System.out.printf("%s spending for %d/%d: £%.2f%n",
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

    private void filter() {
        Category c = input.readCategory();
        display.showTransactions(history.filterByCategory(c));
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