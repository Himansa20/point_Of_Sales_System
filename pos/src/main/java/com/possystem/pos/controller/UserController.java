package com.possystem.pos.controller;

import com.possystem.pos.dto.UserDTO;
import com.possystem.pos.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value="api/v1/")
public class UserController {
        @Autowired
        private UserService userService;        
                
	@GetMapping("/getusers")
	public List<UserDTO> getUsers() {
		return userService.getAlluser();
	}
}
