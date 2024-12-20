package bg.softuni.ut.repository.attraction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bg.softuni.ut.model.entity.ReservationEntity;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long>{
	
	@Query("SELECT r FROM ReservationEntity r JOIN r.user")
	Optional<List<ReservationEntity>> findAllUserRervations();
	
	@Query("SELECT sum(r.bill) FROM ReservationEntity r WHERE r.reservationDate BETWEEN ?2 AND ?1")
	Optional<BigDecimal> findMonthIncome(LocalDate today,LocalDate previousMonth); 
	
}
