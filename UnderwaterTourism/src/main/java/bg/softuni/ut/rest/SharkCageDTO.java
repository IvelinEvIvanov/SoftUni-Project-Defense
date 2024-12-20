package bg.softuni.ut.rest;

import java.math.BigDecimal;

import javax.persistence.Column;

public class SharkCageDTO {

	private Long id;
	private String title;
	private String description;
	private BigDecimal price;
	private Integer duration;
	private String divingSeason;
	private String difficulty;

	public SharkCageDTO() {
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

}
