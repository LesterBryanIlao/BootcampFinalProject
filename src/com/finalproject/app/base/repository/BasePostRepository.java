package com.finalproject.app.base.repository;

import java.util.List;

import com.finalproject.app.entity.Post;

interface BasePostRepository extends BaseRepository<Post> {
	public List<Post> getViaUserId(long userId);
}
