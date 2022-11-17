package app.service;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;


import app.base.service.UserSessionManagementService;
import app.entity.LoginSession;
import app.entity.User;
import app.repository.UserRepository;

@Service
public class UserSessionManagementServiceImpl implements UserSessionManagementService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public LoginSession loginUserViaEmailAndPassword(HttpServletRequest request, String email, String password) {
		LoginSession loginSession = null;
		String passwordHash = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
		User user = userRepository.getViaEmailAndPassword(email, passwordHash);
		if (user != null) {
			HttpSession session = request.getSession(true);

			String sessionCreationTime = String.valueOf(session.getCreationTime());
			String sessionId = request.getSession().getId();
			String hashVerifier = this.cookieHashVerifierGenerator(sessionId, sessionCreationTime,
					String.valueOf(user.getId()), user.getPassword());

			Cookie creationTimeCookie = new Cookie("creationTime", sessionCreationTime);
			Cookie userIdCookie = new Cookie("userId", String.valueOf(user.getId()));
			Cookie hashVerifierCookie = new Cookie("hashVerifier", hashVerifier);
			Cookie[] cookies = new Cookie[] { userIdCookie, hashVerifierCookie, creationTimeCookie };
			loginSession = new LoginSession(user, session, cookies);
		}
		return loginSession;
	}

	@Override
	public LoginSession loginUserViaExistingSession(HttpServletRequest request) {
//		LoginSession loginSession = null;
//		Map<String, String> cookies = new HashMap<String, String>();
//		for (Cookie cookie : request.getCookies()) {
//			cookies.put(cookie.getName(), cookie.getValue());
//		}
//
//		String userId = cookies.get("userId");
//		User user = userRepository.getOne(Long.parseLong(userId));
//
//		if (user != null) {
//			HttpSession session = request.getSession();
//			String sessionId = session.getId();
//			String creationTime = cookies.get("creationTime");
//			String password = user.getPassword();
//			String actualHashVerifier = this.cookieHashVerifierGenerator(sessionId, creationTime, userId, password);
//			String expectedHashVerifier = cookies.get("hashVerifier");
//			
//			if(actualHashVerifier.equals(expectedHashVerifier)) {
//				Cookie creationTimeCookie = new Cookie("creationTime", sessionCreationTime);
//				Cookie userIdCookie = new Cookie("userId", String.valueOf(user.getId()));
//				Cookie hashVerifierCookie = new Cookie("hashVerifier", hashVerifier);
//				Cookies [] newCookies = new Cookie[] {creationTimeCookie, userIdCookie, hashVerifierCookie};
//				loginSession = new LoginSession(user, session, null);
//			}
//		}

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
		HttpSession session = request.getSession();
		session.invalidate();
	}

	private String cookieHashVerifierGenerator(String sessionId, String sessionCreationTime, String userId,
			String password) {
		String hashVerifierBasisString = new StringBuilder().append(sessionId).append(sessionCreationTime)
				.append(userId).append(password).toString();

		return Hashing.sha256().hashString(hashVerifierBasisString, StandardCharsets.UTF_8).toString();
	}

}
