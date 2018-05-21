package fr.esgi.reporitories.users.adapter;

import entities.Role;
import entities.User;
import fr.esgi.reporitories.users.dao.TRoleEntity;
import fr.esgi.reporitories.users.dao.TUserEntity;

public class RoleDataAdapter {
    public Role entityToModel(TRoleEntity roleEntity){
        Role role = new Role();
        role.setId(roleEntity.getId());
        role.setName(roleEntity.getName());
        return role;
    }

    public TRoleEntity modelToEntity(Role role){
        TRoleEntity roleEntity = new TRoleEntity();
        if(null != role.getId()){
            roleEntity.setId(role.getId());
        }
        roleEntity.setName(role.getName());
        return roleEntity;
    }
}
