package com.gevbagratunyan.school.controllers;

import com.gevbagratunyan.school.entity.data.AllMarks;
import com.gevbagratunyan.school.entity.models.Employee;
import com.gevbagratunyan.school.entity.models.Pupil;
import com.gevbagratunyan.school.entity.models.User;
import com.gevbagratunyan.school.service.services.UserService;
import com.gevbagratunyan.school.service.services.EmployeeService;
import com.gevbagratunyan.school.service.services.PupilService;
import com.gevbagratunyan.school.transfer.user.request.*;
import com.gevbagratunyan.school.transfer.user.response.UserResponse;
import com.gevbagratunyan.school.transfer.employee.EmployeeResponse;
import com.gevbagratunyan.school.transfer.pupil.PupilResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/api")
public class UserController {

	private final UserService userService;
	private final EmployeeService employeeService;
	private final PupilService pupilService;

	public UserController(UserService userService, EmployeeService employeeService, PupilService pupilService, UserService adminService) {
		this.userService = userService;
		this.employeeService = employeeService;
		this.pupilService = pupilService;
	}

    //DONE
    @GetMapping
    public String homePage() {
        return "Hi! You are on the Api Page";
    }

	@PostMapping("/create-profile")
	public UserResponse addUser(@Valid @RequestBody UserAddRequest userAddRequest) {
		return userService.add(userAddRequest);
	}

                   				 //*************************USER********************//


	@GetMapping("/admins/{id}")
	public UserResponse getAdmin(@PathVariable Long id) {
		return userService.get(id);
	}


	@PostMapping("/admins/create")
	public UserResponse addAdmin(@Valid @RequestBody UserAddRequest userAddRequest) {
		return userService.add(userAddRequest);
	}

	@DeleteMapping("/admins/delete/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/admins/get-all-users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User>  users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PutMapping("/users/update/{id}")
	public UserResponse updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest updateRequest) {
		return userService.update(id, updateRequest);
	}

	@GetMapping("/users/{id}")
	public UserResponse getUser(@PathVariable Long id) {
		return userService.get(id);
	}

								//*************************PUPIL********************//

	@GetMapping("/pupils/{id}")
	public PupilResponse getPupil(@PathVariable Long id) {
		return pupilService.get(id);
	}

	@PostMapping("pupils/create")
	public ResponseEntity<PupilResponse> createPupil(@Valid @RequestBody PupilCreateRequest pupilCreateRequest) {
		PupilResponse response = pupilService.add(pupilCreateRequest);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/pupils/set-year-mark/{id}")
	public ResponseEntity<Void> setYearMark(@PathVariable Long id, @Valid @RequestBody PupilSetMarkRequest setMarkRequest) {
		pupilService.setYearMark(id,setMarkRequest);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/pupils/set-all-year-marks/{id}")
	public ResponseEntity<Void> setAllYearMarks(@PathVariable Long id) {
		pupilService.setAllYearMarks(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/pupils/set-mark/{id}")
	public ResponseEntity<Void> setMark(@PathVariable Long id, @Valid @RequestBody PupilSetMarkRequest setMarkRequest) {
		pupilService.setMark(id,setMarkRequest);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/pupils/get-current-marks/{id}")
	public ResponseEntity<AllMarks> getCurrentMarks(@PathVariable Long id){
		AllMarks marks = pupilService.getCurrentMarks(id);
		return new ResponseEntity<>(marks,HttpStatus.OK);
	}

	@DeleteMapping("pupils/delete/{id}")
	public ResponseEntity<Void> deletePupil(@PathVariable Long id){
		pupilService.delete(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/pupils/all")
	public ResponseEntity<List<Pupil>> getAllPupils(){
		List<Pupil> pupils = pupilService.getAllPupils();
		return new ResponseEntity<>(pupils, HttpStatus.OK);
	}

	@PutMapping("/pupils/update/{id}")
	public PupilResponse updatePupil(@PathVariable Long id, @Valid @RequestBody PupilUpdateRequest updateRequest){
		return pupilService.update(id, updateRequest);
	}


	           //*************************EMPLOYEE********************//

	@GetMapping("/employees/{id}")
	public EmployeeResponse getEmployee(@PathVariable Long id) {
		return employeeService.get(id);
	}


	@GetMapping("/employees/all")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		return new ResponseEntity<>(employees, HttpStatus.OK);

//		return ResponseEntity.ok(employees);
	}

	@PostMapping("/employees/create")
	public EmployeeResponse createEmployee( @Valid @RequestBody EmployeeCreateRequest employeeCreateRequest) {
		return employeeService.add(employeeCreateRequest);
	}

	@DeleteMapping("/employees/delete/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		employeeService.delete(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("employees/update/{id}")
	public EmployeeResponse updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeUpdateRequest updateRequest) {
		return employeeService.update(id,updateRequest);
	}

	@PutMapping("/employees/banking/{id}")
	public EmployeeResponse updateBanking(@PathVariable Long id, @Valid @RequestBody EmployeeBankingUpdateRequest bankingUpdateRequest) {
		return employeeService.updateBanking(id, bankingUpdateRequest);
	}

}
