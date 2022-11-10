package com.bptn.endpoint;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.bptn.service.PostService;
import com.bptn.soap.GetUserPostsRequest;
import com.bptn.soap.GetUserPostsResponse;
import com.bptn.soap.Post;

@Endpoint
public class PostEndpoint {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String NAMESPACE_URI = "http://bptn.com/soap";

	@Autowired
	private PostService postService;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserPostsRequest")
	@ResponsePayload
	public GetUserPostsResponse getUserPosts(@RequestPayload GetUserPostsRequest request) {
		
		logger.debug("Getting Posts for User: {}", request.getUsername());
		
		GetUserPostsResponse response = new GetUserPostsResponse();
		
		List<Post> posts = this.postService.listPosts(request.getUsername());
		response.getPosts().addAll(posts);

		return response;
	}
}
