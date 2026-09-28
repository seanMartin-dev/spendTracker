import java.util.ArrayList;

public class SpendingHistory {

    private ArrayList<Transaction> transactions;

    public SpendingHistory() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void printAll() {
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}