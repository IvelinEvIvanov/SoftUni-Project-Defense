package bg.softuni.ut.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bg.softuni.ut.model.entity.UserEntity;
import bg.softuni.ut.model.entity.UserRoleEntity;
import bg.softuni.ut.model.entity.enums.UserRoleEnum;
import bg.softuni.ut.model.service.UserEditServiceModel;
import bg.softuni.ut.model.service.UserServiceModel;
import bg.softuni.ut.repository.UserRepository;
import bg.softuni.ut.service.UserRoleService;
import bg.softuni.ut.service.UserService;

@Service
public class UserServiceIml implements UserService {

	private final UserRepository userRepository;
	private final UserRoleService userRoleService;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;

	public UserServiceIml(UserRepository userRepository, UserRoleService userRoleService, ModelMapper modelMapper,
			PasswordEncoder passwordEncoder) {

		this.userRepository = userRepository;
		this.userRoleService = userRoleService;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserEntity findByEmail(String email) {
		return this.userRepository.findByEmailIgnoreCase(email).orElse(null);
	}

	@Transactional
	@Override
	public UserEntity registerUser(UserServiceModel userServiceModel) {
		
		UserEntity user = this.modelMapper.map(userServiceModel, UserEntity.class);

		UserRoleEntity role = this.userRoleService.findByRole(UserRoleEnum.USER);
		user.setRoles(Set.of(role));

		String encodedPassword = this.passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		return this.userRepository.save(user);
	}

	@Override
	public boolean isEmailUnique(String email) {
		return this.userRepository.findByEmailIgnoreCase(email).isEmpty();
	}

	@Override
	public List<UserEntity> findAllUsers() {
		return this.userRepository.findAll(
				Sort.by(Sort.Direction.ASC, "firstName").and(Sort.by(Sort.Direction.ASC, "lastName")));
	}

	@Override
	public void deleteUserById(Long id) {
		this.userRepository.deleteById(id);

	}

	@Override
	public Optional<UserEntity> findUserById(Long id) {
		return this.userRepository.findById(id);
	}

	@Override
	public UserEntity editUser(UserEditServiceModel userEditServiceModel) {

		UserEntity userEntity = this.userRepository.findById(userEditServiceModel.getId()).get();

		this.modelMapper.map(userEditServiceModel, userEntity);

		String codedPassword = this.passwordEncoder.encode(userEditServiceModel.getPassword());
		userEntity.setPassword(codedPassword);

		return this.userRepository.save(userEntity);

	}

	@Override
	public List<UserEntity> findAllManagers() {
		return this.userRepository.findAllManagers();
	}

}
