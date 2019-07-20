package com.jazwii.jazpassgen.Controllers.v1.Authenticated;

import com.fasterxml.jackson.annotation.JsonView;
import com.jazwii.jazpassgen.Entity.Model.Account;
import com.jazwii.jazpassgen.Entity.Model.Address;
import com.jazwii.jazpassgen.Exception.Exception.CommonException;
import com.jazwii.jazpassgen.Pojo.FormData.FormAddress;
import com.jazwii.jazpassgen.Pojo.ResponseData.Authenticated.AddressListResponse;
import com.jazwii.jazpassgen.Pojo.ResponseData.Authenticated.AddressResponse;
import com.jazwii.jazpassgen.Services.AddressService;
import com.jazwii.jazpassgen.Singleton.RestViews;
import com.jazwii.jazpassgen.Singleton.RouteConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = RouteConstants.AUTHENTICATED_ADDRESS)
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping(value = "/addresses", name = "get_addresses")
    @JsonView(RestViews.AddressPublicMinimal.class)
    public AddressListResponse getAddresses(@AuthenticationPrincipal Account account) {
        return new AddressListResponse(addressService.getAddresses(account));
    }

    @GetMapping(value = "/address/{id}", name = "get_address_information")
    @JsonView(RestViews.AddressPublicDetailed.class)
    public AddressResponse getAddressInformation(@AuthenticationPrincipal Account account, @PathVariable("id") int id) {
        //throws an exception if address does not exist
        Address address = addressService.getAddressById(id);
        return new AddressResponse(address);
    }

    @PostMapping(value = "/address", name = "create_address")
    @JsonView(RestViews.AddressPublicDetailed.class)
    public AddressResponse createAddress(@AuthenticationPrincipal Account account, @Valid @RequestBody FormAddress form, Errors errors) {
        //validate the form sent by client
        if (errors.hasErrors()) {
            throw new CommonException(
                    HttpStatus.BAD_REQUEST,
                    CommonException.Type.COMMON_EXCEPTION_INVALID_FORM,
                    errors
            );
        }
        //create address, set data and save it
        Address address = addressService.createAddress(form, account);
        return getAddressInformation(account, address.getId());
    }

    @PutMapping(value = "/address/{id}", name = "update_address")
    @JsonView(RestViews.AddressPublicDetailed.class)
    public AddressResponse updateAddress(@AuthenticationPrincipal Account account, @PathVariable("id") int id, @Valid @RequestBody FormAddress form, Errors errors) {
        //validate the form sent by client
        if (errors.hasErrors()) {
            throw new CommonException(
                    HttpStatus.BAD_REQUEST,
                    CommonException.Type.COMMON_EXCEPTION_INVALID_FORM,
                    errors
            );
        }

        //throws an exception if address does not exist
        Address address = addressService.getAddressById(id);
        Address newAddress;
        //remove the address, and then re-create a new one
        try {
            addressService.removeAddress(address.getId(), account);
            newAddress = addressService.createAddress(form, account);
        } catch (Exception ex) {
            addressService.restoreAddress(address.getId(), account);
            throw ex;
        }
        //update address information and save it
        return getAddressInformation(account, newAddress.getId());
    }

    @DeleteMapping(value = "/address/{id}", name = "delete_address")
    @JsonView(RestViews.AddressPublicDetailed.class)
    public AddressResponse deleteAddress(@AuthenticationPrincipal Account account, @PathVariable("id") int id) {
        //throws an exception if address does not exist
        return new AddressResponse(addressService.removeAddress(id, account));
    }
}