package bg.softuni.ut.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import bg.softuni.ut.model.entity.UserRoleEntity;
import bg.softuni.ut.model.entity.enums.UserRoleEnum;
import bg.softuni.ut.repository.UserRoleRepository;

@ExtendWith(MockitoExtension.class)
class UserRoleServiceImplTest {

	private UserRoleEntity admin, user;

	private UserRoleServiceImpl testUserRoleServiceImpl;

	@Mock
	private UserRoleRepository mockUserRoleRepository;

	@BeforeEach
	public void init() {

		this.testUserRoleServiceImpl = new UserRoleServiceImpl(this.mockUserRoleRepository);

		this.admin = new UserRoleEntity();
		this.admin.setRole(UserRoleEnum.ADMIN);

		this.user = new UserRoleEntity();
		this.user.setRole(UserRoleEnum.USER);

	}

	@Test
	public void testFindByRole() {
		// Arrange
		Mockito.when(this.mockUserRoleRepository.findByRole(UserRoleEnum.ADMIN)).thenReturn(admin);

		// Act
		UserRoleEntity actual = this.testUserRoleServiceImpl.findByRole(UserRoleEnum.ADMIN);

		// Assert
		Assertions.assertEquals(this.admin, actual);
		Assertions.assertEquals(this.admin.getRole().name(), actual.getRole().name());
	}

	@Test
	public void testFindByRoleNotFound() {
		// Arrange
		Mockito.when(this.mockUserRoleRepository.findByRole(UserRoleEnum.ADMIN)).thenReturn(admin);

		// Act
		UserRoleEntity actual = this.testUserRoleServiceImpl.findByRole(UserRoleEnum.ADMIN);

		// Assert
		Assertions.assertNotEquals(this.user, actual);
		Assertions.assertNotEquals(this.user.getRole().name(), actual.getRole().name());

	}

}
