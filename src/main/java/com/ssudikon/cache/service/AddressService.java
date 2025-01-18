package com.ssudikon.cache.service;

import com.ssudikon.cache.entity.Address;
import com.ssudikon.cache.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;


    public void saveAddress(Address address) {
        addressRepository.save(address);
    }

    public Address getAddress(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

}
