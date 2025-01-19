package com.ssudikon.cache.controller;

import com.ssudikon.cache.entity.Address;
import com.ssudikon.cache.service.AddressService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/address/{id}")
    public Address getAddress(@PathVariable Long id) {
        return addressService.getAddress(id);
    }

    @GetMapping("/addresses")
    @Cacheable(value = "addresses")
    public Iterable<Address> getAddresses() {
        return addressService.getAllAddresses();
    }

    @PostMapping("/address")
    @CacheEvict(value = "addresses", allEntries = true)
    public String createAddress(@RequestBody Address address) {
        try {
            log.info("Creating address: {}", address);
            addressService.saveAddress(address);
            return "Address created successfully";
        } catch (Exception e) {
            log.error("Failed to create address: {}", e.getMessage());
            return "Failed to create address: " + e.getMessage();
        }
    }

}
