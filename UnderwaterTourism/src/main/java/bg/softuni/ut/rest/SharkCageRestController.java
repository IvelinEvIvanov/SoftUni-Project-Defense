package bg.softuni.ut.rest;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.softuni.ut.model.entity.AttractionEntity;
import bg.softuni.ut.service.attraction.AttractionService;

@RestController
@RequestMapping("/rest/shark-cage")
public class SharkCageRestController {

	private final AttractionService attractionService;
	private final ModelMapper modelMapper;

	public SharkCageRestController(AttractionService attractionService, ModelMapper modelMapper) {
		this.attractionService = attractionService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/details")
	public ResponseEntity<SharkCageDTO> showSharkCageInfo() {

		AttractionEntity sharkCage = this.attractionService.findAttractionByTitle("Shark Cage Dive");
		if (sharkCage == null) {
			return ResponseEntity.notFound().build();
		}

		SharkCageDTO sharkCageDTO = this.modelMapper.map(sharkCage, SharkCageDTO.class);

		return ResponseEntity.ok(sharkCageDTO);
	}
}
