# Spending Tracker

A simple Java CLI application to track spending, view history, filter by category, and calculate totals.  
Supports UK-style dates and multiple sorting options.

## Features

### Core
- Add transactions (amount, category, date, time)
- View all transactions
- Filter by category
- View total spend

### Sorting
- Sort transactions by **amount**
- Sort transactions by **date** (UK format: dd-MM-yyyy HH:mm)
- Accurate chronological sorting using `LocalDateTime` parsing

### Storage
- JSON persistence using `JsonStorage`
- Load/save transactions automatically

## Tech Stack
- Java 17+
- Maven
- JUnit 5
- Gson (JSON storage)

## Future Improvements
- Delete/edit transactions
- Monthly summaries
- Category totals
- CSV export
- GUI
