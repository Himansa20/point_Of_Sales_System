package com.pharmacy.posSystem.Domain.Service;

import com.pharmacy.posSystem.App.dto.EmployeeDTO;
import com.pharmacy.posSystem.App.dto.Response.EmployeGeneral;
import com.pharmacy.posSystem.Domain.Entity.EmployeeEntity;
import com.pharmacy.posSystem.External.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional

public class EmployeeService {
    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public ResponseEntity<EmployeeEntity> registerEmployee(EmployeeEntity employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setAddress(employee.getAddress());
        employeeEntity.setDob(employee.getDob());
        employeeEntity.setNic(employee.getNic());
        employeeEntity.setMobile(employee.getMobile());
        employeeEntity.setPassword(passwordEncoder.encode(employee.getPassword()));
        String username = generateUsername(employee.getFirstName(), employee.getLastName());
        employeeEntity.setUsername(username); //Generate Automatically Username
        employeeRepository.save(employeeEntity);
        return new ResponseEntity<>(employeeEntity, HttpStatus.OK);
    }

    private String generateUsername(String firstName, String lastName) {
        // Remove any whitespace and convert to lowercase
        String cleanFirst = firstName.replaceAll("\\s+", "").toLowerCase();
        String cleanLast = lastName.replaceAll("\\s+", "").toLowerCase();

        // Determine how many letters to take from each name
        int firstLength = cleanFirst.length();
        int lastLength = cleanLast.length();

        // Default to 3 letters from first name and 3 from last name
        int takeFromFirst = Math.min(3, firstLength);
        int takeFromLast = Math.min(3, lastLength);

        // If first name is shorter, take more from last name
        if (takeFromFirst < 3) {
            takeFromLast += (3 - takeFromFirst);
            takeFromLast = Math.min(takeFromLast, lastLength);
        }
        // If last name is shorter, take more from first name
        else if (takeFromLast < 3) {
            takeFromFirst += (3 - takeFromLast);
            takeFromFirst = Math.min(takeFromFirst, firstLength);
        }

        // Get the portions from each name
        String firstPart = cleanFirst.substring(0, takeFromFirst);
        String lastPart = cleanLast.substring(0, takeFromLast);

        // Combine them to make 6 characters
        String username = firstPart + lastPart;

        // If still less than 6 characters, pad with numbers
        if (username.length() < 6) {
            username += String.format("%0" + (6 - username.length()) + "d", 0);
        }

        return username;
    }

    //Get All Employees
    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employees = employeeRepository.findAll();
        return employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private EmployeeDTO convertToDTO(EmployeeEntity employee) {
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public ResponseEntity<EmployeGeneral> getEmplooye(Integer id) {
        EmployeGeneral employeGeneral = new EmployeGeneral();
        Optional<EmployeeEntity> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            EmployeeEntity employeeEntity = employee.get();
            employeGeneral.setEmp_id(employeeEntity.getId());
            employeGeneral.setFirstName(employeeEntity.getFirstName());
            employeGeneral.setLastName(employeeEntity.getLastName());
            employeGeneral.setAddress(employeeEntity.getAddress());
            employeGeneral.setDob(employeeEntity.getDob());
            employeGeneral.setNic(employeeEntity.getNic());
            employeGeneral.setMobile(employeeEntity.getMobile());
            ResponseEntity.ok(employeGeneral);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(employeGeneral);
    }


    public ResponseEntity<String> updateEmployee(EmployeGeneral employeGeneral) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(employeGeneral.getEmp_id());
        if(optionalEmployee.isPresent()){
            EmployeeEntity employe = optionalEmployee.get();
            employe.setFirstName(employeGeneral.getFirstName());
            employe.setLastName(employeGeneral.getLastName());
            employe.setAddress(employeGeneral.getAddress());
            employe.setMobile(employeGeneral.getMobile());
            employe.setDob(employeGeneral.getDob());
            employe.setNic(employeGeneral.getNic());
            if (employeGeneral.getPassword() != null && !employeGeneral.getPassword().isEmpty()) {
                employe.setPassword(passwordEncoder.encode(employeGeneral.getPassword())); // CORRECTED
            }
            employeeRepository.save(employe);
            return ResponseEntity.ok("Employee updated successfully");
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> deleteStudent(Integer id){
        Optional<EmployeeEntity> OptionalEmployee = employeeRepository.findById(id);
        if (OptionalEmployee.isPresent()){
            employeeRepository.delete(OptionalEmployee.get());
            return ResponseEntity.ok("Employee deleted successfully");
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
