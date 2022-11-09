package com.finalproject.app.base.service;

import java.util.List;
import com.finalproject.app.entity.Post;
import com.finalproject.app.entity.User;

public interface PostService  {

	public void createPost(Post post);

	public void deletePost(String postId);

	public void updatePost(Post post);
	
	public List<Post> getPosts();

	public List<Post> getUserPosts(User user);
	
}
