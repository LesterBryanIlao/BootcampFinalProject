package com.finalproject.app.base.service;

import java.util.List;

import com.finalproject.app.entity.Comment;
import com.finalproject.app.entity.Post;

public interface BaseCommentService extends BaseUserGeneratedContentService<Comment> {

	public List<Comment> getFromPost(Post post);
}
