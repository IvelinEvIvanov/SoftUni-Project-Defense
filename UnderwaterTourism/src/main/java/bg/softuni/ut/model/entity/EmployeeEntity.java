package bg.softuni.ut.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class EmployeeEntity extends BaseEntity {

	@Column(name = "job_title", nullable = false, length = 45)
	private String jobTitle;

	@Column(name = "hire_date", nullable = false)
	private LocalDate hireDate;

	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;

	@Column(name = "salary")
	private BigDecimal salary;

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@OneToOne(optional = true, orphanRemoval = false)
	@JoinColumn(name = "manager_id", referencedColumnName = "id")
	private EmployeeEntity manager;

	public EmployeeEntity() {
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
