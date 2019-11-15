package com.foodscribe.resource;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foodscribe.config.SecurityConfig;
import com.foodscribe.config.SecurityUtility;
import com.foodscribe.domain.User;
import com.foodscribe.service.UserService;
import com.foodscribe.utility.MailConstructor;




@CrossOrigin(origins = "http://foodscribe.herokuapp.com", maxAge=3600)
@RestController
@RequestMapping("/user")
public class UserResorce {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailConstructor mailConstructor;
	
	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping(value="/newUser", method = RequestMethod.POST)
	public Map<String, Long> newUserPost(HttpServletRequest request, @RequestBody HashMap<String, String> mapper) throws Exception{
		//String username = mapper.get("username");
		String userEmail = mapper.get("email");
		
		if(userService.findByEmail(userEmail) != null){
			return Collections.singletonMap("userId", Long.parseLong("0"));
		}
		
		User user = new User();
		user.setEmail(userEmail);
		user.setUsername(null);
		
		String password = mapper.get("password");
		
		//String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(password);
		
		userService.createUser(user);
		
		 user = userService.findByEmail(userEmail);
		//SimpleMailMessage email = mailConstructor.constructNewUserEmail(user, password);
		//mailSender.send(email);
		
		
		return Collections.singletonMap("userId", user.getId());
	}
	
	
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
	public ResponseEntity forgetPasswordPost(HttpServletRequest request, @RequestBody HashMap<String, String> mapper)
			throws Exception {

		User user = userService.findByEmail(mapper.get("email"));

		if (user == null) {
			return new ResponseEntity("Email not found", HttpStatus.BAD_REQUEST);
		}
		String password = SecurityUtility.randomPassword();

		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encryptedPassword);
		userService.save(user);

		SimpleMailMessage newEmail = mailConstructor.constructNewUserEmail(user, password);
		mailSender.send(newEmail);

		return new ResponseEntity("Email sent!", HttpStatus.OK);

	}
	
	@RequestMapping(value="/updateUserInfo", method=RequestMethod.POST)
	public ResponseEntity profileInfo(
				@RequestBody HashMap<String, Object> mapper
			) throws Exception{
		
		int id = (Integer) mapper.get("userid");
		//String email = (String) mapper.get("email");
		//String username = (String) mapper.get("username");
		String firstName = (String) mapper.get("firstName");
		String lastName = (String) mapper.get("lastName");
		String phoneNumber = (String) mapper.get("phoneNumber");
		
		User currentUser = userService.findById(Long.valueOf(id));
		
		if(currentUser == null) {
			throw new Exception ("User not found");
		}	
		
		currentUser.setFirstName(firstName);
		currentUser.setLastName(lastName);
		currentUser.setPhoneNumber(phoneNumber);
		
		userService.save(currentUser);
		
		return new ResponseEntity("Update Success", HttpStatus.OK);
	}
	
	@RequestMapping("/getCurrentUser")
	public User getCurrentUser(Principal principal) {
		User user = new User();
		if (null != principal) {
			user = userService.findByUsername(principal.getName());
		}

		return user;
	}
	
	@RequestMapping("/getLastName/{userid}")
	public String getLastName(@PathVariable("userid") Long userid) {
		User user = new User();
		
	    user = userService.findById(userid);
		
		return user.getLastName();
	}
}
