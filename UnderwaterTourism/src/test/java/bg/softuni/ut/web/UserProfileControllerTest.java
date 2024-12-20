package bg.softuni.ut.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import bg.softuni.ut.model.entity.UserEntity;
import bg.softuni.ut.model.entity.UserRoleEntity;
import bg.softuni.ut.model.entity.enums.UserGenderEnum;
import bg.softuni.ut.model.entity.enums.UserRoleEnum;
import bg.softuni.ut.model.service.UserDetailsImpl;
import bg.softuni.ut.model.service.UserServiceModel;
import bg.softuni.ut.repository.UserRepository;
import bg.softuni.ut.repository.UserRoleRepository;
import bg.softuni.ut.service.UserService;
import bg.softuni.ut.service.impl.UserDetailsServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
class UserProfileControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private ModelMapper modelMapper;

	private UserEntity savedUser;;
	private UserRoleEntity roleUser;

	private Long userId;

	// @Autowired
	// private UserDetailsServiceImpl userDetailsServiceImpl;

	@BeforeEach
	void setUp() throws Exception {

		roleUser = new UserRoleEntity();
		roleUser.setRole(UserRoleEnum.USER);
		this.userRoleRepository.save(roleUser);

		UserEntity newUser = new UserEntity();
		newUser.setAge(33);
		newUser.setEmail("pesho@gbg.bg");
		newUser.setFirstName("Pesho");
		newUser.setLastName("Peshev");
		newUser.setGender(UserGenderEnum.Male);
		newUser.setPassword("123");
		newUser.setPhone("0989123456");
		newUser.setRoles(Set.of(roleUser));

		this.savedUser = this.userService.registerUser(this.modelMapper.map(newUser, UserServiceModel.class));

	}

	@AfterEach
	void tearDown() throws Exception {
		if (this.userRepository.count() > 0) {
			this.userRepository.deleteAll();
		}

		this.userRoleRepository.deleteAll();
	}

	@Test
	void testEditUserPost() throws Exception {
		Assertions.assertEquals(1, this.userRepository.count());

		UserDetailsServiceImpl userDetailsServiceImpl = new UserDetailsServiceImpl(this.userService);
		UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(this.savedUser.getEmail());

		this.mockMvc.perform(
				patch("/user-profile/{id}/edit", this.savedUser.getId()).with(user(userDetails))
																		.param("firstName", "Pesho")
																		.param("lastName", "Peshev")
																		.param("age", "33")
																		.param("gender", "Male")
																		.param("email", "pesho@gbg.bg")
																		.param("phone", "0898333555")// Edit
																		.param("password", "123")
																		.param("confirmPassword", "123")
																		.with(csrf())
																		.contentType(
																				MediaType.APPLICATION_FORM_URLENCODED))
					.andExpect(status().is3xxRedirection());

		UserEntity actualUser = this.userService.findByEmail("pesho@gbg.bg");
		Assertions.assertNotNull(actualUser);

		Assertions.assertEquals(1, this.userRepository.count());

		Assertions.assertEquals("Pesho", actualUser.getFirstName());
		Assertions.assertEquals("Peshev", actualUser.getLastName());
		Assertions.assertEquals(33, actualUser.getAge());
		Assertions.assertEquals("Male", actualUser.getGender().name());
		Assertions.assertEquals("pesho@gbg.bg", actualUser.getEmail());
		Assertions.assertEquals("0898333555", actualUser.getPhone());
	}

}
