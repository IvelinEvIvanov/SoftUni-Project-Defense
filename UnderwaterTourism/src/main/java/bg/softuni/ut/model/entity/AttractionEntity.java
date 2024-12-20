package bg.softuni.ut.model.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attractions")
public class AttractionEntity extends BaseEntity {

	@Column(name = "title",unique = true,nullable = false)
	private String title;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Column(name = "price", precision = 12, scale = 2,nullable = false)
	private BigDecimal price;

	@Column(name = "duration",nullable = false)
	private Integer duration;

	@Column(name = "diving_season")
	private String divingSeason;

	@Column(name = "difficulty")
	private String difficulty;

	@Column(name = "image")
	private String image;

	@ManyToOne(optional = true)
	@JoinColumn(name = "department_id",referencedColumnName = "id")
	private DepartmentEntity department;

	public AttractionEntity() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getDivingSeason() {
		return divingSeason;
	}

	public void setDivingSeason(String divingSeason) {
		this.divingSeason = divingSeason;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}

}
