package bg.softuni.ut.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;
import java.time.LocalDate;

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
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.web.servlet.MockMvc;

import bg.softuni.ut.model.entity.EmployeeEntity;
import bg.softuni.ut.model.entity.UserEntity;
import bg.softuni.ut.model.entity.UserRoleEntity;
import bg.softuni.ut.model.entity.enums.UserGenderEnum;
import bg.softuni.ut.model.entity.enums.UserRoleEnum;

import bg.softuni.ut.model.service.UserServiceModel;
import bg.softuni.ut.repository.EmployeeRepository;
import bg.softuni.ut.repository.UserRepository;
import bg.softuni.ut.repository.UserRoleRepository;
import bg.softuni.ut.service.EmployeeService;
import bg.softuni.ut.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private ModelMapper modelMapper;

	private Long userId;
	private Long testId;

	UserEntity savedAdmin;
	UserEntity savedUser;
	UserEntity savedTestUser;

	EmployeeEntity employeeManager;
	EmployeeEntity employee;

	@BeforeEach
	void setUp() throws Exception {

		// Role ADMIN
		UserRoleEntity roleAdmin = new UserRoleEntity();
		roleAdmin.setRole(UserRoleEnum.ADMIN);
		this.userRoleRepository.save(roleAdmin);

		// Role USER
		UserRoleEntity roleUser = new UserRoleEntity();
		roleUser.setRole(UserRoleEnum.USER);
		this.userRoleRepository.save(roleUser);

		// User Admin
		UserEntity admin = new UserEntity();
		admin.setAge(33);
		admin.setEmail("admin@gbg.bg");
		admin.setFirstName("Admin");
		admin.setLastName("Adminov");
		admin.setGender(UserGenderEnum.Male);
		admin.setPassword("123");
		admin.setPhone("0989123456");
		admin.setRoles(Set.of(roleAdmin));

		// User
		UserEntity user = new UserEntity();
		user.setAge(33);
		user.setEmail("pesho@gbg.bg");
		user.setFirstName("Pesho");
		user.setLastName("Peshev");
		user.setGender(UserGenderEnum.Male);
		user.setPassword("123");
		user.setPhone("0989123456");
		user.setRoles(Set.of(roleUser));

		// Test User
		UserEntity testUser = new UserEntity();
		testUser.setAge(33);
		testUser.setEmail("test@gbg.bg");
		testUser.setFirstName("Test");
		testUser.setLastName("Testov");
		testUser.setGender(UserGenderEnum.Male);
		testUser.setPassword("123");
		testUser.setPhone("0989123456");
		user.setRoles(Set.of(roleUser));

		savedAdmin = this.userService.registerUser(this.modelMapper.map(admin, UserServiceModel.class));
		savedUser = this.userService.registerUser(this.modelMapper.map(user, UserServiceModel.class));
		savedTestUser = this.userService.registerUser(this.modelMapper.map(testUser, UserServiceModel.class));

		employeeManager = new EmployeeEntity();
		employeeManager.setUser(savedAdmin);
		employeeManager.setBirthDate(LocalDate.of(1988, 1, 1));
		employeeManager.setHireDate(LocalDate.of(2000, 1, 1));
		employeeManager.setJobTitle("Diver Manager");
		employeeManager.setSalary(BigDecimal.valueOf(222l));

		this.employeeRepository.save(employeeManager);

		employee = new EmployeeEntity();
		employee.setUser(savedUser);
		employee.setManager(this.employeeManager);
		employee.setBirthDate(LocalDate.of(1988, 1, 1));
		employee.setHireDate(LocalDate.of(2000, 1, 1));
		employee.setJobTitle("Diver User");
		employee.setSalary(BigDecimal.valueOf(222l));

		this.employeeRepository.save(employee);

		this.userId = savedUser.getId();

	}

	@AfterEach
	void tearDown() throws Exception {
		if (this.userRepository.count() > 0) {
			this.userRepository.deleteAll();
		}

		this.userRoleRepository.deleteAll();
	}

	@Test
	@WithMockUser(username = "admin@gbg.bg", authorities = { "ADMIN" })
	void testShowUser() throws Exception {

		this.mockMvc.perform(get("/admin/show-users"))
					.andExpect(status().isOk())
					.andExpect(view().name("user/show_users"))
					.andExpect(model().attributeExists("users"));

	}

	@Test
	@WithMockUser(username = "admin@gbg.bg", authorities = { "ADMIN" })
	void testDeleteUser() throws Exception {

		this.mockMvc.perform(delete("/admin/delete/user/{id}", this.userId).with(csrf()))
					.andExpect(status().is3xxRedirection());

	}

	@Test
	@WithMockUser(username = "admin@gbg.bg", authorities = { "ADMIN" })
	void testAddEmployeeGet() throws Exception {

		this.mockMvc.perform(get("/admin/user/{id}/add-employee", this.userId).with(csrf()))
					.andExpect(status().isOk())
					.andExpect(view().name("employee/register_employee"))
					.andExpect(model().attributeExists("employeeModel"));
	}

	@Test
	@WithMockUser(username = "admin@gbg.bg", authorities = { "ADMIN" })
	void testAddEmployeePost() throws Exception {

		this.mockMvc.perform(post("/admin/user/{id}/add-employee",
				this.savedTestUser.getId())	.param("userId", String.valueOf(this.savedTestUser.getId()))
											.param("jobTitle", "Diver")
											.param("hireDate", "2000-01-01")
											.param("birthDate", "1980-01-01")
											.param("roles", "EMPLOYEE")
											.param("salary", "222")
											.with(csrf())
											.contentType(MediaType.APPLICATION_FORM_URLENCODED))
					.andExpect(status().is3xxRedirection());

		EmployeeEntity actualEmployee = this.employeeRepository.findByUserId(this.savedTestUser.getId());

		Assertions.assertEquals("Diver", actualEmployee.getJobTitle());
		Assertions.assertEquals("2000-01-01", actualEmployee.getHireDate().toString());
		Assertions.assertEquals("1980-01-01", actualEmployee.getBirthDate().toString());
		Assertions.assertEquals(1, actualEmployee.getUser().getRoles().size());
		Assertions.assertEquals("222.00", actualEmployee.getSalary().toString());

	}

	@Test
	@WithMockUser(username = "admin@gbg.bg", authorities = { "ADMIN" })
	void testAddEmployeePost_WithError() throws Exception {

		this.mockMvc.perform(
				post("/admin/user/{id}/add-employee", this.userId)	.param("userId", String.valueOf(this.userId))
																	.param("jobTitle", "Diver")
																	.param("hireDate", "2000-01-01")
																	.param("birthDate", "1980-01-01")
																	.param("roles", "")// Error
																	.param("salary", "222")
																	.with(csrf())
																	.contentType(MediaType.APPLICATION_FORM_URLENCODED))
					.andExpect(status().is3xxRedirection());

	}

	@Test
	@WithMockUser(username = "admin@gbg.bg", authorities = { "ADMIN" })
	void testShowEmployees() throws Exception {
		this.mockMvc.perform(get("/admin/show-employees"))
					.andExpect(status().isOk())
					.andExpect(model().attributeExists("allEmployees"))
					.andExpect(view().name("employee/show_employees"));
	}

	@Test
	@WithMockUser(username = "admin@gbg.bg", authorities = { "ADMIN" })
	void testEditEmployeeGet() throws Exception {

		this.mockMvc.perform(get("/admin/employee/{id}/edit", this.employee.getId()).with(csrf()))
					.andExpect(status().isOk())
					.andExpect(model().attributeExists("employeeEditBindingModel", "allManagers"))
					.andExpect(view().name("employee/edit_employee"));

	}

	@Test
	@WithMockUser(username = "admin@gbg.bg", authorities = { "ADMIN" })
	void testEditEmployeeGet_WithoutManager() throws Exception {

		this.mockMvc.perform(get("/admin/employee/{id}/edit", this.employeeManager.getId()).with(csrf()))
					.andExpect(status().isOk())
					.andExpect(model().attributeExists("employeeEditBindingModel"))
					.andExpect(view().name("employee/edit_employee"));

	}

	@Test
	@WithMockUser(username = "admin@gbg.bg", authorities = { "ADMIN" })
	void testEditEmployeePost() throws Exception {

		this.mockMvc.perform(
				post("/admin/employee/{id}/edit", this.employee.getId())
																		.param("id",
																				String.valueOf(this.employee.getId()))
																		.param("managerID",
																				this.employeeManager.getId().toString())
																		.param("jobTitle", "New Job")
																		.param("hireDate", "2000-01-01")
																		.param("birthDate", "1980-01-01")
																		.param("roles", "EMPLOYEE")
																		.param("salary", "333")
																		.contentType(
																				MediaType.APPLICATION_FORM_URLENCODED)
																		.with(csrf()))

					.andExpect(status().is3xxRedirection());

	}

	@Test
	@WithMockUser(username = "admin@gbg.bg", authorities = { "ADMIN" })
	void testEditEmployeePost_WithError() throws Exception {

		this.mockMvc.perform(
				post("/admin/employee/{id}/edit", this.employee.getId())
																		.param("id",
																				String.valueOf(this.employee.getId()))
																		.param("managerID",
																				this.employeeManager.getId().toString())
																		.param("jobTitle", "New Job")
																		.param("hireDate", "2000-01-01")
																		.param("birthDate", "1980-01-01")
																		.param("roles", "EMPLOYEE")
																		.param("salary", "1")// Error
																		.contentType(
																				MediaType.APPLICATION_FORM_URLENCODED)
																		.with(csrf()))

					.andExpect(status().is3xxRedirection());

	}

	@Test
	@WithMockUser(username = "admin@gbg.bg", authorities = { "ADMIN" })
	void testDeleteEmployee() throws Exception {

		this.mockMvc.perform(delete("/admin/delete/employee/{id}", this.employee.getId()).with(csrf()))
					.andExpect(status().is3xxRedirection());

	}

	@Test
	@WithMockUser(username = "admin@gbg.bg", authorities = { "ADMIN" })
	void testShowEmployeeInfoGet() throws Exception {

		this.mockMvc.perform(get("/admin/employee-info/{id}", employee.getId()))
					.andExpect(status().isOk())
					.andExpect(view().name("employee/employee_info"))
					.andExpect(model().attributeExists("employeeInfo"));
	}

}
