package com.jazwii.password_cloud_backend.Entity.Repository;

import com.jazwii.password_cloud_backend.Entity.Model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findById(int id);
    Role findByCode(String code);
}