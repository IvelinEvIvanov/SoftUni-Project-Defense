package bg.softuni.ut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bg.softuni.ut.model.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
	
	EmployeeEntity findByUserId(Long id);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM EmployeeEntity empl WHERE empl.id=?1")
	void deleteEmployeeById(Long id);
	
}
