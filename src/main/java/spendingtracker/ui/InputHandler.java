package spendingtracker.ui;

import spendingtracker.model.Category;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class InputHandler {

    private final Scanner scanner = new Scanner(System.in);
    
    /**
     * Reads the user's menu choice as an integer.
     * 
     * Behaviour
     * - Reads input as a full line to avoid Scanner newline issues.
     * - Trims whitespace before parsing.
     * - Converts the input to an integer
     * - Does not perform range validation; the caller is responsible for
     *   checking whether the returned value corresponds to a valid menu option.
     * 
     * @return The user's menu choice as an integer.
     * @throws NumberFormatException if the input cannot be parsed as an integer.
     */
    public int readMenuChoice() {
        while (true) {
            String raw = scanner.nextLine().trim();

            try {
                return Integer.parseInt(raw);
            } catch (NumberFormatException e) {
                System.out.println("[WARNING] Please enter a valid number.");
            }
        }
    }

    /**
    * Prompts the user to select a Category from the enum list.
    * 
    * Behaviour:
    * - Displays all categories with numbered options.
    * - Reads user input as a full line (avoids Scanner newline bugs).
    * - Validates the number is within range.
    * - Shows the selected category and asks for confirmation.
    * - If the user confirms with 'y', the category is returned.
    * - Any invalid input or a 'no' response causes the loop to repeat.
    * 
    * @return A valid, user-confirmed Category selection.
    */
    public Category readCategory() {
        Category[] cats = Category.values();

        while (true) {
            // Display numbered list of categories
            int i = 1;
            for (Category c : cats) {
                System.out.println("Press " + i++ + " for " + c);
            }

            System.out.println("Please choose a number");
            String raw = scanner.nextLine().trim(); // Read full line to avoid Scanner desync

            try {
                int choice = Integer.parseInt(raw);

                // Validate range
                if (choice >= 1 && choice <= cats.length) {
                    Category selected = cats[choice -1];

                    System.out.println("User selected: " + selected + ". Correct? (y/n)");
                    
                    // Only return once user confirms
                    if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                        return selected;
                    }
                }
            // Repeat loop when anyother key is pressed including (n)
            } catch (Exception ignored) {}

            System.out.println("Invalid choice, try again.\n");
        }
    }

    /**
     * Reads a transaction amount from the user.
     * 
     * Behaviour:
     * - Prompts the user for a numeric amount.
     * - Reads input as a full line to avoid Scanner newline bugs.
     * - Attempts to parse the input as a double.
     * - Shows the parsed amount and asks the user to confirm it.
     * - Returns the amount only after the user confirms with 'y'.
     * - Any invalid input or a 'no' response cause the loop to repeat.
     * 
     * @return A valid, user-confirmed transaction amount.
     */
    public double readAmount() {
        while (true) {
            System.out.println("Amount of transaction:");
            String raw = scanner.nextLine().trim();

            try {
                double amount = Double.parseDouble(raw);
                System.out.println("You entered: £" + amount + ". Correct? (y/n)");
                if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                    return amount;
                }
            } catch (Exception ignored) {}

            System.out.println("Invalid amount, try again. \n");
        }
    }

    /**
     * Reads a date from the user in dd-MM-yyyy format.
     * 
     * Behaviour:
     * - Prompts the user for a date string.
     * - Reads input as a full line to avoid Scanner newline bugs
     * - Attempts to parse the input using a strict DateTimeFormatter.
     *   This validates both the format (dd-MM-YYYY) and the actual calendar date
     *   (e.g., rejects impossible dates like 31-02-2026).
     * - Displays the parsed date and asks the user to confirm it.
     * - Returns the LocalDate only after the user confirms with 'y'.
     * - Any invalid input, parsing failure, or a 'n' response cuases the loop to repeat.
     * 
     * @return A valid, user-confirmed LocalDate.
     */
    public LocalDate readDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while (true) {
            System.out.println("Enter data (dd-MM-yyyy):");
            String raw = scanner.nextLine().trim();

            try {
                LocalDate date = LocalDate.parse(raw, formatter);
                System.out.println("You entered: " + date.format(formatter) + ". Correct? (y/n)");
                if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                    return date;
                }
            } catch (Exception ignored) {}

            System.out.println("Invalid date, try again. \n");
        }
    }

    /**
     * Reads a time value in HH:mm format.
     * 
     * Behaviour:
     * - Allows the user to press Enter to default to "00:00".
     * - Uses a strict DateTimeFormatter to validate both format and real time values.
     *   (e.g., rejects 25:00, 99:99, or malformed input)
     * - Asks the user to confirm the parsed time.
     * - Repeats until a valid, confirmed time is entered.
     * 
     * @return A valid, user-confirmed time string in HH:mm format.
     */
    public String readTime() {
        System.out.println("DEBUG: Using InputHandler.readTime()");
        DateTimeFormatter strictFmt = new DateTimeFormatterBuilder()
                                        .appendPattern("HH:mm")
                                        .toFormatter()
                                        .withResolverStyle(ResolverStyle.STRICT);
        while (true) {
            System.out.println("Enter time (HH:mm) or press Enter for 00:00:");
            String raw = scanner.nextLine().trim();

            if (raw.isEmpty()) {
                return "00:00";
            }

            try {
                // Validate time using strict parsing
                LocalTime time = LocalTime.parse(raw, strictFmt);

                System.out.println("You entered: " + time + ". Correct? (y/n)");
                if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                    return time.toString(); // returns HH:mm
                }
            } catch (Exception e) {
                System.out.print("Invalid time format, try again. \n");
            }
        }
    }

    public int readIntRange(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt + " ");
            int value = readMenuChoice();
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println("Value muse be between " + min + " and " + max + ".");
        }
    }
}