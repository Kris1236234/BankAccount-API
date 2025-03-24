package pl.KrystianStepien.interfaces;

import pl.KrystianStepien.model.BankAccountDTO;
import pl.KrystianStepien.model.BankAccount;
import pl.KrystianStepien.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface BankAccountOperations {
    void createAccount(BankAccount dto);
    void deposit(String pesel, BigDecimal amount);
    void withdraw(String pesel, BigDecimal amount);
    void deleteAccount(String pesel);
    BankAccount getAccount(String pesel);
    void transfer(String fromPesel, String toPesel, BigDecimal amount);

    List<Transaction> getTransactionHistory(String pesel);

    void updateAccount(String pesel, BankAccountDTO dto);
}
