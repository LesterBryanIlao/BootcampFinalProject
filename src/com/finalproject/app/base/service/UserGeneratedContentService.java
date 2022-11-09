package com.finalproject.app.base.service;

import com.finalproject.app.base.entity.UserGeneratedContent;
import com.finalproject.app.entity.Post;

public interface UserGeneratedContentService<T extends UserGeneratedContent> {
	public void createContent(T content);

	public void deleteContent(long contentId);

	public void updateContente(T content);

	public Post getContent(long contentId);
}
