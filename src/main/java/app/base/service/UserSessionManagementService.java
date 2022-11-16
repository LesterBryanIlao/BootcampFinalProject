package app.base.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.entity.LoginSession;
import app.entity.User;

public interface UserSessionManagementService {
	public Cookie[] loginUserViaEmailAndPassword( HttpServletRequest request, HttpServletResponse response,String email,
			String password);

	public User getCurrentLoggedInUser(HttpServletRequest request);

	public void logoutUser(HttpServletRequest request);
}
