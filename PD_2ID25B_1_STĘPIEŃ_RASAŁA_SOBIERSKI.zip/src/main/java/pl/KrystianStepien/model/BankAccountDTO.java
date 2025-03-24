package pl.KrystianStepien.model;

import pl.KrystianStepien.validation.ValidIban;
import pl.KrystianStepien.validation.ValidPesel;
import pl.KrystianStepien.validation.ValidStudentEmail;
import jakarta.validation.constraints.NotBlank;



public class BankAccountDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @ValidIban
    private String iban;

    @ValidPesel
    private String pesel;

    @ValidStudentEmail
    private String email;



    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getIban() { return iban; }
    public void setIban(String iban) { this.iban = iban; }

    public String getPesel() { return pesel; }
    public void setPesel(String pesel) { this.pesel = pesel; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }


    public BankAccountDTO() {
    }// do deserializacji JSON.


    public BankAccountDTO(BankAccount account) {
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        this.iban = account.getIban();
        this.pesel = account.getPesel();
        this.email = account.getEmail();
    }




}
