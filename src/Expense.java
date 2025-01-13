import java.io.Serializable;
import java.util.Date;

public class Expense implements Serializable {
    private double amount;
    private String category;
    private Date date;

    public Expense(double amount, String category, Date date) {
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    // Getters
    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Expense [amount=" + amount + ", category=" + category + ", date=" + date + "]";
    }
}
