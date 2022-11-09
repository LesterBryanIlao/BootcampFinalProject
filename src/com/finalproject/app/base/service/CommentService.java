package com.finalproject.app.base.service;

import java.util.List;

import com.finalproject.app.entity.Comment;
import com.finalproject.app.entity.Post;

public interface CommentService extends UserGeneratedContentService<Comment> {

	public List<Comment> getCommentFromPost(Post post);
}
