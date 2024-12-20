package bg.softuni.ut.service.attraction;

import java.util.List;

import javax.validation.Valid;

import bg.softuni.ut.model.dto.AttractionDTO;
import bg.softuni.ut.model.dto.ReservationDTO;
import bg.softuni.ut.model.entity.AttractionEntity;
import bg.softuni.ut.model.entity.ReservationEntity;

public interface AttractionService {

	AttractionEntity findAttractionByTitle(String string);
	
	List<AttractionEntity> findAllAttractions();

	AttractionEntity createAttraction(AttractionDTO attractionDTO);

	List<ReservationEntity> findAllUserReservations();

	void deleteAttraction(Long id);
}
