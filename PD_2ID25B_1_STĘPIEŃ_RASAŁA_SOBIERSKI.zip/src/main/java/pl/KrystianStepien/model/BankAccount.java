package pl.KrystianStepien.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pl.KrystianStepien.interfaces.JsonSerializable;
import pl.KrystianStepien.validation.ValidStudentEmail;
import pl.KrystianStepien.validation.ValidIban;
import pl.KrystianStepien.validation.ValidPesel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public abstract class BankAccount implements JsonSerializable {

    private String firstName;
    private String lastName;
    @ValidIban
    private String iban;
    private BigDecimal balance;
    private List<Transaction> transactions;

    @ValidPesel
    private String pesel;
    @ValidStudentEmail
    private String email;




    public BankAccount() {
        this.balance = BigDecimal.ZERO;
        this.transactions = new ArrayList<>();
    }

    public BankAccount(String firstName, String lastName, String iban, String pesel, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.iban = iban;
        this.pesel = pesel;
        this.email = email;
        this.balance = BigDecimal.ZERO;
        this.transactions = new ArrayList<>();
    }



    public void deposit(BigDecimal amount, String description) {
        this.balance = this.balance.add(amount);
        transactions.add(new Transaction("deposit", amount, description));
    }

    public boolean withdraw(BigDecimal amount, String description) {
        if (this.balance.compareTo(amount) >= 0) {
            this.balance = this.balance.subtract(amount);
            transactions.add(new Transaction("withdraw", amount, description));
            return true;
        }
        return false;
    }

    public void transferTo(BankAccount recipient, BigDecimal amount) {
        if (this.withdraw(amount, "Przelew do " + recipient.getPesel())) {
            recipient.deposit(amount, "Przelew od " + this.getPesel());
        }
    }

    public List<Transaction> getTransactions() {
        if (transactions == null) {
            transactions = new ArrayList<>();
        }
        return transactions;
    }


    @Override
    public String toString() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            throw new RuntimeException("Błąd serializacji do JSON", e);
        }
    }


    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIban() {
        return iban;
    }

    public String getEmail() {
        return email;
    }



}
