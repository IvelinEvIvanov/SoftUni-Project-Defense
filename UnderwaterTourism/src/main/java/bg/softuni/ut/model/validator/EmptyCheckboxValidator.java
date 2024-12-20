package bg.softuni.ut.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmptyCheckboxValidator implements ConstraintValidator<EmptyCheckbox, String[]> {

	@Override
	public boolean isValid(String[] checkboxes, ConstraintValidatorContext context) {
		return checkboxes.length > 0;
	}

}
