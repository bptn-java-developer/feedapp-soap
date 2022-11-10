package com.bptn.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bptn.repository.UserRepository;
import com.bptn.soap.Post;
@Service
public class PostService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private UserRepository userRepository;

	
	public List<Post> listPosts(String username){
		
		List<Post> posts = new ArrayList<>();

		this.userRepository.findById(username)
		    .ifPresent(userId -> {
		    	
		    	userId.getPosts()
				   .forEach(p -> {
					   Post post = new Post();
					
					   post.setPostID(p.getPostID());
					   post.setPostType(p.getPostType());
					   post.setPost(p.getPost());
					
					   posts.add(post);
	   			   });;			
		    });
    	
    	logger.debug("Posts found for User: {} : {}",username, posts.size());

		return posts;
	}

}
