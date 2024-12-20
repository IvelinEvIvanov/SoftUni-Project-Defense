package bg.softuni.ut.model.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import bg.softuni.ut.model.entity.enums.UserGenderEnum;
import bg.softuni.ut.model.validator.UniqueEmail;

public class UserEditBindingModel {

	private Long id;

	@NotBlank(message = "Field can't be blank.")
	@Size(min = 2, max = 20, message = "Fill with 2 to 20 symbols.")
	private String firstName;

	@NotBlank(message = "Field can't be blank.")
	@Size(min = 2, max = 20, message = "Fill with 2 to 20 symbols.")
	private String lastName;

	@NotNull
	@Min(value = 5L, message = "Age must be at least 5 years old.")
	private Integer age;

	@NotNull(message = "Gender must be checked.")
	private UserGenderEnum gender;

	private String email;

	@NotBlank(message = "Field can't be blank.")
	@Size(min = 3, message = "Field must be at least 4 symbols.")
	private String password;

	@NotBlank(message = "Field can't be blank.")
	@Size(min = 3, message = "Field must be at least 4 symbols.")
	private String confirmPassword;

	@Size(min = 0, max = 10, message = "Field can't be more than 10 symbols.")
	private String phone;

	private boolean isWrongInput = false;

	public UserEditBindingModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public UserGenderEnum getGender() {
		return gender;
	}

	public void setGender(UserGenderEnum gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isWrongInput() {
		return isWrongInput;
	}

	public void setWrongInput(boolean isWrongInput) {
		this.isWrongInput = isWrongInput;
	}

}
