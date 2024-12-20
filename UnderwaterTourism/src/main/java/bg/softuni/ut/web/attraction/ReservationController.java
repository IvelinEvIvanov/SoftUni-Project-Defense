package bg.softuni.ut.web.attraction;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bg.softuni.ut.model.dto.ReservationDTO;
import bg.softuni.ut.model.view.UserReservationView;
import bg.softuni.ut.service.attraction.ReservationService;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

	private final ReservationService reservationService;

	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@PostMapping("/{title}")
	public String makeReservation(@Valid ReservationDTO reservationDTO, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, @PathVariable("title") String attractionTitle) {
		
		
		if (bindingResult.hasErrors()) {

			redirectAttributes.addFlashAttribute("reservation", reservationDTO);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.reservation",
					bindingResult);
			redirectAttributes.addFlashAttribute("hasErrors", true);

			return "redirect:/attractions/{title}/details";
		}

		this.reservationService.makeReservation(reservationDTO, attractionTitle);

		return "redirect:/attractions/show-all";
	}

	@GetMapping("/user-reservations")
	public String showUserReservations(Model model) {

		List<UserReservationView> reservations = this.reservationService.getUserReservations();

		model.addAttribute("missingAttractions", false);

		if (reservations != null) {
			BigDecimal allAttractionsSum = this.reservationService.getAllAttractionsTotalSum(reservations);

			model.addAttribute("reservations", reservations);
			model.addAttribute("allAttractionsSum", allAttractionsSum);

			return "user/user_reservations";

		}
		
		model.addAttribute("missingAttractions", true);

		return "user/user_reservations";
	}
	
	@DeleteMapping("/remove/{id}")
	public String deleteReservation(@PathVariable("id") Long id) {
		this.reservationService.deleteReservation(id);
		return "redirect:/reservation/user-reservations";
	}

}
