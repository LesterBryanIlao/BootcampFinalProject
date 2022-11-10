package com.finalproject.app.base.service;

import com.finalproject.app.base.repository.UserRepository;
import com.finalproject.app.entity.User;

public interface UserSessionManagementService {
	public User loginUser(UserRepository userRepository,String email, String password);
//	public boolean isLoggedIn();
	public void logoutUser() ;
}
