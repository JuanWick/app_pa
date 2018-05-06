package fr.esgi.components.user.adapter;

import entities.Role;
import entities.User;
import fr.esgi.components.user.dto.RoleDto;
import fr.esgi.components.user.dto.UserDto;

public class RoleDtoAdapter {
    public Role convertToModel (RoleDto roleDto){
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        return role;
    }

    public RoleDto convertToDto(Role role){
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }
}
