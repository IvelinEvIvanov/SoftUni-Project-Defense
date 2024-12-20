package bg.softuni.ut.model.service;

import java.util.HashSet;
import java.util.Set;

import bg.softuni.ut.model.entity.UserRoleEntity;
import bg.softuni.ut.model.entity.enums.UserGenderEnum;

public class UserServiceModel {

	private Long id;
	private String firstName;
	private String lastName;
	private Integer age;
	private UserGenderEnum gender;
	private String email;
	private String password;
	private String phone;
	private Set<UserRoleEntity> roles = new HashSet<>();

	public UserServiceModel() {

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
