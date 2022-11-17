package app.base.service;

import app.entity.User;
import app.repository.UserRepository;

public interface UserAccountManagementService {
	public void registerUser(User user);
	public User getUserById(long userId);
}
