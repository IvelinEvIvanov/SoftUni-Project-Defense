package bg.softuni.ut.web;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bg.softuni.ut.model.binding.EmployeeEditBindingModel;
import bg.softuni.ut.model.binding.EmployeeRegisterBindingModel;
import bg.softuni.ut.model.entity.EmployeeEntity;
import bg.softuni.ut.model.entity.UserEntity;
import bg.softuni.ut.model.service.EmployeeEditServiceModel;
import bg.softuni.ut.model.service.EmployeeRgisterServiceModel;
import bg.softuni.ut.model.view.EmployeeInfoViewModel;
import bg.softuni.ut.service.EmployeeService;
import bg.softuni.ut.service.UserService;
import bg.softuni.ut.web.exception.EntityNotExistException;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final UserService userService;
	private final EmployeeService employeeService;
	private final ModelMapper modelMapper;

	public AdminController(UserService userService, EmployeeService employeeService, ModelMapper modelMapper) {
		this.userService = userService;
		this.employeeService = employeeService;
		this.modelMapper = modelMapper;
	}

	// Users

	@GetMapping("/show-users")
	public String showUsers(Model model) {
		model.addAttribute("users", this.userService.findAllUsers());

		return "user/show_users";
	}

	@DeleteMapping("/delete/user/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		this.userService.deleteUserById(id);

		return "redirect:/admin/show-users";
	}

	// Employees

	@GetMapping("/user/{id}/add-employee")
	public String addEmployeeGet(@PathVariable("id") Long id, Model model) {

		if (!model.containsAttribute("employeeModel")) {
			EmployeeRegisterBindingModel employeeModel = new EmployeeRegisterBindingModel();
			employeeModel.setUserId(id);

			model.addAttribute("employeeModel", employeeModel);
		}

		return "employee/register_employee";
	}

	@PostMapping("/user/{id}/add-employee")
	public String addEmployeePost(@Valid EmployeeRegisterBindingModel employeeRegisterBindingModel,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("employeeModel", employeeRegisterBindingModel);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.employeeModel",
					bindingResult);

			return "redirect:/admin/user/{id}/add-employee";
		}

		EmployeeRgisterServiceModel employeeRgisterServiceModel = this.modelMapper.map(employeeRegisterBindingModel,
				EmployeeRgisterServiceModel.class);

		this.employeeService.registerEmployee(employeeRgisterServiceModel);

		return "redirect:/admin/show-users";
	}

	@GetMapping("/show-employees")
	public String showEmployees(Model model) {
		model.addAttribute("allEmployees", this.employeeService.findAllEmployees());

		return "employee/show_employees";
	}

	@GetMapping("/employee/{id}/edit")
	public String editEmployeeGet(Model model, @PathVariable("id") Long id) {

		if (!model.containsAttribute("employeeEditBindingModel")) {

			EmployeeEntity employeeEntity = this.employeeService.findEmployeeById(id);
			EmployeeEditBindingModel employeeEditBindingModel = this.modelMapper.map(employeeEntity,
					EmployeeEditBindingModel.class);
			
			if (employeeEntity.getManager() != null) {
				employeeEditBindingModel.setManagerID(employeeEntity.getManager().getUser().getId());
			}

			model.addAttribute("employeeEditBindingModel", employeeEditBindingModel);
		}

		/*
		 * //Other way for many-to-many join UserRoleEntity
		 * userRole=this.userRoleRepository.findByRole(UserRoleEnum.MANAGER);
		 * List<UserEntity> users=this.userRepository.findAllManagers(userRole);
		 */

		List<UserEntity> managers = this.userService.findAllManagers();
		model.addAttribute("allManagers", managers);

		return "employee/edit_employee";
	}

	@PostMapping("/employee/{id}/edit")
	public String editEmployeePost(@PathVariable("id") Long id,
			@Valid EmployeeEditBindingModel employeeEditBindingModel, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("employeeEditBindingModel", employeeEditBindingModel);
			redirectAttributes.addFlashAttribute(
					"org.springframework.validation.BindingResult.employeeEditBindingModel", bindingResult);

			return "redirect:/admin/employee/{id}/edit";
		}

		EmployeeEditServiceModel employeeEditServiceModel = this.modelMapper.map(employeeEditBindingModel,
				EmployeeEditServiceModel.class);

		this.employeeService.editEmployee(employeeEditServiceModel);

		return "redirect:/admin/show-employees";
	}

	@DeleteMapping("/delete/employee/{id}")
	public String deleteEmployeeDelete(@PathVariable("id") Long id) {
		
		this.employeeService.deleteEmployeeById(id);

		return "redirect:/admin/show-employees";
	}

	@GetMapping("/employee-info/{id}")
	public String showEmployeeInfoGet(@PathVariable("id") Long id, Model model) {
		EmployeeEntity employeeEntity = this.employeeService.findEmployeeById(id);
		EmployeeInfoViewModel employeeInfoViewModel = this.modelMapper.map(employeeEntity, EmployeeInfoViewModel.class);

		model.addAttribute("employeeInfo", employeeInfoViewModel);

		return "employee/employee_info";
	}

	@ExceptionHandler(EntityNotExistException.class)
	public ModelAndView handleEntityException(EntityNotExistException ex) {

		ModelAndView modelAndView = new ModelAndView("customErrorPage/error_no_such_id");
		modelAndView.addObject("message", ex.getMessage());
		modelAndView.addObject("employeeId", ex.getId());
		modelAndView.setStatus(HttpStatus.NOT_FOUND);

		return modelAndView;
	}
}
