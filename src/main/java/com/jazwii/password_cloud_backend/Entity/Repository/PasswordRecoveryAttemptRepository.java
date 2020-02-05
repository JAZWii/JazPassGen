package com.jazwii.password_cloud_backend.Entity.Repository;

import com.jazwii.password_cloud_backend.Entity.Model.Account;
import com.jazwii.password_cloud_backend.Entity.Model.PasswordRecoveryAttempt;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by root on 17/07/17.
 */

@Repository
public interface PasswordRecoveryAttemptRepository extends CrudRepository<PasswordRecoveryAttempt, Long> {
    @Transactional
    @Modifying
    @Query("update PasswordRecoveryAttempt r set r.removed = true where r.removed = false and r.creator = :creator")
    void removeAllByCreator(@Param("creator") Account creator);

    PasswordRecoveryAttempt findByCreatorAndRemovedIsFalse(Account creator);
}

