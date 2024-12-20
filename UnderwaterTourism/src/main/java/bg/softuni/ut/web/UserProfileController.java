package bg.softuni.ut.web;

import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import bg.softuni.ut.model.binding.UserEditBindingModel;
import bg.softuni.ut.model.binding.UserRegisterBindingModel;
import bg.softuni.ut.model.entity.UserEntity;
import bg.softuni.ut.model.service.UserDetailsImpl;
import bg.softuni.ut.model.service.UserEditServiceModel;
import bg.softuni.ut.service.UserService;

@Controller
@RequestMapping("/user-profile")
public class UserProfileController {

	private final UserService userService;
	private final ModelMapper modelMapper;

	public UserProfileController(UserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/show")
	public String showProfileGet(Model model, @AuthenticationPrincipal UserDetailsImpl currentUser) {

		if (!model.containsAttribute("myProfile")) {

			UserEntity userEntity = this.userService.findUserById(currentUser.getUserId()).get();

			UserRegisterBindingModel userRegisterBindingModel = this.modelMapper.map(userEntity,
					UserRegisterBindingModel.class);
			
			model.addAttribute("myProfile", userRegisterBindingModel);
		}

		return "user/user_profile";
	}

	@PatchMapping("/{id}/edit")
	public String editProfilePost(@PathVariable("id") Long id, @Valid UserEditBindingModel userEditBindingModel,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		boolean arePasswordsEquals = userEditBindingModel	.getPassword()
															.equals(userEditBindingModel.getConfirmPassword());

		if (bindingResult.hasErrors() || !arePasswordsEquals) {
			
			redirectAttributes.addFlashAttribute("myProfile", userEditBindingModel);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.myProfile",
					bindingResult);

			userEditBindingModel.setWrongInput(true);

			return "redirect:/user-profile/show";
		}

		UserEditServiceModel userEditServiceModel = this.modelMapper.map(userEditBindingModel,
				UserEditServiceModel.class);
		
		userEditServiceModel.setId(id);

		this.userService.editUser(userEditServiceModel);

		return "redirect:/";
	}

}
