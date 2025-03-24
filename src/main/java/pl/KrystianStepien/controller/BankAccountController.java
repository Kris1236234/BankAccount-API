package pl.KrystianStepien.controller;

import pl.KrystianStepien.model.BankAccountDTO;
import jakarta.validation.Valid;
import pl.KrystianStepien.model.BankAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.KrystianStepien.model.Transaction;
import pl.KrystianStepien.model.TransferRequest;
import pl.KrystianStepien.service.BankAccountService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {
    private final BankAccountService service;

    public BankAccountController(BankAccountService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> createAccount(@Valid @RequestBody BankAccountDTO dto) {
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
        service.createAccount(account);
        return ResponseEntity.ok("Konto utworzone");
    }

    @PostMapping("/{pesel}/deposit")
    public ResponseEntity<String> deposit(@PathVariable String pesel, @RequestParam BigDecimal amount) {
        service.deposit(pesel, amount); // ✅ poprawna kolejność
        return ResponseEntity.ok("Wpłata zakończona sukcesem");
    }

    @PostMapping("/{pesel}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable String pesel, @RequestParam BigDecimal amount) {
        service.withdraw(pesel, amount); // ✅ poprawna kolejność
        return ResponseEntity.ok("Wypłata zakończona sukcesem");
    }


    @GetMapping("/{pesel}")
    public ResponseEntity<BankAccountDTO> getAccount(@PathVariable String pesel) {
        BankAccount account = service.getAccount(pesel);
        return ResponseEntity.ok(new BankAccountDTO(account));
    }

    @DeleteMapping("/{pesel}")
    public ResponseEntity<String> deleteAccount(@PathVariable String pesel) {
        service.deleteAccount(pesel);
        return ResponseEntity.ok("Konto usunięte");
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequest request) {
        service.transfer(request.getFromPesel(), request.getToPesel(), request.getAmount());
        return ResponseEntity.ok("Przelew zakończony sukcesem");
    }


    @GetMapping("/{pesel}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable String pesel) {
        return ResponseEntity.ok(service.getTransactionHistory(pesel));
    }

    @PutMapping("/{pesel}")
    public ResponseEntity<String> updateAccount(@PathVariable String pesel,
                                                @Valid @RequestBody BankAccountDTO update) {
        service.updateAccount(pesel, update);
        return ResponseEntity.ok("Konto zaktualizowane");
    }




}
