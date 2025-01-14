package com.foxnival.service.user;

import com.foxnival.entity.User;
import com.foxnival.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User authenticateUser(String username, String password) {

        User user = null;

        Optional<User> userOptionals = userRepository.findByUsernameAndPassword(username.trim(), password.trim());
        if (!userOptionals.isEmpty()) {
            user = userOptionals.get();
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
