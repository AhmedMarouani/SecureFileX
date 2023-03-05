package com.example.Challenge2.services;

import com.example.Challenge2.entities.Post;
import com.example.Challenge2.repositories.PostRepository;
import com.example.Challenge2.responses.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepository postRepository;

    @Override
    public void verifyExistenceById(Integer id){
        postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no employee was found for id " + id));
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Integer id) {
        return postRepository.findById(id).get();
    }

    @Override
    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post editPost(Integer id, Post post) {
        Post editedPost = postRepository.findById(id).get();
        editedPost.setDescription(post.getDescription());
        editedPost.setPublished(post.getPublished());
        editedPost.setTitle(post.getTitle());
        editedPost.setCreatedAt(post.getCreatedAt());
        editedPost.setUpdatedAt(post.getUpdatedAt());


        return postRepository.save(editedPost);
    }

    @Override
    public ResponseEntity<MessageResponse> deletePostById(int id) {
        verifyExistenceById(id);
        postRepository.deleteById(id);
        MessageResponse messageResponse = new MessageResponse("Post Deleted");

        return ResponseEntity.ok(messageResponse);
    }
}
