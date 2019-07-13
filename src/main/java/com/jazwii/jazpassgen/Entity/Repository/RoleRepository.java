package com.jazwii.jazpassgen.Entity.Repository;

import com.jazwii.jazpassgen.Entity.Model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findById(int id);
    Role findByCode(String code);
}