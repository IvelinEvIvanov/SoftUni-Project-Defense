package bg.softuni.ut.model.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import bg.softuni.ut.model.entity.enums.UserGenderEnum;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

	@Column(name = "first_name", nullable = false, length = 20)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 20)
	private String lastName;

	@Column(name = "age", nullable = false)
	private Integer age;

	@Column(name = "gender", nullable = false, length = 6)
	@Enumerated(value = EnumType.STRING)
	private UserGenderEnum gender;

	@Column(name = "email", nullable = false, unique = true, length = 50)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "phone", length = 10)
	private String phone;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<UserRoleEntity> roles = new HashSet<>();

	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true)
	private Set<EmployeeEntity> employees;

	public UserEntity() {
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<UserRoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRoleEntity> roles) {
		this.roles = roles;
	}

	public Set<EmployeeEntity> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeeEntity> employees) {
		this.employees = employees;
	}

}
