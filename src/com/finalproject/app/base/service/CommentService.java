package com.finalproject.app.base.service;

import java.util.List;

import com.finalproject.app.base.repository.CommentRepository;
import com.finalproject.app.entity.Comment;
import com.finalproject.app.entity.Post;
import com.finalproject.app.entity.User;

public interface CommentService {

	public void createComment(CommentRepository commentRepository, User user, Comment comment);

	public void deleteComment(CommentRepository commentRepository, Comment comment);

	public void deletePostComments(CommentRepository commentRepository, Post post);

	public void updateComment(CommentRepository commentRepository, Comment existingComment, Comment newComment);

	public List<Comment> getCommentFromPost(CommentRepository commentRepository, Post post);

}
