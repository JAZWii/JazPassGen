package com.jazwii.password_cloud_backend.Entity.Repository;

import com.jazwii.password_cloud_backend.Entity.Model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findById(long id);

    Account findByUsername(String username);

    Account findByPhoneNumber(long phoneNumber);

    Account findByPhoneNumberAndEmail(long phoneNumber, String email);

    Account findByEmail(String email);
}