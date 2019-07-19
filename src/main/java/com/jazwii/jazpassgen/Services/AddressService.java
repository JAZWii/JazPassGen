package com.jazwii.jazpassgen.Services;

import com.google.common.collect.Lists;
import com.jazwii.jazpassgen.Entity.Model.Account;
import com.jazwii.jazpassgen.Entity.Model.Address;
import com.jazwii.jazpassgen.Entity.Repository.AddressRepository;
import com.jazwii.jazpassgen.Exception.Exception.CommonException;
import com.jazwii.jazpassgen.Pojo.FormData.FormAddress;
import com.jazwii.jazpassgen.Singleton.MessageConstants;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Primary
@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAddresses(Account account) {
        return addressRepository.findAllByAccountAndRemovedIsFalse(account);
    }

    public List<Address> getAddresssAdmin(Account account) {
        return addressRepository.findAllByAccount(account);
    }

    public List<Address> getAddresssAll() {
        return Lists.newArrayList(addressRepository.findAll().iterator());
    }

    public Address getAddressById(int addressId) {
        Address address = addressRepository.findByIdAndRemovedIsFalse(addressId);

        //throw an exception if address was not found
        if (address == null) {
            throw new CommonException(
                    HttpStatus.BAD_REQUEST,
                    CommonException.Type.COMMON_EXCEPTION_INVALID_FORM,
                    MessageConstants.INVALID_LOGIN
            );
        }

        return address;
    }

    public Address updateAddress(Address address, FormAddress form, Account account) {
        //check if code already exist
        Address duplicateAddress = addressRepository.findFirstByAddressNameAndRemovedIsFalse(form.getAddressName());
        if (duplicateAddress != null && address.getId() != duplicateAddress.getId()) {
            throw new CommonException(
                    HttpStatus.BAD_REQUEST,
                    CommonException.Type.COMMON_EXCEPTION_INVALID_FORM,
                    MessageConstants.DUPLICATE_LOGIN
            );
        }

        return address;
    }

    public Address createAddress(FormAddress form, Account account) {
        //create an empty address and update it by uy passing the form to update address method
        return createAddress(form, account);
    }

    public Address removeAddress(int addressId, Account account) {
        Address address = addressRepository.findByIdAndRemovedIsFalse(addressId);
        return removeAddress(address, account);
    }

    public Address restoreAddress(int addressId, Account account) {
        Address address = addressRepository.findById(addressId);

        //show exception if target address does not exist or is removed already
        if (address == null) {
            throw new CommonException(
                    HttpStatus.BAD_REQUEST,
                    CommonException.Type.COMMON_EXCEPTION_INVALID_FORM,
                    MessageConstants.INVALID_LOGIN
            );
        }

        //update address's information and store its current code to former
        //storing former code is helpful to avoid duplicates in case a new record is added
        //with the same code of a removed address and to restore address later if needed
        address.setAddressName(address.getFormerAddressName());
        address.setFormerAddressName(null);

        //set removed flag and remove data and the remover account
        address.setRemoved(false);
        address.setRemovedBy(null);
        address.setRemoveDate(null);

        //save address and return it
        save(address);
        return address;
    }

    public void save(Address address) {
        save(address, false, true);
    }

    public void save(Address address, boolean recursiveSave, boolean deleteDependant) {
        addressRepository.save(address);
    }

    private Address removeAddress(Address address, Account account) {
        String generatedString = RandomStringUtils.randomAlphanumeric(150);

        //show exception if target address does not exist or is removed already
        if (address == null) {
            throw new CommonException(
                    HttpStatus.BAD_REQUEST,
                    CommonException.Type.COMMON_EXCEPTION_INVALID_FORM,
                    MessageConstants.INVALID_LOGIN
            );
        }
        //update address's information and store its current code to former
        //storing former code is helpful to avoid duplicates in case a new record is added
        //with the same code of a removed address and to restore address later if needed
        address.setFormerAddressName(address.getAddressName());
        address.setAddressName(generatedString);

        //set removed flag and remove data and the remover account
        address.setRemoved(true);
        address.setRemovedBy(account);
        address.setRemoveDate(new Date());

        //save address and return it
        save(address);
        return address;
    }

    private Address removeAddressIfExists(String AddressName, Account account) {
        Address address = addressRepository.findFirstByAddressNameAndRemovedIsFalse(AddressName);
        if (address != null)
            return removeAddress(address, account);
        return null;
    }
}
