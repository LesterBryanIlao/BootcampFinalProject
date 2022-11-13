package com.finalproject.app.base.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.finalproject.app.base.repository.UserRepository;
import com.finalproject.app.entity.LoginSession;

public interface UserSessionManagementService {
	public LoginSession loginUserViaEmailAndPassword(UserRepository userRepository, HttpServletRequest request, String email,
			String password);

	public LoginSession loginUserViaExistingSession(UserRepository userRepository, HttpServletRequest request);

	public boolean isLoggedIn(UserRepository userRepository, HttpSession session, Cookie[] cookies);

	public void logoutUser(HttpServletRequest request);
}
