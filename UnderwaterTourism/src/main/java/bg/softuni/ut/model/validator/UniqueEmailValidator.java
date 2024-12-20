package bg.softuni.ut.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import bg.softuni.ut.service.UserService;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	private final UserService userService;

	public UniqueEmailValidator(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if (email == null) {
			return true;
		}

		return this.userService.isEmailUnique(email);
	}

}
