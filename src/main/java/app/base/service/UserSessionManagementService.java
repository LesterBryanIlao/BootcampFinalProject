package app.base.service;

import javax.servlet.http.HttpServletRequest;

import app.entity.LoginSession;

public interface UserSessionManagementService {
	public LoginSession loginUserViaEmailAndPassword( HttpServletRequest request, String email,
			String password);

	public LoginSession loginUserViaExistingSession(HttpServletRequest request);

	public boolean isLoggedIn(HttpServletRequest request);

	public void logoutUser(HttpServletRequest request);
}
