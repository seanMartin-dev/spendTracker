package spendingtracker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserInterface {

    private final InputHandler input = new InputHandler();
    private final Display display = new Display();
    private final JsonStorage storage = new JsonStorage();
    private final SpendingHistory history = new SpendingHistory(storage);

    public void start() {
        while (true) {
            display.showMenu();
            int choice = input.readMenuChoice();

            switch (choice) {
                case 1 -> addTransaction();
                case 2 -> display.showTransactions(history.getAll());
                case 3 -> filter();
                case 4 -> display.showTotal(history.getTotalSpend());
                case 5 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void addTransaction() {
        Category c = input.readCategory();
        double amount = input.readAmount();
        LocalDate date = input.readDate();
        String time = input.readTime();

        String timestamp = date.format(DateTimeFormatter.ofPattern("dd-MM-uuuu")) + " " + time;

        history.addTransaction(new Transaction(amount, c, timestamp));
        System.out.println("Transaction added!");
    }

    private void filter() {
        Category c = input.readCategory();
        display.showTransactions(history.filterByCategory(c));
    }
}
