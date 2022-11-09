package com.finalproject.app.base.service;

import com.finalproject.app.entity.User;

public interface BaseUserSessionManagementService {
	public User loginUser(String email, String password);
	public boolean isLoggedIn();
	public void logoutUser() ;
}
