package app.entity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

public class LoginSession {
	private User user;
	private HttpSession httpSession;
	private Cookie[] cookies;

	public LoginSession(User user, HttpSession httpSession, Cookie[] cookies) {
		this.user = user;
		this.cookies = cookies;
		this.httpSession = httpSession;
	}

	public User getUser() {
		return user;
	}

	public Cookie[] getCookies() {
		return cookies;
	}

	public HttpSession getSession() {
		return this.httpSession;
	}

}
