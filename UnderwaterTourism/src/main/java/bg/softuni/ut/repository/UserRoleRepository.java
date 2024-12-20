package bg.softuni.ut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bg.softuni.ut.model.entity.UserRoleEntity;
import bg.softuni.ut.model.entity.enums.UserRoleEnum;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long>{
	
	UserRoleEntity findByRole(UserRoleEnum role);
}
