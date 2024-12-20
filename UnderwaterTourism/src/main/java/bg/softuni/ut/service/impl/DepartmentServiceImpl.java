package bg.softuni.ut.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import bg.softuni.ut.model.dto.DepartmentDTO;
import bg.softuni.ut.model.entity.DepartmentEntity;
import bg.softuni.ut.repository.DepartmentRepository;
import bg.softuni.ut.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository departmentRepository;
	private final ModelMapper modelMapper;

	public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {

		this.departmentRepository = departmentRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<DepartmentDTO> getAllDepartments() {

		List<DepartmentEntity> departmentEntities = this.departmentRepository.findAll();
		if (departmentEntities != null && !departmentEntities.isEmpty()) {

			List<DepartmentDTO> listDepartmentDTO = new ArrayList<>();
			for (DepartmentEntity itrDepartmentEntity : departmentEntities) {
				listDepartmentDTO.add(this.modelMapper.map(itrDepartmentEntity, DepartmentDTO.class));
			}

			return listDepartmentDTO;
		}

		return null;
	}

}
