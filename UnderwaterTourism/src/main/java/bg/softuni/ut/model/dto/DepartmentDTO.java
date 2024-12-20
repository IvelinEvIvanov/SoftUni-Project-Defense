package bg.softuni.ut.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DepartmentDTO {

	private Long departmentID;;

	@NotBlank
	@Size(min = 3, max = 50)
	private String name;

	public DepartmentDTO() {
	}

	public Long getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(Long departmentID) {
		this.departmentID = departmentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
