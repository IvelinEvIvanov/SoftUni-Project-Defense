package bg.softuni.ut.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReportDTO {

	private LocalDate fromDate;
	private LocalDate toDate;
	private BigDecimal income;

	public ReportDTO() {
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
