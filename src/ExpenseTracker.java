import java.io.*;
import java.util.*;
import java.text.*;

public class ExpenseTracker {
    private List<Expense> expenses;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
    }

    // Add an expense
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    // Delete an expense by index
    public void deleteExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
            System.out.println("Expense deleted successfully.");
        } else {
            System.out.println("Invalid expense index.");
        }
    }

    // Search expenses by date range
    public void searchExpensesByDateRange(Date startDate, Date endDate) {
        double total = 0.0;
        System.out.println("Expenses from " + startDate + " to " + endDate + ":");
        for (Expense expense : expenses) {
            if (expense.getDate().after(startDate) && expense.getDate().before(endDate)) {
                System.out.println(expense);
                total += expense.getAmount();
            }
        }
        System.out.println("Total for the selected range: " + total);
    }

    // Sort expenses by amount
    public void sortExpensesByAmount() {
        expenses.sort(Comparator.comparingDouble(Expense::getAmount));
        System.out.println("Expenses sorted by amount:");
        expenses.forEach(System.out::println);
    }

    // Sort expenses by date
    public void sortExpensesByDate() {
        expenses.sort(Comparator.comparing(Expense::getDate));
        System.out.println("Expenses sorted by date:");
        expenses.forEach(System.out::println);
    }

    // Save expenses to file
    public void saveExpensesToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(expenses);
            System.out.println("Expenses saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    // Load expenses from file
    public void loadExpensesFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            expenses = (List<Expense>) ois.readObject();
            System.out.println("Expenses loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading expenses: " + e.getMessage());
        }
    }

    // Generate total expense report
    public void generateTotalReport() {
        double total = 0.0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        System.out.println("Total Spent: " + total);
    }

    // Generate category-wise expense report
    public void generateCategoryReport() {
        HashMap<String, Double> categoryReport = new HashMap<>();
        for (Expense expense : expenses) {
            categoryReport.put(expense.getCategory(),
                    categoryReport.getOrDefault(expense.getCategory(), 0.0) + expense.getAmount());
        }

        System.out.println("Category-wise Expense Report:");
        for (String category : categoryReport.keySet()) {
            System.out.println(category + ": " + categoryReport.get(category));
        }
    }

    // Generate weekly expense report
    public void generateWeeklyReport() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(new Date());  // Current date
        currentDate.add(Calendar.WEEK_OF_YEAR, -1);  // Get the date 7 days ago
        Date startOfWeek = currentDate.getTime();

        double weeklyTotal = 0.0;
        for (Expense expense : expenses) {
            if (expense.getDate().after(startOfWeek)) {
                weeklyTotal += expense.getAmount();
            }
        }

        System.out.println("Weekly Expense Report:");
        System.out.println("Total Spent in the Last Week: " + weeklyTotal);
    }

    // Generate monthly expense report
    public void generateMonthlyReport() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(new Date());  // Current date
        currentDate.add(Calendar.MONTH, -1);  // Get the date 30 days ago
        Date startOfMonth = currentDate.getTime();

        double monthlyTotal = 0.0;
        for (Expense expense : expenses) {
            if (expense.getDate().after(startOfMonth)) {
                monthlyTotal += expense.getAmount();
            }
        }

        System.out.println("Monthly Expense Report:");
        System.out.println("Total Spent in the Last Month: " + monthlyTotal);
    }

    // Filter expenses by category
    public void filterExpensesByCategory(String category) {
        double total = 0.0;
        System.out.println("Expenses for category: " + category);
        for (Expense expense : expenses) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                System.out.println(expense);
                total += expense.getAmount();
            }
        }
        System.out.println("Total for " + category + ": " + total);
    }
}
