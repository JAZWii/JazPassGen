package com.jazwii.jazpassgen.Entity.Repository;

import com.jazwii.jazpassgen.Entity.Model.Account;
import com.jazwii.jazpassgen.Entity.Model.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepository extends CrudRepository<Login, Long> {
    Login findById(int id);

    Login findByUsername(String username);

    Login findByLoginName(String loginName);

    Login findByWebsite(String website);

    Login findByEmail(String email);

    Login findFirstByLoginName(String email);

    List<Login> findAll();

    List<Login> findAllByAccountAndRemovedIsFalse(Account account);

    List<Login> findAllByAccount(Account account);

    Login findByIdAndRemovedIsFalse(int loginId);
}