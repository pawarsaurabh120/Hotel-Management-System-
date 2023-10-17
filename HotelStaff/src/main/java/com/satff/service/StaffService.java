package com.satff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.satff.entity.Staff;
import com.satff.exception.StaffNotFoundException;
import com.satff.repository.StaffRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Service
public class StaffService implements IStaffService {

	@Autowired
	private StaffRepository staffRepository;

	@Override
	public Staff addStaff(@RequestBody Staff staff) {
		return staffRepository.save(staff);
	}

	@Override
	public Staff updateStaff(Staff staff) throws StaffNotFoundException {
		staffRepository.findById(staff.getStaffEmailId())
				.orElseThrow(() -> new StaffNotFoundException("Staff not found"));
		return staffRepository.save(staff);
	}

	@Override
	public ResponseEntity<Staff> updateStaffById(String staffEmailId, double staffSalary)
			throws StaffNotFoundException {
		Staff s1 = staffRepository.findById(staffEmailId).orElseThrow(() -> new StaffNotFoundException("Staff not found"));
		s1.setStaffSalary(staffSalary);
		staffRepository.save(s1);
		return ResponseEntity.ok(s1);
	}

	@Override
	public List<Staff> getAllStaff() {
		return staffRepository.findAll();
	}

	@Override
	public Staff getStaffById(String staffEmailId) throws StaffNotFoundException {
		try {
			return staffRepository.findById(staffEmailId).get();
		} catch (Exception e) {
			throw new StaffNotFoundException("Staff not found");
		}
	}

	@Override
	public void deleteStaff(String staffEmailId) throws StaffNotFoundException {
		staffRepository.findById(staffEmailId).orElseThrow(() -> new StaffNotFoundException("Staff not found"));
		staffRepository.deleteById(staffEmailId);
	}

}
