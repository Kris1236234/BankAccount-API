package pl.KrystianStepien.service;

import org.springframework.stereotype.Service;
import pl.KrystianStepien.interfaces.BankAccountOperations;
import pl.KrystianStepien.interfaces.FileOperationsInterface;
import pl.KrystianStepien.model.BankAccount;
import pl.KrystianStepien.model.BankAccountDTO;
import pl.KrystianStepien.model.Transaction;

import java.math.BigDecimal;
import java.io.File;
import java.util.List;

@Service
public class BankAccountService implements BankAccountOperations {

    private final FileOperationsInterface fileOperations;

    public BankAccountService(FileOperationsInterface fileOperations) {
        this.fileOperations = fileOperations;
    }

    @Override
    public void createAccount(BankAccount dto) {
        if (fileOperations.loadAccount(dto.getPesel()) != null) {
            throw new RuntimeException("Konto już istnieje!");
        }

        BankAccount account = new BankAccount(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getIban(),
                dto.getPesel(),
                dto.getEmail()
        ) {
            @Override
            public String toJson() {
                return "";
            }
        };

        fileOperations.saveAccount(account);
    }

    @Override
    public void deposit(String pesel, BigDecimal amount) {
        BankAccount account = getAccount(pesel);
        account.setBalance(account.getBalance().add(amount));
        account.getTransactions().add(new Transaction("deposit", amount, "Wpłata"));
        fileOperations.saveAccount(account);
    }

    @Override
    public void withdraw(String pesel, BigDecimal amount) {
        BankAccount account = getAccount(pesel);
        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Brak środków na koncie");
        }
        account.setBalance(account.getBalance().subtract(amount));
        account.getTransactions().add(new Transaction("withdraw", amount, "Wypłata"));
        fileOperations.saveAccount(account);
    }

    @Override
    public BankAccount getAccount(String pesel) {
        BankAccount account = fileOperations.loadAccount(pesel);
        if (account == null) {
            throw new RuntimeException("Konto nie istnieje!");
        }
        return account;
    }

    @Override
    public void deleteAccount(String pesel) {
        File file = new File("data/" + pesel + ".json");
        if (!file.delete()) {
            throw new RuntimeException("Nie udało się usunąć konta");
        }
    }

    @Override
    public void transfer(String fromPesel, String toPesel, BigDecimal amount) {
        BankAccount fromAccount = getAccount(fromPesel);
        BankAccount toAccount = getAccount(toPesel);

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Brak środków na koncie źródłowym");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        fromAccount.getTransactions().add(new Transaction("transfer_out", amount, "Przelew do " + toPesel));
        toAccount.getTransactions().add(new Transaction("transfer_in", amount, "Przelew od " + fromPesel));

        fileOperations.saveAccount(fromAccount);
        fileOperations.saveAccount(toAccount);
    }

    @Override
    public List<Transaction> getTransactionHistory(String pesel) {
        return getAccount(pesel).getTransactions();
    }

    @Override
    public void updateAccount(String pesel, BankAccountDTO dto) {
        BankAccount account = getAccount(pesel);
        account.setFirstName(dto.getFirstName());
        account.setLastName(dto.getLastName());
        account.setIban(dto.getIban());
        account.setEmail(dto.getEmail());
        fileOperations.saveAccount(account);
    }
}
