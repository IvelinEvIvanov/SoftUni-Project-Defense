package bg.softuni.ut.model.view;

import java.math.BigDecimal;
import java.time.LocalDate;

import bg.softuni.ut.model.entity.enums.PartOfDayEnum;

public class UserReservationView {

	private Long id;
	private String image;
	private String title;
	private LocalDate reservationDate;
	private long remainingDays;
	private Integer peopleNumber;
	private Integer hoursNumber;
	private PartOfDayEnum partOfDay;
	private String userName;
	private BigDecimal price;
	private BigDecimal totalSumPerAttraction;
	private BigDecimal totalSumAllAttractions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getTotalSumPerAttraction() {
		return totalSumPerAttraction;
	}

	public void setTotalSumPerAttraction(BigDecimal totalSumPerAttraction) {
		this.totalSumPerAttraction = totalSumPerAttraction;
	}

	public BigDecimal getTotalSumAllAttractions() {
		return totalSumAllAttractions;
	}

	public void setTotalSumAllAttractions(BigDecimal totalSumAllAttractions) {
		this.totalSumAllAttractions = totalSumAllAttractions;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public PartOfDayEnum getPartOfDay() {
		return partOfDay;
	}

	public void setPartOfDay(PartOfDayEnum partOfDay) {
		this.partOfDay = partOfDay;
	}

	public Integer getHoursNumber() {
		return hoursNumber;
	}

	public void setHoursNumber(Integer hoursNumber) {
		this.hoursNumber = hoursNumber;
	}

	public UserReservationView() {
	}

	public Integer getPeopleNumber() {
		return peopleNumber;
	}

	public void setPeopleNumber(Integer peopleNumber) {
		this.peopleNumber = peopleNumber;
	}

	public long getRemainingDays() {
		return remainingDays;
	}

	public void setRemainingDays(long remainingDays) {
		this.remainingDays = remainingDays;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}

}
