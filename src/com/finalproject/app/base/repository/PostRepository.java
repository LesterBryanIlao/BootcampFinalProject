package com.finalproject.app.base.repository;

import java.util.List;

import com.finalproject.app.entity.Post;

interface PostRepository extends Repository<Post> {
	public List<Post> getViaUserId(long userId);
}
