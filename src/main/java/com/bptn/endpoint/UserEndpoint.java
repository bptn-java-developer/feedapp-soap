package com.bptn.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.bptn.service.UserService;
import com.bptn.soap.GetUserRequest;
import com.bptn.soap.GetUserResponse;
import com.bptn.soap.UserID;

@Endpoint
public class UserEndpoint {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String NAMESPACE_URI = "http://bptn.com/soap";

	@Autowired
	private UserService userService;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
	@ResponsePayload
	public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
		
		logger.debug("Getting User: {}", request.getUsername());

		GetUserResponse response = new GetUserResponse();
		
		UserID userID = this.userService.findUser(request.getUsername());
		response.setUserID(userID);

		return response;
	}
}
