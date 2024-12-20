package bg.softuni.ut.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import bg.softuni.ut.model.entity.UserEntity;
import bg.softuni.ut.model.entity.UserRoleEntity;
import bg.softuni.ut.model.entity.enums.UserRoleEnum;
import bg.softuni.ut.repository.UserRepository;
import bg.softuni.ut.repository.UserRoleRepository;
import bg.softuni.ut.service.impl.UserServiceIml;

//@TestInstance(Lifecycle.PER_CLASS) ->Needed for @BeforAll and @AfterAll
@SpringBootTest
@AutoConfigureMockMvc
class UserAuthenticationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private UserServiceIml userServiceIml;

	@BeforeEach
	void setUp() {
		UserRoleEntity role = new UserRoleEntity();
		role.setRole(UserRoleEnum.USER);
		this.userRoleRepository.save(role);
	}

	@AfterEach
	void tearDown() {
		if (this.userRepository.count() > 0) {
			this.userRepository.deleteAll();
		}

		this.userRoleRepository.deleteAll();
	}

	@Test
	void testOpenRegisterForm() throws Exception {
		this.mockMvc.perform(get("/user/register"))
					.andExpect(status().isOk())
					.andExpect(view().name("authentication/register"));
	}

	@Test
	void testRegisterPost() throws Exception {
		this.mockMvc.perform(post("/user/register")	.param("firstName", "Pesho")
													.param("lastName", "Peshev")
													.param("age", "33")
													.param("gender", "Male")
													.param("email", "pesho@gbg.bg")
													.param("phone", "0898333444")
													.param("password", "123")
													.param("confirmPassword", "123")
													.with(csrf())
													.contentType(MediaType.APPLICATION_FORM_URLENCODED))
					.andExpect(status().is3xxRedirection());

		Assertions.assertEquals(1, userRepository.count());

		UserEntity actualUser = userServiceIml.findByEmail("pesho@gbg.bg");
		Assertions.assertNotNull(actualUser);

		Assertions.assertEquals("Pesho", actualUser.getFirstName());
		Assertions.assertEquals("Peshev", actualUser.getLastName());
		Assertions.assertEquals(33, actualUser.getAge());
		Assertions.assertEquals("Male", actualUser.getGender().name());
		Assertions.assertEquals("pesho@gbg.bg", actualUser.getEmail());
		Assertions.assertEquals("0898333444", actualUser.getPhone());

		Assertions.assertTrue(actualUser.getEmployees().isEmpty());
		Assertions.assertEquals(1, actualUser.getRoles().size());

	}

	@Test
	void testRegisterPost_HasErrors_ButNotInPassword() throws Exception {
		this.mockMvc.perform(post("/user/register")	.param("firstName", "Pesho")
													.param("lastName", "Peshev")
													.param("age", "33")
													.param("gender", "Male")
													.param("email", "peshoemail")// Error
													.param("phone", "0898333444")
													.param("password", "123")
													.param("confirmPassword", "123")
													.param("isWrongInput", "false")
													.with(csrf())
													.contentType(MediaType.APPLICATION_FORM_URLENCODED))
					.andExpect(status().is3xxRedirection());

		Assertions.assertEquals(0, userRepository.count());

	}

	@Test
	void testRegisterPost_HasErrors_OnlyInPassword() throws Exception {
		this.mockMvc.perform(post("/user/register")	.param("firstName", "Pesho")
													.param("lastName", "Peshev")
													.param("age", "33")
													.param("gender", "Male")
													.param("email", "pesho@email.bg")
													.param("phone", "0898333444")
													.param("password", "123")
													.param("confirmPassword", "321")// Error
													.param("isWrongInput", "false")
													.with(csrf())
													.contentType(MediaType.APPLICATION_FORM_URLENCODED))
					.andExpect(status().is3xxRedirection());

		Assertions.assertEquals(0, userRepository.count());

	}

	@Test
	void testRegisterPost_HasErrors_InPasswordAndField() throws Exception {
		this.mockMvc.perform(post("/user/register")	.param("firstName", "Pesho")
													.param("lastName", "Peshev")
													.param("age", "33")
													.param("gender", "Male")
													.param("email", "peshoemail")// Error
													.param("phone", "0898333444")
													.param("password", "123")
													.param("confirmPassword", "321")// Error
													.param("isWrongInput", "false")
													.with(csrf())
													.contentType(MediaType.APPLICATION_FORM_URLENCODED))
					.andExpect(status().is3xxRedirection());

		Assertions.assertEquals(0, userRepository.count());

	}

	@Test
	@WithMockUser(username = "pesho@gbg.bg", authorities = { "USER" })
	void testLogin() throws Exception {

		this.mockMvc.perform(get("/user/login"))
					.andExpect(status().isOk())
					.andExpect(view().name("authentication/login"));
	}

	@Test
	@WithMockUser(username = "pesho@gbg.bg", authorities = { "USER" })
	void testLoginPost_WithError() throws Exception {

		this.mockMvc.perform(post("/user/login-error").with(csrf())).andExpect(status().is3xxRedirection());

	}

}
