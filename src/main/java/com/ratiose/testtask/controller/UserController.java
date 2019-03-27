package com.ratiose.testtask.controller;

import javax.servlet.http.HttpSession;

import com.ratiose.testtask.entity.User;
import com.ratiose.testtask.exception.RequestException;
import com.ratiose.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@RequestMapping("/user")
public class UserController
{

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = POST)
	public ResponseEntity registerUser(@RequestParam String email, @RequestParam String password, HttpSession session)
	{
		try {
			User user = userService.registerUser(email, password);
			return ResponseEntity.status(HttpStatus.OK).body(user.getId());
		} catch (RequestException ex)
		{
			return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
}
