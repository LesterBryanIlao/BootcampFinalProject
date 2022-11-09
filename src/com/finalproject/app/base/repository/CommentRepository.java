package com.finalproject.app.base.repository;

import java.util.List;

import com.finalproject.app.entity.Comment;

public interface CommentRepository  extends Repository<Comment> {
	public List<Comment> getViaPostId(String postId);
}
