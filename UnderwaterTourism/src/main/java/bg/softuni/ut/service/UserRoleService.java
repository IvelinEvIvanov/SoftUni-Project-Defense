package bg.softuni.ut.service;

import bg.softuni.ut.model.entity.UserRoleEntity;
import bg.softuni.ut.model.entity.enums.UserRoleEnum;

public interface UserRoleService {
	
	UserRoleEntity findByRole(UserRoleEnum role);
}
