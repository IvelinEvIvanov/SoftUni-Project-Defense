package bg.softuni.ut.web.attraction;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bg.softuni.ut.model.dto.AttractionDTO;
import bg.softuni.ut.model.dto.DepartmentDTO;
import bg.softuni.ut.model.dto.ReservationDTO;
import bg.softuni.ut.model.entity.AttractionEntity;
import bg.softuni.ut.service.DepartmentService;
import bg.softuni.ut.service.attraction.AttractionService;
import bg.softuni.ut.web.exception.EntityNotExistException;

@Controller
@RequestMapping(value = "/attractions")
public class AttractionController {

	private final AttractionService attractionService;
	private final DepartmentService departmentService;

	public AttractionController(AttractionService attractionService, DepartmentService departmentService) {
		this.attractionService = attractionService;
		this.departmentService = departmentService;
	}

	@GetMapping("/show-all")
	public String showAllAttractions(Model model) {

		List<AttractionEntity> attractions = this.attractionService.findAllAttractions();
		model.addAttribute("attractions", attractions);

		return "attraction/show_attractions";
	}

	@GetMapping("/add")
	public String addAttractionGet(Model model) {

		if (!model.containsAttribute("attraction") && !model.containsAttribute("departments")) {
			model.addAttribute("attraction", new AttractionDTO());

			List<DepartmentDTO> departmentsDTOs = this.departmentService.getAllDepartments();
			if (departmentsDTOs != null) {
				model.addAttribute("allDepartments", departmentsDTOs);
			}
		}

		return "attraction/add_attraction";
	}

	@PostMapping(value = "/add")
	public String addAttractionPost(@Valid AttractionDTO attractionDTO, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, @RequestParam("imageUpload") MultipartFile multipartFile)
			throws IOException {

		if (bindingResult.hasErrors()) {

			redirectAttributes.addFlashAttribute("attraction", attractionDTO);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.attraction",
					bindingResult);

			return "redirect:/attractions/add";
		}

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		attractionDTO.setImage(fileName);

		this.attractionService.createAttraction(attractionDTO);

		return "index";
	}

	@GetMapping("/{title}/details")
	public String showAttractionInfo(@PathVariable("title") String title, Model model) {

		AttractionEntity attraction = this.attractionService.findAttractionByTitle(title);
		if (attraction == null) {
			throw new EntityNotExistException("Wrong attraction name.\n" + "There is no such attraction:", title);
		}

		model.addAttribute("attraction", attraction);

		if (!model.containsAttribute("reservation")) {
			model.addAttribute("reservation", new ReservationDTO());
		}

		return "attraction/attraction_info";
	}

	@DeleteMapping("/remove/{id}")
	public String deleteAttraction(@PathVariable("id") Long id) {
		this.attractionService.deleteAttraction(id);
		return "redirect:/attractions/show-all";
	}

	@ExceptionHandler(EntityNotExistException.class)
	public ModelAndView handleEntityException(EntityNotExistException ex) {

		ModelAndView modelAndView = new ModelAndView("customErrorPage/wrong_attraction_name");
		modelAndView.addObject("message", ex.getMessage());
		modelAndView.addObject("attractionName", ex.getAttractionName());
		modelAndView.setStatus(HttpStatus.NOT_FOUND);

		return modelAndView;
	}

}
