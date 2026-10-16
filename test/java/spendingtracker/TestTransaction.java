public class TestTransaction {
    public static void main(String[] args) {
        
        String timestamp = "28-09-2026 14:00";
        Transaction t = new Transaction(63.48,Category.GROCERIES, timestamp);

        System.out.println(t.getAmount());
        System.out.println(t.getCategory());
        System.out.println(t.getTimestamp());
        System.out.println(t);
    }
}