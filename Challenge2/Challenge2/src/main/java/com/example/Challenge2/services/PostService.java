package com.example.Challenge2.services;

import com.example.Challenge2.entities.Post;
import com.example.Challenge2.responses.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();

    Post getPostById(Integer id);

    Post addPost(Post post);

    Post editPost(Integer id, Post post);

    ResponseEntity<MessageResponse> deletePostById(int id);

    void verifyExistenceById(Integer id);


}
