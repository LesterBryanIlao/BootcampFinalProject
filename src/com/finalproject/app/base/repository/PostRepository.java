package com.finalproject.app.base.repository;

import java.util.List;

import com.finalproject.app.entity.Post;

public interface PostRepository extends Repository<Post> {

	public List<Post> getByUserId(long userId);

	public void deleteByPostId(long postId);

	public void updateUpvotes(long postId, long newUpvotes);
	public void updateContent(long postid, String newContent);
	

}
