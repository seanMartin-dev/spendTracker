package spendingtracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Transaction {

    private double amount;
    private Category category;
    private String timestamp;
    private transient LocalDateTime dateTime;

    public Transaction() {}

    public Transaction(double amount, Category category, String timestamp) {
        this.amount = amount;
        this.category = category;
        this.timestamp = timestamp;
        this.dateTime = LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    public double getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    
    public void rebuildDateTime() {
        this.dateTime = LocalDateTime.parse(
            this.timestamp,
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
        );
    }

    @Override 
    public String toString() {
        return timestamp + " | " + category + " | £" + amount;
    }
}