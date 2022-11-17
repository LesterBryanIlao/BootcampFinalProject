package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.base.service.UserAccountManagementService;
import app.entity.User;
import app.repository.UserRepository;

@Service
public class UserAccountManagementServiceImpl implements UserAccountManagementService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User getUserById(long userId) {
		return userRepository.getOne(userId);
	}

	@Override
	public void registerUser(User user) {
		userRepository.save(user);
	}
}
