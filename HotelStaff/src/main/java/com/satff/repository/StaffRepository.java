package com.satff.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.satff.entity.Staff;

@Repository
public interface StaffRepository extends MongoRepository <Staff,Long> {
	
	Optional<Staff> findByUsername(String username);

}