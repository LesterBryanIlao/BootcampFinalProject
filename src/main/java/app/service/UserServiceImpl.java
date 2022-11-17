package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.base.service.UserService;
import app.entity.User;
import app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User getUserById(long userId) {
		return userRepository.getOne(userId);
	}
}
