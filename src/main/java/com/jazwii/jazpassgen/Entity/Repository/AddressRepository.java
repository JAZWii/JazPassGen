package com.jazwii.jazpassgen.Entity.Repository;

import com.jazwii.jazpassgen.Entity.Model.Account;
import com.jazwii.jazpassgen.Entity.Model.Address;
import com.jazwii.jazpassgen.Entity.Model.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CustomRepository<Address, Integer> {
    Address findById(int id);

    Address findByIdAndRemovedIsFalse(int addressId);

    Address findByAddressName(String addressName);

    Address findByAddressNameAndRemovedIsFalse(String addressName);

    Address findByCountry(String website);

    Address findByCountryAndRemovedIsFalse(String website);

    Address findFirstByAddressNameAndRemovedIsFalse(String email);

    List<Address> findAll();

    List<Address> findAllByAccount(Account account);

    List<Address> findAllByAccountAndRemovedIsFalse(Account account);
}