package com.bptn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bptn.jpa.Post;
import com.bptn.jpa.UserID;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
    List<Post> findByUserId(UserID userId);
}
