# Backlog

- Add transaction
- View all transactions
- Filter by category
- View total spend
- Exit cleanly
- Sorting (optional)
- Save/load to file (optional)
- Monthly totals (optional)
- Monthly Totals per category

## Sprint 4 Backlog
1. Monthly Totals
    - Add method: getMonthlyTotal(int month, int year)
    - CLI option: View total spend for a month

2. Monthly Totals Per Category
    - Add mehtod:
    getMonthlyTotalBy Category(int month, int year, Category category)
    -CLI option: View monthly totals per category

3. Reports Submenu
    - Submenu will display
        - Monthly totals
        - Monthly total per category
        - Category stats
        - Sorting options

4. Introduce a Service Layer
    - Create a SpendingService class to centralise:
        - Sortinng
        - Filtering
        - Monthly totals
        - Category totals

5. Add Unit test for new features
    - Test monthly totals
    - Test monthly totals per category
    - Test service layer behaviour

## Acceptance criteria
 - User can view monthly totals
 - User can view monthly totals per category
 - Reprots submenu implemented
 - Sorting + stats moved into Reports
 - Service layer introduced
 - All new features tested
 - CLI remains cleans and stable
