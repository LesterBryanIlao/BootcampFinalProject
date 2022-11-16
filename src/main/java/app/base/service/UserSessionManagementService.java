package app.base.service;

import javax.servlet.http.HttpServletRequest;

import app.entity.LoginSession;
import app.entity.User;

public interface UserSessionManagementService {
	public LoginSession loginUserViaEmailAndPassword( HttpServletRequest request, String email,
			String password);

	public LoginSession loginUserViaExistingSession(HttpServletRequest request);

	public User getCurrentLoggedInUser(HttpServletRequest request);

	public void logoutUser(HttpServletRequest request);
}
