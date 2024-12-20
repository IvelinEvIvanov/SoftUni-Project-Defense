package bg.softuni.ut.model.dto;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import bg.softuni.ut.model.entity.AttractionEntity;
import bg.softuni.ut.model.entity.UserEntity;
import bg.softuni.ut.model.entity.enums.PartOfDayEnum;

public class ReservationDTO {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Future(message = "Reservation date must be in the future.")
	private LocalDate reservationDate;

	@Min(value = 1l, message = "The number of people mustbe at least 1.")
	private Integer peopleNumber;

	@Min(value = 1l)
	private Integer hoursNumber;

	@NonNull
	private PartOfDayEnum partOfDay;

	public Integer getHoursNumber() {
		return hoursNumber;
	}

	public void setHoursNumber(Integer hoursNumber) {
		this.hoursNumber = hoursNumber;
	}

	private String email;

	private UserEntity user;

	private Set<AttractionEntity> attractions;

	public ReservationDTO() {
	}

	public LocalDate getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Integer getPeopleNumber() {
		return peopleNumber;
	}

	public void setPeopleNumber(Integer peopleNumber) {
		this.peopleNumber = peopleNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Set<AttractionEntity> getAttractions() {
		return attractions;
	}

	public void setAttractions(Set<AttractionEntity> attractions) {
		this.attractions = attractions;
	}

	public PartOfDayEnum getPartOfDay() {
		return partOfDay;
	}

	public void setPartOfDay(PartOfDayEnum partOfDay) {
		this.partOfDay = partOfDay;
	}

}
