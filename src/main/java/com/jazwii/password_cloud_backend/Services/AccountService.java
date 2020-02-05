package com.jazwii.password_cloud_backend.Services;

import com.jazwii.password_cloud_backend.Entity.Model.Account;
import com.jazwii.password_cloud_backend.Entity.Model.Role;
import com.jazwii.password_cloud_backend.Entity.Repository.AccountRepository;
import com.jazwii.password_cloud_backend.Exception.Exception.CommonException;
import com.jazwii.password_cloud_backend.Pojo.FormData.FormAccount;
import com.jazwii.password_cloud_backend.Security.Validator.Account.AccountBeforeCreateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.Collection;

@Primary
@Service("userDetailsService")
public class AccountService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AccountBeforeCreateValidator accountBeforeCreateValidator;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);

        if (account == null) {
            // account not found
            throw new UsernameNotFoundException("account_not_found");
        }

        if (account.getRoles() == null || account.getRoles().isEmpty()) {
            // No Roles assigned to user...
            throw new UsernameNotFoundException("no_account_roles");
        }


        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for (Role role : account.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getCode()));
        }

        User userDetails = new User(account.getUsername(),
                account.getPassword(), account.isEnabled(),
                account.isAccountNonExpired(), account.isCredentialsNonExpired(),
                account.isAccountNonLocked(), grantedAuthorities);


        return account;
    }

    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public Account getAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public void beforeCreateValidate(Account account) {
        Errors errors = new BeanPropertyBindingResult(account, "account-before-create");
        accountBeforeCreateValidator.validate(account, errors);
        if (errors.hasErrors()) {
            throw new CommonException(
                    HttpStatus.BAD_REQUEST,
                    CommonException.Type.COMMON_EXCEPTION_LOGICAL_ERROR,
                    errors
            );
        }
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public Account createAccount(FormAccount form) {
        return createAccount(form, true);
    }

    public Account createAdminAccount(FormAccount form) {
        return createAdminAccount(form, true);
    }

    public Account createAccount(FormAccount form, boolean save) {
        Account account = new Account();
        account.setEmail(form.getEmail());
        account.setUsername(form.getEmail());
        account.setEnabled(1);
        account.setPlainPassword(form.getPassword());
        beforeCreateValidate(account);

        roleService.addAuthenticatedRole(account);

        if(save)
            save(account);
        return account;
    }

    public Account createAdminAccount(FormAccount form, boolean save) {
        Account account = new Account();
        account.setEmail(form.getEmail());
        account.setUsername(form.getEmail());
        account.setEnabled(1);
        account.setPlainPassword(form.getPassword());
        beforeCreateValidate(account);

        roleService.addAuthenticatedRole(account);
        roleService.addAdministratorRole(account);

        if(save)
            save(account);
        return account;
    }
}
