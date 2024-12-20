package bg.softuni.ut.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "reports")
public class ReportEntity extends BaseEntity {

	@Column(nullable = false)
	private LocalDate fromDate;

	@Column(nullable = false)
	private LocalDate toDate;

	@Column(nullable = false)
	private BigDecimal income;

	public ReportEntity() {
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

}
