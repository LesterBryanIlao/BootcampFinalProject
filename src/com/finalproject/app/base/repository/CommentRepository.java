package com.finalproject.app.base.repository;

import java.util.List;

import com.finalproject.app.entity.Comment;

public interface CommentRepository {
	public void create(Comment comment);

	public void delete(String commentId);

	public void update(Comment comment);

	public List<Comment> getViaPostId(String postId, long start, long end);

}
