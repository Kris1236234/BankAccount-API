package pl.KrystianStepien.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PeselValidator implements ConstraintValidator<ValidPesel, String> {

    @Override
    public boolean isValid(String pesel, ConstraintValidatorContext context) {
        if (pesel == null || pesel.length() != 11 || !pesel.matches("\\d+")) {
            return false;
        }

        int[] weights = {9, 7, 3, 1, 9, 7, 3, 1, 9, 7}; // oficjalny algorytm PESEL
        int sum = 0;

        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(pesel.charAt(i)) * weights[i];
        }

        int control = sum % 10;
        return control == Character.getNumericValue(pesel.charAt(10));
    }
}