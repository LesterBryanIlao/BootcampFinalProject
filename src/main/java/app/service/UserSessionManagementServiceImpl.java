package app.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.base.service.UserSessionManagementService;
import app.entity.User;
import app.repository.UserRepository;
import app.util.HashingUtil;

@Service
public class UserSessionManagementServiceImpl implements UserSessionManagementService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public void loginUserViaEmailAndPassword(HttpServletRequest request, HttpServletResponse response, String email,
			String password) {
		String passwordHash = HashingUtil.toSHA256(password);
		User user = userRepository.getViaEmailAndPassword(email, passwordHash);

		if (user == null) {
			throw new IllegalArgumentException("User with the provided email and password doesn't exist");
		}

		HttpSession session = request.getSession(true);

		String sessionCreationTime = String.valueOf(session.getCreationTime());
		String sessionId = request.getSession().getId();
		String userId = String.valueOf(user.getId());
		String hashVerifier = this.cookieHashVerifierGenerator(sessionId, sessionCreationTime, userId,
				user.getPasswordHash());

		Cookie creationTimeCookie = new Cookie("creationTime", sessionCreationTime);
		Cookie userIdCookie = new Cookie("userId", userId);
		Cookie hashVerifierCookie = new Cookie("hashVerifier", hashVerifier);

		response.addCookie(hashVerifierCookie);
		response.addCookie(creationTimeCookie);
		response.addCookie(userIdCookie);

	}

	@Override
	public User getCurrentLoggedInUser(HttpServletRequest request) {
//		HttpSession session = request.getSession();
////		if (session == null) {
////			return null;
////		}
//
//		User currentUser = null;
//
//		Map<String, Cookie> cookiesMap = new HashMap<>();
//		for (Cookie cookie : request.getCookies()) {
//			cookiesMap.put(cookie.getName(), cookie);
//		}
//
//		if (cookiesMap.size() > 0) {
//			Cookie creationTimeCookie = cookiesMap.get("creationTime");
//			Cookie userIdCookie = cookiesMap.get("userIdCookie");
//			Cookie hashVerifierCookie = cookiesMap.get("hashVerifierCookie");
//
//			User tempUser = userRepository.getOne(Long.parseLong(userIdCookie.getValue()));
//			if (tempUser != null) {
//
//				String passwordHash = tempUser.getPasswordHash();
//				String actualHashVerifier = hashVerifierCookie.getValue();
//				String expectedHashVerifier = this.cookieHashVerifierGenerator(String.valueOf(session.getId()),
//						creationTimeCookie.getValue(), String.valueOf(tempUser.getId()), passwordHash);
//
//				if (actualHashVerifier == expectedHashVerifier) {
//					currentUser = tempUser;
//				}
//			}
//		}
//
//		currentUser = new User();
//		currentUser.setId(10);
//		currentUser.setFirstName("User_10");
//		currentUser.setLastName("Doe");
//		currentUser.setPasswordHash("Test");
		return userRepository.findById(1l).orElse(null);
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

		return HashingUtil.toSHA256(hashVerifierBasisString);
	}

}
