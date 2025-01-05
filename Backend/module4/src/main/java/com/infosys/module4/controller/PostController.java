package com.infosys.module4.controller;

import com.infosys.module4.dto.PostDto;
import com.infosys.module4.model.Post;
import com.infosys.module4.response.PostResponse;
import com.infosys.module4.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/addPost")
    public PostResponse addPost(@RequestHeader("Authorization") String jwt, @RequestBody PostDto postDto){
        return postService.addPost(postDto,jwt);
    }

    @GetMapping("/getAllPosts")
    public List<Post> getAllPosts(@RequestHeader("Authorization") String jwt){
        return postService.getAllPosts();
    }

    @PutMapping("/updatePost/{postId}")
    public PostResponse updatePost(@RequestHeader("Authorization") String jwt,@PathVariable Long postId,@RequestBody PostDto postDto){
        return postService.updatePost(postDto,postId);
    }

    @DeleteMapping("/deletePost/{postId}")
    public PostResponse deletePost(@RequestHeader("Authorization") String jwt,@PathVariable Long postId){
        return postService.deletePost(postId);
    }

    @PutMapping("/likePost/{postId}")
    public Post likePost(@PathVariable Long postId){
        return postService.likePost(postId);
    }

    @PutMapping("/removeLike/{postId}")
    public Post removeLike(@PathVariable Long postId){
        return postService.removeLike(postId);
    }
}
