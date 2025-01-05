package com.infosys.module4.service;

import com.infosys.module4.dto.PostDto;
import com.infosys.module4.exception.PostException;
import com.infosys.module4.model.Post;
import com.infosys.module4.response.PostResponse;

import java.util.List;

public interface PostService {
    public PostResponse addPost(PostDto postDto,String jwt) throws PostException;
    public List<Post> getAllPosts() throws PostException;
    public PostResponse updatePost(PostDto postDto,Long postId) throws PostException;
    public PostResponse deletePost(Long postId) throws PostException;
    public Post getPostById(Long postId) throws PostException;
    public Post likePost(Long postId) throws PostException;
    public Post removeLike(Long postId);
}
