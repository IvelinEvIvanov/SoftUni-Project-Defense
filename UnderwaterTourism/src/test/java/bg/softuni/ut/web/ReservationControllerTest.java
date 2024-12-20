package bg.softuni.ut.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import bg.softuni.ut.model.dto.AttractionDTO;
import bg.softuni.ut.model.dto.ReservationDTO;
import bg.softuni.ut.model.entity.AttractionEntity;
import bg.softuni.ut.model.entity.DepartmentEntity;
import bg.softuni.ut.model.entity.UserEntity;
import bg.softuni.ut.model.entity.UserRoleEntity;
import bg.softuni.ut.model.entity.enums.PartOfDayEnum;
import bg.softuni.ut.model.entity.enums.UserGenderEnum;
import bg.softuni.ut.model.entity.enums.UserRoleEnum;
import bg.softuni.ut.model.service.UserServiceModel;
import bg.softuni.ut.repository.DepartmentRepository;
import bg.softuni.ut.repository.UserRepository;
import bg.softuni.ut.repository.UserRoleRepository;
import bg.softuni.ut.repository.attraction.AttractionRepository;
import bg.softuni.ut.repository.attraction.ReservationRepository;
import bg.softuni.ut.service.UserService;
import bg.softuni.ut.service.attraction.AttractionService;
import bg.softuni.ut.service.attraction.ReservationService;

@SpringBootTest
@AutoConfigureMockMvc
class ReservationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private AttractionRepository attractionRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AttractionService attractionService;
	
	@Autowired
	private DepartmentRepository departmentRepository;

	private AttractionDTO attractionDTO;
	private UserEntity user;
	private AttractionEntity attractionEntity;

	@BeforeEach
	void setUp() {

		// Role USER
		UserRoleEntity roleUser = new UserRoleEntity();
		roleUser.setRole(UserRoleEnum.USER);

		this.userRoleRepository.save(roleUser);

		// User
		user = new UserEntity();
		user.setAge(33);
		user.setEmail("pesho@gbg.bg");
		user.setFirstName("Pesho");
		user.setLastName("Peshev");
		user.setGender(UserGenderEnum.Male);
		user.setPassword("123");
		user.setPhone("0989123456");
		user.setRoles(Set.of(roleUser));

		this.userService.registerUser(this.modelMapper.map(user, UserServiceModel.class));
		
		DepartmentEntity departmentEntity = new DepartmentEntity();
		departmentEntity.setName("Diving");
		this.departmentRepository.save(departmentEntity);

		// Attraction
		attractionDTO = new AttractionDTO();
		attractionDTO.setDescription("description1");
		attractionDTO.setDifficulty("difficulty1");
		attractionDTO.setDivingSeason("diving season 1");
		attractionDTO.setDuration(3);
		attractionDTO.setPrice(new BigDecimal(123));
		attractionDTO.setTitle("title1");
		attractionDTO.setImage("image1");
		attractionDTO.setDepartmentID(departmentEntity.getId());

		attractionEntity = this.attractionService.createAttraction(attractionDTO);

	}

	@AfterEach
	void tearDown() {
		if (this.userRepository.count() > 0) {
			this.reservationRepository.deleteAll();
			this.attractionRepository.deleteAll();
			this.userRepository.deleteAll();
		}

		this.userRoleRepository.deleteAll();
		this.departmentRepository.deleteAll();
	}

	@Test
	@WithMockUser(username = "pesho@gbg.bg", authorities = "USER")
	void testShowUserReservation_WithoutResrvation() throws Exception {

		this.mockMvc.perform(get("/reservation/user-reservations"))
					.andExpect(model().attributeExists("missingAttractions"))
					.andExpect(status().isOk())
					.andExpect(view().name("user/user_reservations"));
	}

	@Test
	@WithUserDetails(value = "pesho@gbg.bg", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	void testShowUserReservation_WithResrvation() throws Exception {

		// Reservation
		ReservationDTO newReservation = new ReservationDTO();
		newReservation.setHoursNumber(2);
		newReservation.setPartOfDay(PartOfDayEnum.Day);
		newReservation.setPeopleNumber(2);
		newReservation.setReservationDate(LocalDate.parse("2022-11-01"));
		newReservation.setUser(user);
		newReservation.setAttractions(Set.of(attractionEntity));
		this.reservationService.makeReservation(newReservation, attractionDTO.getTitle());

		this.mockMvc.perform(get("/reservation/user-reservations"))
					.andExpect(model().attributeExists("missingAttractions"))
					.andExpect(status().isOk())
					.andExpect(view().name("user/user_reservations"));
	}

	@Test
	@WithUserDetails(value = "pesho@gbg.bg", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	void testAddReservation() throws Exception {
		this.mockMvc.perform(post("/reservation/{title}",
				this.attractionDTO.getTitle())	.param("reservationDate", "2022-12-12")
												.param("peopleNumber", "2")
												.param("hoursNumber", "2")
												.param("partOfDay", "Day")
												.with(csrf())
												.contentType(MediaType.APPLICATION_FORM_URLENCODED))
					.andExpect(status().is3xxRedirection());

	}

	@Test
	@WithUserDetails(value = "pesho@gbg.bg", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	void testAddReservation_WithError() throws Exception {
		this.mockMvc.perform(post("/reservation/{title}",
				this.attractionDTO.getTitle())	.param("reservationDate", "2022-12-12")
												.param("peopleNumber", "2")
												.param("hoursNumber", "2")
												.param("partOfDay", "Day1")// Error
												.with(csrf())
												.contentType(MediaType.APPLICATION_FORM_URLENCODED))
					.andExpect(status().is3xxRedirection());

	}

}
