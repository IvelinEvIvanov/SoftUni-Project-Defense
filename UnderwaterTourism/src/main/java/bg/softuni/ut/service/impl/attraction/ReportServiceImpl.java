package bg.softuni.ut.service.impl.attraction;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import bg.softuni.ut.model.dto.ReportDTO;
import bg.softuni.ut.model.entity.ReportEntity;
import bg.softuni.ut.repository.attraction.ReportRepository;
import bg.softuni.ut.service.attraction.ReportService;
import bg.softuni.ut.service.attraction.ReservationService;

@Service
public class ReportServiceImpl implements ReportService {

	private final ReportRepository reportRepository;
	private final ReservationService reservationService;
	private final ModelMapper modelMapper;

	public ReportServiceImpl(ReservationService reservationService, ModelMapper modelMapper,
			ReportRepository reportRepository) {
		
		this.reportRepository = reportRepository;
		this.reservationService = reservationService;
		this.modelMapper = modelMapper;
	}

	@Override
	public void createReport(ReportDTO reportDTO) {
		ReportEntity newReport = this.modelMapper.map(reportDTO, ReportEntity.class);
		this.reportRepository.save(newReport);
	}
}
