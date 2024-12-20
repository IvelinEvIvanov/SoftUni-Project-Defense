package bg.softuni.ut.model.service;

import java.math.BigDecimal;

public class EmployeeEditServiceModel {

	private Long id;

	private Long managerID;

	private String jobTitle;

	private String hireDate;

	private BigDecimal salary;

	EmployeeEditServiceModel() {
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
