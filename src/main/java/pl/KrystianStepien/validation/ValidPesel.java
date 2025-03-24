package pl.KrystianStepien.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PeselValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPesel {
    String message() default "Nieprawidłowy numer PESEL";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
