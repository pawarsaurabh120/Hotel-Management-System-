
package com.satff.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satff.entity.Staff;
import com.satff.exception.StaffNotFoundException;
import com.satff.service.IStaffService;

@RestController
@RequestMapping("/hotel")
@CrossOrigin("http://localhost:3000")
public class StaffController {
	
	@Autowired
	private IStaffService iStaffService;
	
	@PostMapping("/staff")
	public Staff addStaff(@RequestBody Staff staff) {
		return iStaffService.addStaff(staff);
	}
	
	@PutMapping("/staff")
	public Staff updateStaff(@RequestBody Staff staff) throws StaffNotFoundException{
		return iStaffService.updateStaff(staff);
	}
	
	@PutMapping("/staff/{staffEmailId}/{staffSalary}")
	public ResponseEntity<Staff> updateStaffById(@PathVariable String staffEmailId, @PathVariable double staffSalary) throws StaffNotFoundException{
		iStaffService.updateStaffById(staffEmailId, staffSalary);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/staff")
	public List<Staff> getAllStaff(){
		return iStaffService.getAllStaff();
	}
	
	@GetMapping("/staff/{staffEmailId}")
	public Staff getStaffById(@PathVariable String staffEmailId) throws StaffNotFoundException{
		return iStaffService.getStaffById(staffEmailId);
	}
	
	@DeleteMapping("/staff/{staffEmailId}")
	public void deleteStaff(@PathVariable String staffEmailId) throws StaffNotFoundException {
		iStaffService.deleteStaff(staffEmailId);
	}
}
