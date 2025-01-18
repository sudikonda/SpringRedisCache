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
    public ResponseEntity<Address> getAddress(@PathVariable Long id) {
        Address address = addressService.getAddress(id);
        return ResponseEntity.ok().body(address);
    }

    @GetMapping("/addresses")
    @Cacheable(value = "addresses")
    public ResponseEntity<Iterable<Address>> getAddresses() {
        Iterable<Address> addresses = addressService.getAllAddresses();
        return ResponseEntity.ok().body(addresses);
    }

    @PostMapping("/address")
    @CacheEvict(value = "addresses", allEntries = true)
    public ResponseEntity<String> createAddress(@RequestBody Address address) {
        try {
            log.info("Creating address: {}", address);
            addressService.saveAddress(address);
            return ResponseEntity.ok().body("Address created successfully");
        } catch (Exception e) {
            log.error("Failed to create address: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Failed to create address: " + e.getMessage());
        }
    }

}
