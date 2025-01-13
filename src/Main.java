import java.util.*;
import java.text.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseTracker tracker = new ExpenseTracker();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  // Define date format
        
        while (true) {
            System.out.println("1. Add Expense");
            System.out.println("2. Generate Category-wise Report");
            System.out.println("3. Generate Total Report");
            System.out.println("4. Generate Weekly Report");
            System.out.println("5. Generate Monthly Report");
            System.out.println("6. Delete Expense");
            System.out.println("7. Search Expenses by Date Range");
            System.out.println("8. Sort Expenses");
            System.out.println("9. Save and Load Data");
            System.out.println("10. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character
            
            switch (choice) {
                case 1:
                    // Add Expense
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline

                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    
                    System.out.print("Enter date (yyyy-mm-dd): ");
                    String dateString = scanner.nextLine();
                    Date date = null;
                    try {
                        date = dateFormat.parse(dateString);  // Parse date using SimpleDateFormat
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
                        break;
                    }
                    
                    Expense expense = new Expense(amount, category, date);
                    tracker.addExpense(expense);
                    System.out.println("Expense added successfully!\n");
                    break;

                case 2:
                    // Generate Category-wise Report
                    tracker.generateCategoryReport();
                    break;

                case 3:
                    // Generate Total Report
                    tracker.generateTotalReport();
                    break;

                case 4:
                    // Generate Weekly Report
                    tracker.generateWeeklyReport();
                    break;

                case 5:
                    // Generate Monthly Report
                    tracker.generateMonthlyReport();
                    break;

                case 6:
                    // Delete Expense
                    System.out.print("Enter the index of the expense to delete: ");
                    int expenseIndex = scanner.nextInt();
                    tracker.deleteExpense(expenseIndex);
                    break;

                case 7:
                    // Search Expenses by Date Range
                    System.out.print("Enter start date (yyyy-mm-dd): ");
                    String startDateString = scanner.nextLine();
                    System.out.print("Enter end date (yyyy-mm-dd): ");
                    String endDateString = scanner.nextLine();
                    try {
                        Date startDate = dateFormat.parse(startDateString);
                        Date endDate = dateFormat.parse(endDateString);
                        tracker.searchExpensesByDateRange(startDate, endDate);
                    } catch (Exception e) {
                        System.out.println("Invalid date format.");
                    }
                    break;

                case 8:
                    // Sort Expenses
                    System.out.println("1. Sort by Amount");
                    System.out.println("2. Sort by Date");
                    System.out.print("Choose an option: ");
                    int sortChoice = scanner.nextInt();
                    if (sortChoice == 1) {
                        tracker.sortExpensesByAmount();
                    } else if (sortChoice == 2) {
                        tracker.sortExpensesByDate();
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;

                case 9:
                    // Save and Load Data
                    System.out.println("1. Save expenses to file");
                    System.out.println("2. Load expenses from file");
                    System.out.print("Choose an option: ");
                    int fileChoice = scanner.nextInt();
                    if (fileChoice == 1) {
                        tracker.saveExpensesToFile("expenses.dat");
                    } else if (fileChoice == 2) {
                        tracker.loadExpensesFromFile("expenses.dat");
                    }
                    break;

                case 10:
                    // Exit
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
