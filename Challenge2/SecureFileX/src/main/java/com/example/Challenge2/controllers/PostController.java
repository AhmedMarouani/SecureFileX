package com.example.Challenge2.controllers;

import com.example.Challenge2.entities.Post;
import com.example.Challenge2.responses.MessageResponse;
import com.example.Challenge2.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Posts")

public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/")
    public List<Post> getAllPost(){
        List<Post> Posts =  postService.getAllPosts();
        return Posts;
    }
    @GetMapping("/{id}")
    public Post getPostByID(@PathVariable("id") Integer id){
        Post post = postService.getPostById(id);
        return post;
    }
    @PostMapping("/addPost")
    public Post addNewPost(@RequestBody Post post){
        Post newPost = postService.addPost(post);
        return newPost;
    }
    @PutMapping("/editPost/{id}")
    public Post updatePostByID(@PathVariable("id") Integer id, @RequestBody Post post){
        Post editedPost = postService.editPost(id, post);
        return editedPost;
    }
    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<MessageResponse> deletePostById(@PathVariable("id") Integer id){
        ResponseEntity<MessageResponse> response = postService.deletePostById(id);
        return response;
    }

}
