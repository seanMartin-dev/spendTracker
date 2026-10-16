package spendingtracker;

import java.util.ArrayList;
import java.util.List;

public class SpendingHistory {

    private ArrayList<Transaction> transactions;

    public SpendingHistory() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void printAll() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    public List<Transaction> filterByCategory(Category category) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getCategory() == category) {
                result.add(t);
            }
        }
        return result;
    }

   public double getTotalSpend() {
        double total = 0;
        for (Transaction t: transactions) {
            total += t.getAmount();
        }
        
        return total;
   }
}