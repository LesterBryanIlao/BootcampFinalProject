package com.finalproject.app.base.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import com.finalproject.app.base.repository.UserRepository;
import com.finalproject.app.entity.LoginSession;

public interface UserSessionManagementService {
	public LoginSession loginUserViaEmailAndPassword(UserRepository userRepository, HttpServlet request, String email, String password);
	public LoginSession loginUserViaExistingSession(UserRepository userRepository, HttpServlet request);
	public boolean isLoggedIn(UserRepository userRepository, HttpSession session, Cookie [] cookies);
	public void logoutUser(LoginSession loginSession) ;
}
