package app.service;

import javax.servlet.http.HttpServletRequest;

import app.base.service.UserSessionManagementService;
import app.entity.LoginSession;
import app.entity.User;

@Service
public class UserSessionManagementServiceImpl implements UserSessionManagementService{

	@Override
	public LoginSession loginUserViaEmailAndPassword(HttpServletRequest request, String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginSession loginUserViaExistingSession(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getCurrentLoggedInUser(HttpServletRequest request) {
		User dummyUser = new User();
		dummyUser.setId(10);
		dummyUser.setFirstName("User_10");
		dummyUser.setLastName("Doe");
		dummyUser.setPassword("Test");
		return dummyUser;
	}

	@Override
	public void logoutUser(HttpServletRequest request) {
	
		
	}

	
}
