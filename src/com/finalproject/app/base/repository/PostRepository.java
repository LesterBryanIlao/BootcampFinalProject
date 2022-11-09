package com.finalproject.app.base.repository;

import java.util.List;

import com.finalproject.app.entity.Post;

interface PostRepository {

	public void create(Post post);

	public void delete(String postId);

	public void update(Post post);

	public List<Post> getByUserId(long userId);

	public List<Post> getPosts(long start, long end);

}
