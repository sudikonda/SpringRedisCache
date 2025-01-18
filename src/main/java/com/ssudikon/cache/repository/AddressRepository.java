package com.ssudikon.cache.repository;

import com.ssudikon.cache.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
