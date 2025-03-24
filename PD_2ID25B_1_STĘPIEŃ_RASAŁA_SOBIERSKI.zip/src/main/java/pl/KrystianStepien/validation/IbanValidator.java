package pl.KrystianStepien.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IbanValidator implements ConstraintValidator<ValidIban, String> {

    @Override
    public boolean isValid(String iban, ConstraintValidatorContext context) {
        if (iban == null || !iban.startsWith("PL") || iban.length() != 28) {
            return false;
        }

        // IBAN walidacja
        String rearranged = iban.substring(4) + iban.substring(0, 4);
        StringBuilder numericIban = new StringBuilder();

        for (char c : rearranged.toCharArray()) {
            int value;
            if (Character.isLetter(c)) {
                value = Character.getNumericValue(c);
            } else {
                value = Character.getNumericValue(c);
            }
            numericIban.append(value);
        }

        try {
            return new java.math.BigInteger(numericIban.toString()).mod(java.math.BigInteger.valueOf(97)).intValue() == 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
