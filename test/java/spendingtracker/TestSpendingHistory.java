import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestSpendingHistory {
    public static void main(String[] args) {
        
        SpendingHistory history = new SpendingHistory();

        // Generate timestamp
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
       
        // Create a transaction
        Transaction t1 = new Transaction(70.23, Category.GROCERIES, timestamp);

        // Add it to the history
        history.addTransaction(t1);

        // Print all transactions
        history.printAll();
    }
}