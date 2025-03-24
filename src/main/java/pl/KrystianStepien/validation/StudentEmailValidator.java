package pl.KrystianStepien.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StudentEmailValidator implements ConstraintValidator<ValidStudentEmail, String> {

    private static final String STUDENT_EMAIL_REGEX = "^s\\d{5}@student\\.tu\\.kielce\\.pl$";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) return false;
        return email.matches(STUDENT_EMAIL_REGEX);
    }
}
