package com.finalproject.app.base.service;

import java.util.List;

import com.finalproject.app.entity.Comment;
import com.finalproject.app.entity.Post;

public interface BaseCommentService {

	public void createComment(Comment comment);

	public void deleteComment(long id);

	public void updateComment(Comment newComment);

	public Comment getComment(long id);

	public List<Comment> getCommentsFromPost(Post post);
}
