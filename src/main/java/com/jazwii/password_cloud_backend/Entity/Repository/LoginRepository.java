package com.jazwii.password_cloud_backend.Entity.Repository;

        import com.jazwii.password_cloud_backend.Entity.Model.Account;
        import com.jazwii.password_cloud_backend.Entity.Model.Login;
        import org.springframework.stereotype.Repository;

        import java.util.List;

@Repository
public interface LoginRepository extends CustomRepository<Login, Integer> {
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