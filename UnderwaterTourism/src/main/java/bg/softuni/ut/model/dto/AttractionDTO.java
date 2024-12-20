package bg.softuni.ut.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class AttractionDTO {

	private Long id;

	@NotBlank(message = "Title can't be blank.")
	private String title;

	private String description;

	@DecimalMin(value = "10", inclusive = true, message = "Price must be greater then 9.")
	@Digits(integer = 8, fraction = 2)
	private BigDecimal price;

	@Min(value = 1l, message = "Duration must be greater than 0.")
	private Integer duration;

	private String divingSeason;

	private String difficulty;

	private Long departmentID;

	private String image;

	public AttractionDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(Long departmentID) {
		this.departmentID = departmentID;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
