package bg.softuni.ut.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bg.softuni.ut.model.entity.UserEntity;
import bg.softuni.ut.model.entity.UserRoleEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	void deleteById(Long id);

	Optional<UserEntity> findById(Long id);

	UserEntity findByEmail(String email);

	Optional<UserEntity> findByEmailIgnoreCase(String email);

	List<UserEntity> findAll();

	// @Query("SELECT user FROM UserEntity user WHERE ?1 MEMBER OF user.roles")
	// List<UserEntity> findAllManagers(UserRoleEntity role);

	// @Query("SELECT user FROM UserEntity user WHERE 4 MEMBER OF user.roles")
	// List<UserEntity> findAllManagers(UserRoleEntity role);

	@Query("SELECT user FROM UserEntity user"
			+ " JOIN user.roles userRoleEntity"
			+ " WHERE userRoleEntity.role IN('MANAGER')")
	List<UserEntity> findAllManagers();
}
