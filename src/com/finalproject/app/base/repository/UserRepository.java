package com.finalproject.app.base.repository;

import com.finalproject.app.entity.User;

public interface UserRepository extends Repository<User> {
	public User getViaEmailAndPassword(String email, String password);
}
