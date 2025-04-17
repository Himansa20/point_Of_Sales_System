package com.possystem.pos.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value="api/v1/")
public class UserController {

	@GetMapping("/getUser")
	public String getUser() {
		return "One User1000";
	}
}
