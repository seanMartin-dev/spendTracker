package spendingtracker;

public enum Category {
    GROCERIES,
    FUEL,
    INTERNET,
    ENERGY,
    TAXI,
    HOLIDAY,
    GARDENERS,
    SUBSCRIPTIONS,
    POCKET_MONEY,
    TAKEAWAY,
    CAR_INSURANCE,
    PRIVATE_HEALHTCARE;

    // Change Constants in enum to string POCKET_MONEY = Pocket money
    @Override
    public String toString() {
        String lower = name().toLowerCase().replace("_", " ");
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }
}
