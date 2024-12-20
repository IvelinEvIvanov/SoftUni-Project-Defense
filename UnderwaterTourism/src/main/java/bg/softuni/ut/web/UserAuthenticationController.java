package bg.softuni.ut.web;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bg.softuni.ut.model.binding.UserRegisterBindingModel;
import bg.softuni.ut.model.entity.UserEntity;
import bg.softuni.ut.model.service.UserDetailsImpl;
import bg.softuni.ut.model.service.UserServiceModel;
import bg.softuni.ut.service.UserService;

@Controller
@RequestMapping("/user")
public class UserAuthenticationController {

	private final UserService userService;
	private final ModelMapper modelMapper;

	public UserAuthenticationController(UserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;

	}

	@ModelAttribute(name = "userRegisterBindingModel")
	public UserRegisterBindingModel userRegisterBindingModel() {
		UserRegisterBindingModel userRegisterBindingModel = new UserRegisterBindingModel();
		return userRegisterBindingModel;
	}

	@GetMapping("/register")
	public String registerGet(Model model) {
		return "authentication/register";
	}

	@PostMapping("/register")
	public String registerPost(@Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		boolean arePasswordsEquals = userRegisterBindingModel	.getPassword()
																.equals(userRegisterBindingModel.getConfirmPassword());

		if (bindingResult.hasErrors() || !arePasswordsEquals) {
			
			redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
			redirectAttributes.addFlashAttribute(
					"org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

			userRegisterBindingModel.setWrongInput(true);

			return "redirect:register";
		}

		UserServiceModel userServiceModel = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
		this.userService.registerUser(userServiceModel);

		return "redirect:login";
	}

	@GetMapping("/login")
	public String loginGet() {
		return "authentication/login";
	}

	@PostMapping("/login-error")
	public String loginPost(@ModelAttribute("email") String email, RedirectAttributes redirectAttributes) {

		redirectAttributes.addFlashAttribute("email", email);
		redirectAttributes.addFlashAttribute("wrongInput", true);

		return "redirect:login";
	}

}
