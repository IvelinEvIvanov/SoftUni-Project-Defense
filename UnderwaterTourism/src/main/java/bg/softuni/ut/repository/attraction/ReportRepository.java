package bg.softuni.ut.repository.attraction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bg.softuni.ut.model.entity.ReportEntity;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Long> {

}
