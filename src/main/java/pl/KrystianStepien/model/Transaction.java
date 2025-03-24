package pl.KrystianStepien.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private String type;
    private BigDecimal amount;
    private LocalDateTime date;
    private String description;

    public Transaction() {
    }

    public Transaction(String type, BigDecimal amount, String description) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = LocalDateTime.now();
    }


    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
