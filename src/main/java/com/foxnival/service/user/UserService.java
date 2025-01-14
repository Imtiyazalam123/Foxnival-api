package com.foxnival.service.user;

import com.foxnival.entity.User;

import java.util.List;

public interface UserService {

    public User authenticateUser(String username, String password);

    List<User> getAllUsers();
}
