package spendingtracker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;



public class UserInterface {

    private Scanner obj;
    private SpendingHistory history;

    public UserInterface() {
        obj = new Scanner(System.in);
        history = new SpendingHistory();
    }

    public void start() {
        while (true) {
            System.out.println("1. Add transaction");
            System.out.println("2. View all");
            System.out.println("3. Filter by category");
            System.out.println("4. View total");
            System.out.println("5. Exit");

            String raw = obj.nextLine().trim();
            int choice = Integer.parseInt(raw);

            switch (choice) {
                case 1 -> addTransaction();
                case 2 -> viewAll();
                case 3 -> filter();
                case 4 -> total();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void addTransaction() {
        // category, amount, data, time logic goes here
        Category category = getCategory();
        Double amount = getAmount();
        LocalDate date = askForDate();
        String time = askForTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String timestamp = date.format(formatter) + " " + time;
        
        // then create Transaction and add to history
        Transaction t = new Transaction(amount, category, timestamp);
        
        history.addTransaction(t);
        System.out.println("Transaction added!");
    }

    // method for ui internal use

    // Handles category selection with confirmation
    private Category getCategory(){
        Category[] cats = Category.values();
        Category selected = null;

        while (true) {
            int i = 1;
            for (Category c : Category.values()) {
                System.out.println("Press " + i++ +" for " + c);
            }

            System.out.println("Please chose a number to select a category: ");
            int userChoice = obj.nextInt();

            if (userChoice >= 1 && userChoice <= cats.length) {
                selected = cats[userChoice -1];
                System.out.println("User selected: " + selected + ". Correct? (y/n)");

                String confirm = obj.next();
                if (confirm.equalsIgnoreCase("y")) {
                    break;
                }

                System.out.println("Okay, let's try again.\n");
            } else {
                System.out.println("Invalid choice");
            }
        }
        
        return selected;
    }
    
    // Prompts user for amount and validates confirmation
    private Double getAmount() {
        Double amount = null;

        while (true) {
            
            System.out.println("Amount of transaction:");
            Double temp = obj.nextDouble();

            System.out.println("You entered: £" + temp + "\nCorrect? (y/n)");
            String confirm = obj.next();

            if(confirm.equalsIgnoreCase("y")) {
                amount = temp;
                break; // exit loop, amount confirmed
            }
        
        System.out.println("Okay, let's try again.");
        }

        return amount;
    }
    
    // Asks user for a date in dd-MM-yyy format with validation
    private LocalDate askForDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while (true) {
            System.out.println("Enter date (dd-MM-yyyy):");
            String input = obj.nextLine().trim();

            try {
                LocalDate temp = LocalDate.parse(input, formatter);
                System.out.println("You entered: " + temp.format(formatter) + ". Correct? (y/n)");
                if (obj.nextLine().trim().equalsIgnoreCase("y")) return temp;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use dd-MM-yyyy.\n");
            }
        }
    }

    // Asks user for time or defaults to 00:00 if empty
    private String askForTime() {
        while (true) {
            System.out.println("Enter time (HH:mm) or press Enter if unknown:");
            String time = obj.nextLine().trim();

            if (time.isEmpty()) return "00:00";

            System.out.println("You entered: " + time + ". Correct? (y/n)");
            if (obj.nextLine().trim().equalsIgnoreCase("y")) return time;

            System.out.println("Okay, try again.");
        }
    }
    


    private void viewAll() {
        history.printAll();
    }

    private void filter() {
        // call history.filterByCategory()
        Category category = getCategory();
        List<Transaction> results = history.filterByCategory(category);

        if (results.isEmpty()) {
            System.out.println("No transactions found for: " + category);
        } else {
            System.out.println("Transactions for: " + category);
            for (Transaction t : results) {
                System.out.println(t);
            }
        }
    }

    private void total() {
       double total = history.getTotalSpend();
       System.out.println("Total spend: £" + total);
    }
}