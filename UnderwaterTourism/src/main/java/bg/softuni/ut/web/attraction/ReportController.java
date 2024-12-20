package bg.softuni.ut.web.attraction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bg.softuni.ut.model.dto.ReportDTO;
import bg.softuni.ut.model.entity.ReportEntity;
import bg.softuni.ut.repository.attraction.ReportRepository;
import bg.softuni.ut.service.attraction.ReportService;
import bg.softuni.ut.service.attraction.ReservationService;

@Controller
@RequestMapping("/report")
public class ReportController {

	private final ReservationService reservationService;
	private final ReportService reportService;
	private final ReportRepository reportRepository;

	public ReportController(ReservationService reservationService, ReportService reportService,
			ReportRepository reportRepository) {
		this.reservationService = reservationService;
		this.reportService = reportService;
		this.reportRepository = reportRepository;
	}

	@Scheduled(cron = "0 0 1 * * *")
	public void createReport() {

		LocalDate today = LocalDate.now();
		LocalDate previousMonth = LocalDate.now().minusMonths(1);

		BigDecimal income = this.reservationService.getMonthlyIncome(today, previousMonth);

		if (income != null) {
			ReportDTO reportDTO = new ReportDTO();
			reportDTO.setFromDate(previousMonth);
			reportDTO.setToDate(today);
			reportDTO.setIncome(income);

			this.reportService.createReport(reportDTO);
		}

	}

	@GetMapping("/test")
	public String showReport(Model model) {

		List<ReportEntity> reportEntities = this.reportRepository.findAll();

		model.addAttribute("reports", reportEntities);

		return "attraction/show_report";
	}
	
	@GetMapping("/add")
	public String addReport() {

		LocalDate today = LocalDate.now();
		LocalDate previousMonth = LocalDate.now().minusMonths(1);

		BigDecimal income = this.reservationService.getMonthlyIncome(today, previousMonth);

		if (income != null) {
			ReportDTO reportDTO = new ReportDTO();
			reportDTO.setFromDate(previousMonth);
			reportDTO.setToDate(today);
			reportDTO.setIncome(income);

			this.reportService.createReport(reportDTO);
		}
		
		return "redirect:/report/test";
	}
}
