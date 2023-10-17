package com.satff.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.satff.entity.Staff;
import com.satff.exception.StaffNotFoundException;
import com.satff.repository.StaffRepository;

class StaffServiceTest {

	@Mock
	private StaffRepository staffRepository;

	@InjectMocks
	private StaffService staffService;

	private static Staff staff;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		staff = new Staff();
		staff.setStaffEmailId("Saurabh@gmail.com");
		staff.setStaffName("Saurabh");
		staff.setStaffAddress("Mumbai");
		staff.setStaffRole("Manager");
		staff.setStaffSalary(5000);

	}

	@Test
	void testAddStaff() {
		when(staffRepository.save(staff)).thenReturn(staff);
		Staff staffResult = staffService.addStaff(staff);
		assertEquals(staff, staffResult);
	}

	@Test
	void testUpdateStaff() throws StaffNotFoundException {
		Staff updatedStaff = new Staff();
		updatedStaff.setStaffEmailId("Saurabh@gmail.com");
		updatedStaff.setStaffName("Saurabh");
		updatedStaff.setStaffAddress("Nagpur");
		updatedStaff.setStaffRole("Receptionist");
		updatedStaff.setStaffSalary(5000);
		when(staffRepository.findById("Saurabh@gmail.com")).thenReturn(Optional.of(staff));
		when(staffRepository.save(updatedStaff)).thenReturn(updatedStaff);
		Staff s = staffService.updateStaff(updatedStaff);
		assertEquals("Receptionist", s.getStaffRole());
		assertEquals("Nagpur", s.getStaffAddress());
	}

	@Test
	void testUpdateStaffById() throws StaffNotFoundException {
		when(staffRepository.findById("Saurabh@gmail.com")).thenReturn(Optional.of(staff));
		ResponseEntity<Staff> staffResponse = staffService.updateStaffById("Saurabh@gmail.com", 6000);
		assertEquals(6000, staffResponse.getBody().getStaffSalary());
		assertEquals(200, staffResponse.getStatusCodeValue());
	}

	@Test
	void testGetAllStaff() {
		List<Staff> staffList = new ArrayList<>(List.of(staff));
		when(staffRepository.findAll()).thenReturn(staffList);
		List<Staff> s = staffService.getAllStaff();
		assertEquals(staffList.size(), s.size());
	}

	@Test
	void testGetStaffById() throws StaffNotFoundException {
		 when(staffRepository.findById("Saurabh@gmail.com")).thenReturn(Optional.of(staff));
	        Staff s = staffService.getStaffById("Saurabh@gmail.com");
	        assertEquals(staff, s);
	}

	@Test
	void testDeleteStaff() throws StaffNotFoundException {
		when(staffRepository.findById("Saurabh@gmail.com")).thenReturn(Optional.of(staff));
        staffService.deleteStaff("Saurabh@gmail.com");
	}

	@Test
	void testGetStaffById_StaffNotFound() throws StaffNotFoundException {
        when(staffRepository.findById("ABC@gmail.com")).thenReturn(null);
        assertThrows(StaffNotFoundException.class, () -> staffService.getStaffById("ABC@gmail.com"));
    }

	@Test
	void testDeletStaff_StaffNotFound() throws StaffNotFoundException{
        when(staffRepository.findById("ABC@gmail.com")).thenReturn(null);
        assertThrows(StaffNotFoundException.class, () -> staffService.getStaffById("ABC@gmail.com"));
    }

}
