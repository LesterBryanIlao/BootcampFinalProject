package com.finalproject.app.base.service;

import java.util.List;

import com.finalproject.app.entity.Comment;
import com.finalproject.app.entity.Post;

public interface CommentService {

	public void createComment(Comment comment);

	public void deleteComment(Comment comment);

	public void updateComment(Comment comment);

	public List<Comment> getCommentFromPost(Post post);
}
