
package com.satff.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satff.awthrequest.AuthRequest;
import com.satff.entity.Staff;
import com.satff.exception.StaffNotFoundException;
import com.satff.jwt.JwtUtil;
import com.satff.service.IStaffService;
import com.satff.service.StaffService;

@RestController
@RequestMapping("/hotel")
@CrossOrigin("*")
public class StaffController {

	@Autowired
	private IStaffService iStaffService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/staff/add")
	public ResponseEntity<Object> addUser(@RequestBody Staff staff) {
		Staff newUser = iStaffService.addStaff(staff);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

	@PutMapping("/staff/update")
	public Staff updateStaff(@RequestBody Staff staff) throws StaffNotFoundException {
		return iStaffService.updateStaff(staff);
	}

	@PutMapping("/staff/{username}/{salary}")
	public ResponseEntity<Staff> updateStaffById(@PathVariable String username, @PathVariable double salary)
			throws StaffNotFoundException {
		iStaffService.updateStaffById(username, salary);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/staff/getAll")
	public List<Staff> getAllStaff() {
		return iStaffService.getAllStaff();
	}

	@GetMapping("/staff/{username}")
	public Staff getStaffById(@PathVariable String username) throws StaffNotFoundException {
		return iStaffService.getStaffById(username);
	}

	@DeleteMapping("/staff/delete/{id}")
	public void deleteStaff(@PathVariable long id) throws StaffNotFoundException {
		iStaffService.deleteStaff(id);
	}

//Security Controller
	@PostMapping("/staff/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (Exception e) {
			throw new Exception("Invalid Username or Password.");
		}
		return jwtUtil.generateToken(authRequest.getUsername());
	}

	@GetMapping("/staff/login/{username}/{password}")
	@PreAuthorize("hasAnyRole('OWNER','MANAGER','RECEPTIONIST')")
	public ResponseEntity<?> Login(@PathVariable String username, @PathVariable String password)
			throws StaffNotFoundException {
		Staff staff = iStaffService.logIn(username, password);
		return new ResponseEntity<>(staff, HttpStatus.OK);

	}

	
}
