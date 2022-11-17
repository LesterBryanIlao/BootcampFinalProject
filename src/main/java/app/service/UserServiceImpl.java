package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.User;
import app.repository.UserRepository;

@Service
public class UserServiceImpl {
	
	@Autowired
	private UserRepository userRepository;
	public User getUserById(long userId) {
		return userRepository.getOne(userId);
	}
}
