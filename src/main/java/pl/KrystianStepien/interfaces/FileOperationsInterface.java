package pl.KrystianStepien.interfaces;

import pl.KrystianStepien.model.BankAccount;

public interface FileOperationsInterface {
    void saveAccount(BankAccount account);
    BankAccount loadAccount(String pesel);
}
