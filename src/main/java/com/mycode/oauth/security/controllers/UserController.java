package com.mycode.oauth.security.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycode.oauth.security.dto.UserDTO;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateUserDetails(@RequestBody UserDTO user){
		LOG.info("Updating user: " + user.getFirstName() + " " + user.getLastName());
		LOG.info("\"Updating user: \" + user.getFirstName() + \" \" + user.getLastName()");
		//LOG.info("Second hotfix for April 2019");
		//LOG.info("Log created in Phase2 Sprint1 Story1 - hotfix for April");
		return "SUCCESS";
	}
	
}
