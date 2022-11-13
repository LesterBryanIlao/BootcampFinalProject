package com.finalproject.app.base.service;

import javax.servlet.http.HttpServletRequest;

import com.finalproject.app.base.repository.UserRepository;
import com.finalproject.app.entity.LoginSession;

public interface UserSessionManagementService {
	public LoginSession loginUserViaEmailAndPassword(UserRepository userRepository, HttpServletRequest request, String email,
			String password);

	public LoginSession loginUserViaExistingSession(UserRepository userRepository, HttpServletRequest request);

	public boolean isLoggedIn(UserRepository userRepository, HttpServletRequest request);

	public void logoutUser(HttpServletRequest request);
}
