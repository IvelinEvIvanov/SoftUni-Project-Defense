package bg.softuni.ut.service.attraction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import bg.softuni.ut.model.dto.ReportDTO;
import bg.softuni.ut.model.dto.ReservationDTO;
import bg.softuni.ut.model.view.UserReservationView;

public interface ReservationService {

	void makeReservation(ReservationDTO reservationDTO, String attractionTitle);

	List<UserReservationView> getUserReservations();

	BigDecimal getAllAttractionsTotalSum(List<UserReservationView> reservations);

	void deleteReservation(Long id);

	BigDecimal getMonthlyIncome(LocalDate today, LocalDate previousMonth);


}
