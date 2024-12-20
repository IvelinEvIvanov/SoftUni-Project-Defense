package bg.softuni.ut.model.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmptyCheckboxValidator.class)
public @interface EmptyCheckbox {
	String message() default "At least one checkbox must be selected.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
