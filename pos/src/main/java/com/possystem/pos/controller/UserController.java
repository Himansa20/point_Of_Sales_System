package com.possystem.pos.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value="api/v1/")
public class UserController {

	@GetMapping("/getUser")
	public String getUser() {
		return "One User500";
	}
        
        @GetMapping("/user/{userId}")
        public UserDTO getUserById(@PathVariable Integer userId){
        return userService.getUserById(userId);
        }
        
        @PostMapping("/adduser")
        public UserDTO saveUSer(@RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
        }
        
        @PutMapping("/updateuser")
        public UserDTO updateUser(@RequestBody UserDTO userDTO){
        return userService.updateUser(userDTO);
        }
        @DeleteMapping("/deleteuser/{userId}")
        public String deleteUser(@PathVariable Integer userId){
        return userService.deleteUSer(userId);
        }
}
