package com.finalproject.app.base.repository;

import java.util.List;

import com.finalproject.app.entity.Comment;

public interface BaseCommentRepository  extends BaseRepository<Comment> {
	public List<Comment> getViaPostId(String postId);
}
