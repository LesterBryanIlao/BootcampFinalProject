package com.finalproject.app.base.service;

import java.util.List;

import com.finalproject.app.entity.Post;
import com.finalproject.app.entity.User;

public interface BasePostService {

	public void createPost(Post post);

	public void deletePost(long id);

	public void updatePost(Post post);

	public Post getPost(long id) ;

	public List<Post> getUserPosts(User user);
}
