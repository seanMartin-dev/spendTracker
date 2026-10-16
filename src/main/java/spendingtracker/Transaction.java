package spendingtracker;

public class Transaction {

    private double amount;
    private Category category;
    private String timestamp;

    public Transaction(double amount, Category category, String timestamp) {
        this.amount = amount;
        this.category = category;
        this.timestamp = timestamp;
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

    @Override 
    public String toString() {
        return timestamp + " | " + category + " | £" + amount;
    }
}