package com.pharmacy.posSystem.Domain.Service;

import com.pharmacy.posSystem.Domain.Entity.EmployeeEntity;
import com.pharmacy.posSystem.External.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;

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
}
