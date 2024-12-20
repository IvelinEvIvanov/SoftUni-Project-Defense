package bg.softuni.ut.model.binding;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import bg.softuni.ut.model.validator.EmptyCheckbox;

public class EmployeeRegisterBindingModel {

	@NotBlank(message = "Field can't be blank.")
	@Size(min = 3, max = 45, message = "Fill with 3 to 45 symbols.")
	private String jobTitle;

	private String hireDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent(message = "Date must be in the past.")
	private LocalDate birthDate;

	@EmptyCheckbox
	private String[] roles;

	@DecimalMin(value = "100", message = "Salary must be greater then 100.")
	private BigDecimal salary;

	private Long userId;

	public EmployeeRegisterBindingModel() {
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
