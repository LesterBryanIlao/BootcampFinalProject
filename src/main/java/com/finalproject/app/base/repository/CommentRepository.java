package com.finalproject.app.base.repository;

import java.util.List;

import com.finalproject.app.entity.Comment;

public interface CommentRepository {
	public void deleteByCommentId(String commentId);

	public void deleteByPostId(String postId);

	public void updateContent(String commentId, String newContent);

	public List<Comment> getByPostId(String postId, long start, long end);

}
