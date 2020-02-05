package com.jazwii.password_cloud_backend.Services;

import com.jazwii.password_cloud_backend.Entity.Model.Account;
import com.jazwii.password_cloud_backend.Entity.Model.Role;
import com.jazwii.password_cloud_backend.Entity.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public void save(Role role) {
        roleRepository.save(role);
    }
    public void save(List<Role> roles) {
        roleRepository.save(roles);
    }

    public List<Role> addAuthenticatedRole(Account account) {
        Role role = roleRepository.findByCode(Role.ROLE_TYPE.AUTHENTICATED.getValue());
        if(role == null){
            role = new Role(Role.ROLE_TYPE.AUTHENTICATED.getValue(), Role.ROLE_TYPE.AUTHENTICATED.getValue());
            save(role);
        }

        List<Role> roles = new ArrayList<>();
        roles.add(role);

        account.setRoles(roles);
        this.save(roles);

        return roles;
    }

    public List<Role> addAdministratorRole(Account account) {
        Role role = roleRepository.findByCode(Role.ROLE_TYPE.ADMINISTRATOR.getValue());
        if(role == null){
            role = new Role(Role.ROLE_TYPE.ADMINISTRATOR.getValue(), Role.ROLE_TYPE.ADMINISTRATOR.getValue());
            save(role);
        }

        List<Role> roles = new ArrayList<>();
        roles.add(role);

        account.setRoles(roles);
        this.save(roles);

        return roles;
    }
}
