package com.satff.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.satff.entity.Staff;
import com.satff.exception.StaffNotFoundException;
import com.satff.repository.StaffRepository;

@Service
public class StaffService implements UserDetailsService, IStaffService {

	@Autowired
	private StaffRepository staffRepository;

    @Override
	public Staff addStaff(Staff staff) {
		return staffRepository.save(staff);
	}

    @Override
	public Staff updateStaff(Staff staff) throws StaffNotFoundException {
		staffRepository.findByUsername(staff.getUsername())
				.orElseThrow(() -> new StaffNotFoundException("Staff not found"));
		return staffRepository.save(staff);
	}

    @Override
	public ResponseEntity<Staff> updateStaffById(String username, double salary)
			throws StaffNotFoundException {
		Staff s1 = staffRepository.findByUsername(username).orElseThrow(() -> new StaffNotFoundException("Staff not found"));
		s1.setSalary(salary);
		staffRepository.save(s1);
		return ResponseEntity.ok(s1);
	}

    @Override
	public List<Staff> getAllStaff() {
		return staffRepository.findAll();
	}

    @Override
	public Staff getStaffById(String username) throws StaffNotFoundException {
		try {
			return staffRepository.findByUsername(username).get();
		} catch (Exception e) {
			throw new StaffNotFoundException("Staff not found");
		}
	}

    @Override
	public void deleteStaff(long id) throws StaffNotFoundException {
		staffRepository.findById(id).orElseThrow(() -> new StaffNotFoundException("Staff not found"));
		staffRepository.deleteById(id);
	}
	
	
//Security
    @Override
	public Staff logIn(String username, String password) throws StaffNotFoundException {

		Staff staffObj= staffRepository.findByUsername(username)
		.orElseThrow(() -> new StaffNotFoundException("Staff not found"));

		if (!staffObj.getPassword().equals(password)) {
			throw new RuntimeException("Invalid password or username");
		}
		return staffObj;

	}

    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Staff staff = staffRepository.findByUsername(username).get();
		return new org.springframework.security.core.userdetails.User(staff.getUsername(), staff.getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + staff.getRole().toString())));

	}


}
