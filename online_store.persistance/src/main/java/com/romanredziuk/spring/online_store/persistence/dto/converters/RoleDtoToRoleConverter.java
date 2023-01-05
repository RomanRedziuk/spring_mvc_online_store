package com.romanredziuk.spring.online_store.persistence.dto.converters;

import org.springframework.stereotype.Service;

import com.romanredziuk.spring.online_store.persistence.dto.RoleDto;

@Service
public class RoleDtoToRoleConverter {

	public RoleDto convertRoleNameToRoleDtoWithOnlyRoleName(String roleName) {
		RoleDto roleDto = new RoleDto();
		roleDto.setRoleName(roleName);
		return roleDto;
	}

}
