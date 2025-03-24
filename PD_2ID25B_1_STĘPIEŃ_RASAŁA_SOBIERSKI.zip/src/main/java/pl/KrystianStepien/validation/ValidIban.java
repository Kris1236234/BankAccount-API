package pl.KrystianStepien.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IbanValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidIban {
    String message() default "Nieprawidłowy numer IBAN";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
