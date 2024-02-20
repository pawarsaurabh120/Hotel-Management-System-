package com.satff.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.satff.entity.Staff;
import com.satff.exception.StaffNotFoundException;


@Service
public interface IStaffService {
	
	public Staff addStaff(Staff staff) throws StaffNotFoundException;
	
	public Staff updateStaff(Staff staff) throws StaffNotFoundException;
	
	public ResponseEntity<Staff> updateStaffById(String staffEmailId, double staffSalary) throws StaffNotFoundException;
	
	public List<Staff> getAllStaff();
	
	public Staff getStaffById(String staffEmailId) throws StaffNotFoundException;
	
	public void deleteStaff(long id) throws StaffNotFoundException;
	
	public Staff logIn(String staffEmailId, String staffPassword) throws StaffNotFoundException;
	
	
}
