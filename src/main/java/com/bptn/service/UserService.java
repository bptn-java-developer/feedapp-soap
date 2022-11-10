package com.bptn.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bptn.repository.UserRepository;
import com.bptn.soap.UserID;
@Service
public class UserService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private UserRepository userRepository;

	public com.bptn.soap.UserID findUser(String username){
		
		UserID userID = new UserID();
		
		this.userRepository.findById(username)
		      .ifPresent( u -> {
		    	  
		    	  logger.debug("User found: {}", u);

		    	  userID.setUsername(u.getUsername());
		    	  userID.setName(u.getName());
		    	  userID.setEmailID(u.getEmailID());
		    	  userID.setPhoneNumber(u.getPhoneNumber());
		    	  userID.setUserPassword(u.getUserPassword());
		    	  		    	  
		      });;

		return userID;
	}

}
