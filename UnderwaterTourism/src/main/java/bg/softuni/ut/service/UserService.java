package bg.softuni.ut.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import bg.softuni.ut.model.entity.UserEntity;
import bg.softuni.ut.model.service.UserEditServiceModel;
import bg.softuni.ut.model.service.UserServiceModel;
import bg.softuni.ut.model.view.UserReservationView;

public interface UserService {

	void deleteUserById(Long id);

	UserEntity findByEmail(String email);

	UserEntity registerUser(UserServiceModel userServiceModel);

	boolean isEmailUnique(String email);

	List<UserEntity> findAllUsers();

	List<UserEntity> findAllManagers();

	Optional<UserEntity> findUserById(Long id);

	UserEntity editUser(UserEditServiceModel userEditServiceModel);

}
