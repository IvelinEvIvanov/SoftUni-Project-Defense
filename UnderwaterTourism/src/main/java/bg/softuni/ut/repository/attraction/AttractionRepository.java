package bg.softuni.ut.repository.attraction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bg.softuni.ut.model.entity.AttractionEntity;

@Repository
public interface AttractionRepository extends JpaRepository<AttractionEntity, Long>{

	AttractionEntity findByTitle(String title);
	
}
