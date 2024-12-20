package bg.softuni.ut.web;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import bg.softuni.ut.model.dto.AttractionDTO;
import bg.softuni.ut.model.entity.AttractionEntity;
import bg.softuni.ut.model.entity.DepartmentEntity;
import bg.softuni.ut.repository.DepartmentRepository;
import bg.softuni.ut.repository.attraction.AttractionRepository;
import bg.softuni.ut.service.attraction.AttractionService;

@SpringBootTest
@AutoConfigureMockMvc
class AttractionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AttractionRepository attractionRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private AttractionService attractionService;

	@Autowired
	private ModelMapper modelMapper;

	private AttractionEntity savedAttraction;

	@BeforeEach
	void setUp() throws Exception {

		DepartmentEntity departmentEntity = new DepartmentEntity();
		departmentEntity.setName("Diving");
		this.departmentRepository.save(departmentEntity);

		AttractionDTO attractionDTO = new AttractionDTO();
		attractionDTO.setDescription("description1");
		attractionDTO.setDifficulty("difficulty1");
		attractionDTO.setDivingSeason("diving season 1");
		attractionDTO.setDuration(3);
		attractionDTO.setPrice(new BigDecimal(123));
		attractionDTO.setTitle("title1");
		attractionDTO.setImage("image1");
		attractionDTO.setDepartmentID(departmentEntity.getId());

		savedAttraction = this.attractionService.createAttraction(attractionDTO);
	}

	@AfterEach
	void tearDown() throws Exception {
		if (this.attractionRepository.count() > 0) {
			this.attractionRepository.deleteAll();
		}
		
		this.departmentRepository.deleteAll();
	}

	@Test
	void testShowAllAttractions() throws Exception {

		this.mockMvc.perform(get("/attractions/show-all"))
					.andExpect(status().isOk())
					.andExpect(view().name("attraction/show_attractions"));
	}

	@Test
	void testOpenAttractionAddForm() throws Exception {

		this.mockMvc.perform(get("/attractions/add"))
					.andExpect(status().isOk())
					.andExpect(view().name("attraction/add_attraction"));
	}

	@Test
	void testDetails() throws Exception {

		this.mockMvc.perform(get("/attractions/{title}/details", this.savedAttraction.getTitle()))
					.andExpect(status().isOk())
					.andExpect(view().name("attraction/attraction_info"));

	}

	@Test
	void testDetails_WithTitleError() throws Exception {

		this.mockMvc.perform(get("/attractions/{title}/details", "wrongImageName"))
					.andExpect(status().is4xxClientError())
					.andExpect(view().name("customErrorPage/wrong_attraction_name"));
	}

}
