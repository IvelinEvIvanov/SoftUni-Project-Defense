package bg.softuni.ut.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class DepartmentEntity extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String name;

	@Column(columnDefinition = "TEXT")
	private String description;

	public DepartmentEntity() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
