package com.finalproject.app.base.service;

import java.util.List;

import com.finalproject.app.entity.Post;
import com.finalproject.app.entity.User;

public interface BasePostService extends BaseUserGeneratedContentService<Post> {
	public List<Post> getUserPosts(User user);
}
