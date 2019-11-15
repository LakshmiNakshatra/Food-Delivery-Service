package com.foodscribe.resource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foodscribe.config.SecurityUtility;
import com.foodscribe.domain.User;
import com.foodscribe.service.UserService;

@CrossOrigin(origins = "http://foodscribe.herokuapp.com", maxAge=3600)
@RestController
public class LoginResource {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value ="/token", method=RequestMethod.POST)
	public Map<String, Long> token(@RequestBody HashMap<String, String> mapper){
		
		String userEmail = mapper.get("useremail").trim();
		String password = mapper.get("password").trim();
		
		//String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		
		
		User user = userService.findByEmail(userEmail);
		
		if(user != null && user.getPassword().equals(password)){
			return Collections.singletonMap("userId", user.getId());
		} else {
			return Collections.singletonMap("userId", Long.parseLong("0"));
		}
		
	}
	
	
	@RequestMapping("/checkSession")
	public ResponseEntity checkSession(){
		return new ResponseEntity("Session Active!", HttpStatus.OK);
	} 
	
	@RequestMapping(value={"/user/logout"}, method=RequestMethod.POST)
	public ResponseEntity logout(){
		SecurityContextHolder.clearContext();
		return new ResponseEntity("Logout Successful", HttpStatus.OK);
	}
	
}
