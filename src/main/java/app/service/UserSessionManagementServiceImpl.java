package app.service;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;

import app.base.service.UserSessionManagementService;
import app.entity.User;
import app.repository.UserRepository;

@Service
public class UserSessionManagementServiceImpl implements UserSessionManagementService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public void loginUserViaEmailAndPassword(HttpServletRequest request, HttpServletResponse response, String email,
			String password) {
		String passwordHash = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
		User user = userRepository.getViaEmailAndPassword(email, passwordHash);

		if (user == null) {
			throw new IllegalArgumentException("User with the provided email and password doesn't exist");
		}

		HttpSession session = request.getSession(true);

		String sessionCreationTime = String.valueOf(session.getCreationTime());
		String sessionId = request.getSession().getId();
		String userId = String.valueOf(user.getId());
		String hashVerifier = this.cookieHashVerifierGenerator(sessionId, sessionCreationTime, userId,
				user.getPassword());

		Cookie creationTimeCookie = new Cookie("creationTime", sessionCreationTime);
		Cookie userIdCookie = new Cookie("userId", userId);
		Cookie hashVerifierCookie = new Cookie("hashVerifier", hashVerifier);

		response.addCookie(hashVerifierCookie);
		response.addCookie(creationTimeCookie);
		response.addCookie(userIdCookie);

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
