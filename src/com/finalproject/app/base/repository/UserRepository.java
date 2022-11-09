package com.finalproject.app.base.repository;

import com.finalproject.app.entity.User;

public interface UserRepository extends   Repository<User>  {
	public User getViaEmailPassword(String email, String password);
}
