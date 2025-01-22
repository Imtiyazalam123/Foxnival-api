package com.foxnival.service.user;

import com.foxnival.dto.UserDto;
import com.foxnival.entity.User;

import java.util.List;

public interface UserService {

    public User authenticateUser(String username, String password);

    List<User> getAllUsers();

    User addUser(UserDto userDto);

    List<User> getAllUsersBySubscriberId(Long subscriberId);

    void deleteUser(Long userId);
}
