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

# Sprint 3 - (data manipulation)

### Feature 1 : Sorting
The user can sort their transactions by amount or date. Sorting must work on data loaded from JSON and must not modiy the underlying stored list.

#### Acceptance Criteria
- Sorting works on loaded data
- Sort by amount (ascending)
- Sort by date (oldest -> newest)
- Sorting returns a new list (non-destructive)
- Sorting does not change the saved JSON order
- Sorting is accessible via CLI menu
- Sorting results are printed clearly
- Sorting logic is fully unit-tested

#### Test Requirements
- Sorting by amount produces correct order
- Sorting by date produces correct order
- Sorting works with:
    - empty list
    - single item
    - multiple items
    - JSON-loaded data

# Feature 2 : Monthly totals

### Description

The user can view total spending grouped by month. Monthly totals must work on data loaded from JSON

#### Acceptance Criteria
- Monthly totals work on loaded data
- Transactions are grouped by month (MM-YYYY)
- Amounts are summed per month
- Monthly totals are accessible via CLI menu
- Results are printed clearly
- Monthly totals logic is fully unit tested

#### Test Requirements
- Correct totals for:
    - empty list
    - single month
    - multiple months
    - repeated months
    - JSON-loaded data