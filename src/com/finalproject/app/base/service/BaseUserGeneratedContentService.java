package com.finalproject.app.base.service;

import com.finalproject.app.base.entity.BaseUserGeneratedContent;
import com.finalproject.app.entity.Post;

public interface BaseUserGeneratedContentService<T extends BaseUserGeneratedContent> {
	public void create(T content);

	public void delete(long id);

	public void update(Post post);

	public Post get(long id);
}
