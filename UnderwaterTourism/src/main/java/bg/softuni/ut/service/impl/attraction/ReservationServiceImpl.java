package bg.softuni.ut.service.impl.attraction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import bg.softuni.ut.model.dto.ReservationDTO;
import bg.softuni.ut.model.entity.AttractionEntity;
import bg.softuni.ut.model.entity.ReservationEntity;
import bg.softuni.ut.model.entity.UserEntity;
import bg.softuni.ut.model.service.UserDetailsImpl;
import bg.softuni.ut.model.view.UserReservationView;
import bg.softuni.ut.repository.attraction.AttractionRepository;
import bg.softuni.ut.repository.attraction.ReservationRepository;
import bg.softuni.ut.service.UserService;
import bg.softuni.ut.service.attraction.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	private final ReservationRepository reservationRepository;
	private final AttractionRepository attractionRepository;

	private final UserService userService;

	private final UserDetailsImpl userDetailsImpl;

	private final ModelMapper modelMapper;

	public ReservationServiceImpl(ReservationRepository reservationRepository, ModelMapper modelMapper,
			UserService userService, UserDetailsImpl userDetailsImpl, AttractionRepository attractionRepository) {

		this.reservationRepository = reservationRepository;
		this.attractionRepository = attractionRepository;
		this.userService = userService;
		this.userDetailsImpl = userDetailsImpl;
		this.modelMapper = modelMapper;
	}

	@Override
	public void makeReservation(ReservationDTO reservationDTO, String attractionTitle) {

		ReservationEntity newReservation = this.modelMapper.map(reservationDTO, ReservationEntity.class);

		UserEntity loggedUser = this.userService.findByEmail(this.userDetailsImpl.getUsername());
		newReservation.setUser(loggedUser);

		AttractionEntity attractionEntity = this.attractionRepository.findByTitle(attractionTitle);
		newReservation.setAttractions(Set.of(attractionEntity));

		BigDecimal bill = this.calculateTotalSumForSingleAttraction(attractionEntity.getPrice(),
				reservationDTO.getPeopleNumber(), reservationDTO.getHoursNumber());

		newReservation.setBill(bill);

		this.reservationRepository.save(newReservation);

	}

	@Override
	public List<UserReservationView> getUserReservations() {

		List<ReservationEntity> reservations = this.reservationRepository.findAllUserRervations().orElse(null);

		if (reservations != null && !reservations.isEmpty()) {

			List<UserReservationView> listUserReservationViews = new ArrayList<>();

			for (ReservationEntity itrReservationEntity : reservations) {

				if (itrReservationEntity.getUser().getId() == this.userDetailsImpl.getUserId()) {

					UserReservationView userReservationView = new UserReservationView();

					userReservationView.setId(itrReservationEntity.getId());

					LocalDate currentDate = LocalDate.now();
					userReservationView.setRemainingDays(
							ChronoUnit.DAYS.between(currentDate, itrReservationEntity.getReservationDate()));

					userReservationView.setReservationDate(itrReservationEntity.getReservationDate());
					userReservationView.setPeopleNumber(itrReservationEntity.getPeopleNumber());
					userReservationView.setHoursNumber(itrReservationEntity.getHoursNumber());
					userReservationView.setPartOfDay(itrReservationEntity.getPartOfDay());

					String userName = this.userDetailsImpl.getFullName();
					userReservationView.setUserName(userName);

					for (AttractionEntity itrAttractionEntity : itrReservationEntity.getAttractions()) {
						userReservationView.setImage(itrAttractionEntity.getImage());
						userReservationView.setTitle(itrAttractionEntity.getTitle());
						userReservationView.setPrice(itrAttractionEntity.getPrice());
					}

					BigDecimal totalSumPerAttraction = this.calculateTotalSumForSingleAttraction(
							userReservationView.getPrice(), userReservationView.getPeopleNumber(),
							userReservationView.getHoursNumber());

					userReservationView.setTotalSumPerAttraction(totalSumPerAttraction);

					listUserReservationViews.add(userReservationView);

				} else {
					return null;
				}
			}

			return listUserReservationViews;
		}

		return null;
	}

	private BigDecimal calculateTotalSumForSingleAttraction(BigDecimal price, Integer peopleNumber, Integer hours) {

		BigDecimal visitors = BigDecimal.valueOf(peopleNumber);
		BigDecimal attractionHours = BigDecimal.valueOf(hours);

		return new BigDecimal(1).multiply(price).multiply(visitors).multiply(attractionHours);
	}

	@Override
	public BigDecimal getAllAttractionsTotalSum(List<UserReservationView> reservations) {

		BigDecimal totalSum = new BigDecimal(0);

		for (UserReservationView itrUserReservationView : reservations) {
			totalSum = totalSum.add(itrUserReservationView.getTotalSumPerAttraction());
		}

		return totalSum;
	}

	@Override
	public void deleteReservation(Long id) {
		this.reservationRepository.deleteById(id);

	}

	@Override
	public BigDecimal getMonthlyIncome(LocalDate today, LocalDate previousMonth) {
		return this.reservationRepository.findMonthIncome(today, previousMonth).orElse(null);
	}

}
