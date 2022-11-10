package com.finalproject.app.base.service;

import com.finalproject.app.base.repository.UserRepository;
import com.finalproject.app.entity.User;

public interface UserAccountManagementService {
	public void registerUser(UserRepository userRepository, User user);
}
