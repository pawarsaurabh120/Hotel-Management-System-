package com.satff.service;

import java.util.List;
import org.springframework.http.ResponseEntity;

import com.satff.entity.Staff;
import com.satff.exception.StaffNotFoundException;

public interface IStaffService {
	
	public Staff addStaff(Staff staff);
	
	public Staff updateStaff(Staff staff) throws StaffNotFoundException;
	
	public ResponseEntity<Staff> updateStaffById(String staffEmailId, double staffSalary) throws StaffNotFoundException;
	
	public List<Staff> getAllStaff();
	
	public Staff getStaffById(String staffEmailId) throws StaffNotFoundException;
	
	public void deleteStaff(String staffEmailId) throws StaffNotFoundException;
	
}
