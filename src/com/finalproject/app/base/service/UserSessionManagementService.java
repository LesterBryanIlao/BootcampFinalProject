package com.finalproject.app.base.service;

import com.finalproject.app.base.repository.UserRepository;
import com.finalproject.app.entity.LoginSession;

public interface UserSessionManagementService {
	public LoginSession loginUserViaEmailAndPassword(UserRepository userRepository, HttpServletRequest request, String email, String password);
	public LoginSession loginUserViaExistingSession(UserRepository userRepository, HttpSession session, Cookie [] cookies);
	public boolean isLoggedIn(UserRepository userRepository, HttpSession session, Cookie [] cookies);
	public void logoutUser(LoginSession loginSession) ;
}
