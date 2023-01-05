package com.romanredziuk.spring.online_store.persistence.dao;

import com.romanredziuk.spring.online_store.persistence.dto.RoleDto;

public interface RoleDao {

	RoleDto getRoleById(int id);

	RoleDto getRoleByRoleName(String roleName);

}
