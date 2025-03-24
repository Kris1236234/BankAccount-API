package pl.KrystianStepien.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;
import pl.KrystianStepien.interfaces.FileOperationsInterface;
import pl.KrystianStepien.model.BankAccount;

import java.io.File;
import java.io.IOException;

@Component
public class FileOperations implements FileOperationsInterface {

    private static final String DATA_DIR = "data/";
    private final ObjectMapper mapper;

    public FileOperations() {
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        new File(DATA_DIR).mkdirs();
    }

    @Override
    public void saveAccount(BankAccount account) {
        try {
            mapper.writeValue(new File(DATA_DIR + account.getPesel() + ".json"), account);
        } catch (IOException e) {
            throw new RuntimeException("Błąd zapisu do JSON", e);
        }
    }

    @Override
    public BankAccount loadAccount(String pesel) {
        File file = new File(DATA_DIR + pesel + ".json");
        if (!file.exists()) return null;

        try {
            return mapper.readValue(file, BankAccount.class);
        } catch (IOException e) {
            throw new RuntimeException("Błąd odczytu JSON", e);
        }
    }
}
