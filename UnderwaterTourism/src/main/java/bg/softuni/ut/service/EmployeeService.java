package bg.softuni.ut.service;

import java.util.List;

import bg.softuni.ut.model.entity.EmployeeEntity;
import bg.softuni.ut.model.service.EmployeeEditServiceModel;
import bg.softuni.ut.model.service.EmployeeRgisterServiceModel;

public interface EmployeeService {

	EmployeeEntity registerEmployee(EmployeeRgisterServiceModel employeeRgisterServiceModel);
	
	List<EmployeeEntity> findAllEmployees();

	EmployeeEntity findEmployeeById(Long id);
	
	EmployeeEntity findManagerByUserId(Long id);

	EmployeeEntity editEmployee(EmployeeEditServiceModel employeeEditServiceModel);
	
	void deleteEmployeeById(Long id);
}
