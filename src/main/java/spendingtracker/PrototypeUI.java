package spendingtracker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PrototypeUI {
    public static void main(String[] args) {
        
        Scanner obj = new Scanner(System.in);
        Category[] cats = Category.values();

        // set selected to null
        Category selected = null;

        // while loop to validate user choice
        while (true) {
            int i = 1;
            for (Category c : Category.values()) {
                System.err.println("Press " + i++ +" for " + c);
            }
        
            System.out.println("Please chose a number for a category: ");
            int userChoice = obj.nextInt();
            
            if (userChoice >= 1 && userChoice <= cats.length) {
                selected = cats[userChoice - 1];
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

        Float amount = null;

        // while loop to validate transaction amount
        while (true) {
            
            System.out.println("Amount of transaction:");
            Float temp = obj.nextFloat();

            System.out.println("You entered: £" + temp + "\nCorrect? (y/n)");
            String confirm = obj.next();

            if(confirm.equalsIgnoreCase("y")) {
                amount = temp;
                break; // exit loop, amount confirmed
            }
        
        System.out.println("Okay, let's try again.");
        }
       
        String time;
         
        obj.nextLine();
        LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while (true) {
            System.out.println("Enter date (dd-MM-yyyy):");
            String input = obj.nextLine().trim();   // user enters raw text

            try {
                LocalDate temp = LocalDate.parse(input, formatter);

                System.out.println("You entered: " + temp.format(formatter) + ". Correct? (y/n)");
                String confirm = obj.nextLine().trim();

                if (confirm.equalsIgnoreCase("y")) {
                date = temp;  // save validated date
                break;
            }

            System.out.println("Okay, try again.\n");

            } catch (Exception e) {
                System.out.println("Invalid date format. Please use dd-MM-yyyy.\n");
            }
           
        }

        // while loop to validate time of transaction
        while (true) {
            System.out.println("Enter time (HH:mm) or press Enter if unknown:");
            time = obj.nextLine().trim();

            if (time.trim().isEmpty()) {
                time = "00:00";
                break;
            }

        System.out.println("You entered: " + time + ". Correct? (y/n)");
        if (obj.nextLine().trim().equalsIgnoreCase("y")) break;

        System.out.println("Okay, try again.");
        }

        String timestamp = date.format(formatter) + " " + time;
        System.out.println("Transaction: " + selected + "\nDate: " + timestamp);
    }
}
