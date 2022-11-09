package com.finalproject.app.base.repository;

import com.finalproject.app.entity.User;

public interface BaseUserRepository extends   BaseRepository<User>  {
	public User getViaEmailPassword(String email, String password);
}
