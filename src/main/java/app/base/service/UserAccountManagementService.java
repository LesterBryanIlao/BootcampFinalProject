package app.base.service;

import app.entity.User;
import app.repository.UserRepository;

public interface UserAccountManagementService {
	public void registerUser(UserRepository userRepository, User user);
}
