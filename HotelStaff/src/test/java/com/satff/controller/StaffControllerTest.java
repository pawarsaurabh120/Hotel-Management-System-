package com.satff.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.satff.entity.Staff;
import com.satff.exception.StaffNotFoundException;
import com.satff.service.IStaffService;

class StaffControllerTest {

	@Mock
	private IStaffService iStaffService;

	@InjectMocks
	private StaffController staffController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testAddStaff() {
		Staff staff = new Staff();
		when(iStaffService.addStaff(staff)).thenReturn(staff);
		Staff staff1 = staffController.addStaff(staff);
		assertEquals(staff, staff1);
	}

	@Test
	void testUpdateStaff() throws StaffNotFoundException {
		Staff staff = new Staff();
		when(iStaffService.updateStaff(staff)).thenReturn(staff);
		Staff updatedStaff = staffController.updateStaff(staff);
		assertEquals(staff, updatedStaff);
	}

	@Test
	void testUpdateStaffById() throws StaffNotFoundException {
		String staffEmailId = "Saurabh@gmail.com";
		double staffSalary = 1000.0;
		ResponseEntity<Staff> responseEntity = staffController.updateStaffById(staffEmailId, staffSalary);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	void testGetAllStaff() {
		List<Staff> staffList = new ArrayList<>();
		when(iStaffService.getAllStaff()).thenReturn(staffList);
		List<Staff> staffList1 = staffController.getAllStaff();
		assertEquals(staffList, staffList1);
	}

	@Test
	void testGetStaffById() throws StaffNotFoundException {
		String staffEmailId = "Saurabh@gmail.com";
		Staff staff = new Staff();
		when(iStaffService.getStaffById(staffEmailId)).thenReturn(staff);
		Staff staff1 = staffController.getStaffById(staffEmailId);
		assertEquals(staff, staff1);
	}

	@Test
	void testDeleteStaff() throws StaffNotFoundException {
		String staffEmailId = "example@example.com";
		staffController.deleteStaff(staffEmailId);
	}

}
