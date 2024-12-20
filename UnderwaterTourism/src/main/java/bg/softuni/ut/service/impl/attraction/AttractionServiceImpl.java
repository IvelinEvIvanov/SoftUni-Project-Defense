package bg.softuni.ut.service.impl.attraction;

import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import bg.softuni.ut.model.dto.AttractionDTO;
import bg.softuni.ut.model.dto.ReservationDTO;
import bg.softuni.ut.model.entity.AttractionEntity;
import bg.softuni.ut.model.entity.DepartmentEntity;
import bg.softuni.ut.model.entity.ReservationEntity;
import bg.softuni.ut.model.entity.UserEntity;
import bg.softuni.ut.model.service.UserDetailsImpl;
import bg.softuni.ut.repository.DepartmentRepository;
import bg.softuni.ut.repository.attraction.AttractionRepository;
import bg.softuni.ut.repository.attraction.ReservationRepository;
import bg.softuni.ut.service.attraction.AttractionService;
import bg.softuni.ut.service.impl.UserServiceIml;

@Service
public class AttractionServiceImpl implements AttractionService {

	private final AttractionRepository attractionRepository;
	private final ReservationRepository reservationRepository;
	private final DepartmentRepository departmentRepository;
	private final UserServiceIml userServiceIml;
	private final UserDetailsImpl userDetailsImpl;
	private final ModelMapper modelMapper;

	public AttractionServiceImpl(AttractionRepository attractionRepository, ReservationRepository reservationRepository,
			UserServiceIml userServiceIml, UserDetailsImpl userDetailsImpl, ModelMapper modelMapper,
			DepartmentRepository departmentRepository) {

		this.attractionRepository = attractionRepository;
		this.reservationRepository = reservationRepository;
		this.departmentRepository = departmentRepository;
		this.userServiceIml = userServiceIml;
		this.userDetailsImpl = userDetailsImpl;
		this.modelMapper = modelMapper;
	}

	@Override
	public AttractionEntity findAttractionByTitle(String title) {
		return this.attractionRepository.findByTitle(title);
	}

	@Override
	public List<AttractionEntity> findAllAttractions() {
		return this.attractionRepository.findAll();
	}

	@Override
	public AttractionEntity createAttraction(AttractionDTO attractionDTO) {

		AttractionEntity attractionEntity = this.modelMapper.map(attractionDTO, AttractionEntity.class);
		DepartmentEntity selectedDepartment = this.departmentRepository	.findById(attractionDTO.getDepartmentID())
																		.orElse(null);
		
		if(selectedDepartment!=null) {
			attractionEntity.setDepartment(selectedDepartment);
		}

		return this.attractionRepository.save(attractionEntity);
	}

	@Override
	public List<ReservationEntity> findAllUserReservations() {
		return this.reservationRepository.findAllUserRervations().orElse(null);
	}

	@Override
	public void deleteAttraction(Long id) {
		this.attractionRepository.deleteById(id);
	}

}
