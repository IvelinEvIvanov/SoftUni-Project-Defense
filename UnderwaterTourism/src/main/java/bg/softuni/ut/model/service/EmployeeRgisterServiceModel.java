package bg.softuni.ut.model.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import bg.softuni.ut.model.entity.EmployeeEntity;
import bg.softuni.ut.model.entity.UserEntity;

public class EmployeeRgisterServiceModel {

	private String jobTitle;
	private LocalDate hireDate;
	private LocalDate birthDate;
	private BigDecimal salary;
	private String[] roles;
	private Long userId;

	public EmployeeRgisterServiceModel() {
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
