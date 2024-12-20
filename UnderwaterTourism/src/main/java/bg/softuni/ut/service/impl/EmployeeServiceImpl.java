package bg.softuni.ut.service.impl;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import bg.softuni.ut.model.binding.UserEditBindingModel;
import bg.softuni.ut.model.entity.EmployeeEntity;
import bg.softuni.ut.model.entity.UserEntity;
import bg.softuni.ut.model.entity.UserRoleEntity;
import bg.softuni.ut.model.entity.enums.UserRoleEnum;
import bg.softuni.ut.model.service.EmployeeEditServiceModel;
import bg.softuni.ut.model.service.EmployeeRgisterServiceModel;
import bg.softuni.ut.model.service.UserEditServiceModel;
import bg.softuni.ut.repository.EmployeeRepository;
import bg.softuni.ut.repository.UserRepository;
import bg.softuni.ut.repository.UserRoleRepository;
import bg.softuni.ut.service.EmployeeService;
import bg.softuni.ut.service.UserService;
import bg.softuni.ut.web.exception.EntityNotExistException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final ModelMapper modelMapper;
	private final UserService userService;
	private final EmployeeRepository employeeRepository;
	private final UserRoleRepository userRoleRepository;
	private final UserRepository userRepository;

	public EmployeeServiceImpl(ModelMapper modelMapper, UserService userService, EmployeeRepository employeeRepository,
			UserRoleRepository userRoleRepository, UserRepository userRepository) {

		this.modelMapper = modelMapper;
		this.userService = userService;
		this.employeeRepository = employeeRepository;
		this.userRoleRepository = userRoleRepository;
		this.userRepository = userRepository;

	}

	@Transactional
	@Override
	public EmployeeEntity registerEmployee(EmployeeRgisterServiceModel employeeRgisterServiceModel) {

		EmployeeEntity employeeEntity = this.modelMapper.map(employeeRgisterServiceModel, EmployeeEntity.class);
		employeeEntity.setId(null);

		UserEntity userEntity = this.userService.findUserById(employeeRgisterServiceModel.getUserId())
												.orElseThrow(() -> new EntityNotExistException(
														"Can't find Employee with such ID:",
														employeeRgisterServiceModel.getUserId()));

		Set<UserRoleEntity> userRoles = userEntity.getRoles();

		for (String itr : employeeRgisterServiceModel.getRoles()) {
			UserRoleEntity userRoleEntity = this.userRoleRepository.findByRole(UserRoleEnum.valueOf(itr));
			userRoles.add(userRoleEntity);
		}

		employeeEntity.setUser(userEntity);

		return this.employeeRepository.save(employeeEntity);
	}

	@Override
	public List<EmployeeEntity> findAllEmployees() {
		return this.employeeRepository.findAll();
	}

	@Override
	public EmployeeEntity findEmployeeById(Long id) {
		return this.employeeRepository.findById(
				id).orElseThrow(() -> new EntityNotExistException("Can't find Employee with such ID: ", id));
	}

	@Override
	public EmployeeEntity findManagerByUserId(Long id) {
		return this.employeeRepository.findByUserId(id);
	}

	@Override
	public EmployeeEntity editEmployee(EmployeeEditServiceModel employeeEditServiceModel) {

		EmployeeEntity employeeEntity = this.findEmployeeById(employeeEditServiceModel.getId());
		this.modelMapper.map(employeeEditServiceModel, employeeEntity);

		if (employeeEntity.getManager() == null && employeeEditServiceModel.getManagerID() != null) {
			EmployeeEntity manager = this.findManagerByUserId(employeeEditServiceModel.getManagerID());
			employeeEntity.setManager(manager);
		}

		return this.employeeRepository.save(employeeEntity);

	}

	@Override
	public void deleteEmployeeById(Long id) {

		EmployeeEntity currentEmployee = this.employeeRepository.findById(id).get();
		UserEntity employeeUserEntity = currentEmployee.getUser();
		
		UserRoleEntity role=this.userRoleRepository.findByRole(UserRoleEnum.USER);
		employeeUserEntity.setRoles(Set.of(role));
		
		this.userRepository.save(employeeUserEntity);

		this.employeeRepository.deleteEmployeeById(id);
	}

}
