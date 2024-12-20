package bg.softuni.ut.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import bg.softuni.ut.model.entity.enums.UserRoleEnum;

@Table
@Entity(name = "roles")
public class UserRoleEntity extends BaseEntity {

	@Column(name = "role", nullable = false, length = 50)
	@Enumerated(value = EnumType.STRING)
	private UserRoleEnum role;

	@ManyToMany(mappedBy = "roles")
	private Set<UserEntity> users = new HashSet<>();

	public UserRoleEntity() {
	}

	public UserRoleEnum getRole() {
		return role;
	}

	public void setRole(UserRoleEnum role) {
		this.role = role;
	}

	public Set<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}

}
