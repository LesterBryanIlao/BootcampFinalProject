package app.base.service;

import app.entity.User;

public interface UserAccountManagementService {
	public void registerUser(User user);
	public User getUserById(long userId);
}
