package app.base.service;

import java.util.List;

import app.entity.User;

public interface UserService {
    List<User> getAllUsers();
    
    String getUserName(Long userId);
    
    User getUserBasedOnUserId(Long userId);
    

}
