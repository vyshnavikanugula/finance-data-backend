package backendassignment.example.myassignment.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

@Entity
@Table(name = "financial_records")
public class FinancialRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private double amount;

    @NotBlank(message = "Type is required")
    @Pattern(regexp = "^(INCOME|EXPENSE)$", message = "Type must be INCOME or EXPENSE")
    private String type;

    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "Date is required")
    private LocalDate date;

    private String description;

    // GETTERS
    public Long getId() { return id; }
    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getCategory() { return category; }
    public LocalDate getDate() { return date; }
    public String getDescription() { return description; }

    //  SETTERS
    public void setId(Long id) { this.id = id; }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}