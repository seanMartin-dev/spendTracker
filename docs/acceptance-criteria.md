# Acceptance Criteria

## Add Transaction
- User enters amount
- User enters category
- Timestamp auto-generated
- Transaction appears in history

## View All
- All transactions print
- Format readable

## Filter
- Case-insensitive
- Only matching categories shown

## Total
- Sum correct
- Printed in £ format

# Sprint 2 - Acceptance Criteria (JSON persistance)

- The app loads transactions from a JSON file at startup
- If the JSON file does not exist, the app starts with an empty history
- If the JSON file is corrupted, the app shows a friendly error and continues with an empty history
- The app saves all transactions to JSON
- The JSON file contains all fields: amount, category, timestamp
- Saving and loading preserve the exact same data
- Sorting works on loaded data (Sprint 3)
- Monthly totals work on loaded data (SPrint 3)
- All persistence logic is tested
- No UI logic is mixed inot persistence logic