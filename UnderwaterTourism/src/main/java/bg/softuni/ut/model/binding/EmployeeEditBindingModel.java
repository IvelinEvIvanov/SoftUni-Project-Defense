package bg.softuni.ut.model.binding;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import bg.softuni.ut.model.validator.EmptyCheckbox;

public class EmployeeEditBindingModel {

	private Long id;

	private Long managerID;

	@NotBlank(message = "Field can't be blank.")
	@Size(min = 3, max = 45, message = "Fill with 3 to 45 symbols.")
	private String jobTitle;

	private String hireDate;

	@DecimalMin(value = "100", message = "Salary must be greater then 100.")
	private BigDecimal salary;

	public EmployeeEditBindingModel() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getManagerID() {
		return managerID;
	}

	public void setManagerID(Long managerID) {
		this.managerID = managerID;
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

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

}
