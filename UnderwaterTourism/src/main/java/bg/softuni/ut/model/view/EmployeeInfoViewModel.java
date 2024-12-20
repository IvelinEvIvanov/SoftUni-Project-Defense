package bg.softuni.ut.model.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import bg.softuni.ut.model.entity.EmployeeEntity;
import bg.softuni.ut.model.entity.UserEntity;
import bg.softuni.ut.model.entity.UserRoleEntity;

public class EmployeeInfoViewModel {

	private Long id;
	private String jobTitle;
	private LocalDate hireDate;
	private LocalDate birthDate;
	private BigDecimal salary;

	UserEntity user;
	EmployeeEntity manager;

	public EmployeeInfoViewModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public EmployeeEntity getManager() {
		return manager;
	}

	public void setManager(EmployeeEntity manager) {
		this.manager = manager;
	}

}
