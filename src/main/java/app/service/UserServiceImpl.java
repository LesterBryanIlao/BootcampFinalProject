package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import app.base.service.UserService;
import app.entity.User;
import app.repository.UserRepository;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    
    
   
    
    @Override
    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        return userRepository.findAll();
    }

    @Override
    public String getUserName(Long userId) {
        
        return userRepository.getOne(userId).getUserName();
    }

    @Override
    public User getUserBasedOnUserId(Long userId) {
        // TODO Auto-generated method stub
        return userRepository.getOne(userId);
    }

}
