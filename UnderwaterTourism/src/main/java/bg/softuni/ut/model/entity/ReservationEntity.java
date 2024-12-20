package bg.softuni.ut.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import bg.softuni.ut.model.entity.enums.PartOfDayEnum;

@Entity
@Table(name = "reservations")
public class ReservationEntity extends BaseEntity {

	@Column(nullable = false)
	private LocalDate reservationDate;

	@Column(nullable = false)
	private Integer peopleNumber;

	@Column(nullable = false)
	private Integer hoursNumber;

	@Enumerated(EnumType.STRING)
	private PartOfDayEnum partOfDay;

	@Column(nullable = false)
	private BigDecimal bill;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "reservations_attractions", joinColumns = @JoinColumn(name = "reservation_id"), inverseJoinColumns = @JoinColumn(name = "attraction_id"))
	private Set<AttractionEntity> attractions;

	public ReservationEntity() {
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

	public Integer getHoursNumber() {
		return hoursNumber;
	}

	public void setHoursNumber(Integer hoursNumber) {
		this.hoursNumber = hoursNumber;
	}

	public PartOfDayEnum getPartOfDay() {
		return partOfDay;
	}

	public void setPartOfDay(PartOfDayEnum partOfDay) {
		this.partOfDay = partOfDay;
	}

	public BigDecimal getBill() {
		return bill;
	}

	public void setBill(BigDecimal bill) {
		this.bill = bill;
	}

}
