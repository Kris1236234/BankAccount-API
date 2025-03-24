package pl.KrystianStepien.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StudentEmailValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidStudentEmail {
    String message() default "Nieprawidłowy adres e-mail studencki";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
