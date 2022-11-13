package com.finalproject.app.base.service;

import java.util.List;

import com.finalproject.app.base.repository.PostRepository;
import com.finalproject.app.entity.Post;
import com.finalproject.app.entity.User;

public interface PostService  {

	public void createPost(PostRepository postRepository, User user, Post post);

	public void deletePost(PostRepository postRepository, Post post);

	public void updatePostContent(PostRepository postRepository,Post existingPost, Post newPost);
	
	public void upVotePost(PostRepository postRepository, User user);
	
	public List<Post> getPosts(PostRepository postRepository);

	public List<Post> getUserPosts(PostRepository postRepository,User user);
	
}
