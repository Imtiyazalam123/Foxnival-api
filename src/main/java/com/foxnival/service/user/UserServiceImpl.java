package com.foxnival.service.user;

import com.foxnival.dto.UserDetailsDto;
import com.foxnival.dto.UserDto;
import com.foxnival.entity.Subscriber;
import com.foxnival.entity.User;
import com.foxnival.exception.DataInsertionFailedException;
import com.foxnival.exception.InvalidRequestException;
import com.foxnival.repository.SubscribeRepository;
import com.foxnival.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubscribeRepository subscribeRepository;

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

    @Override
    public User addUser(UserDto userDto) {

        Optional<User> byUsername = userRepository.findByUsername(userDto.getUsername().trim());
        if (byUsername.isPresent()) {
            log.error("Email/Username already exist with " + userDto.getUsername());
            throw new DataInsertionFailedException("Email/Username already exist.");
        }
        Optional<Subscriber> subscriberById = subscribeRepository.findById(userDto.getSubscriberId());
        if (subscriberById.isEmpty()) {
            log.error("Subscriber not found for provided subscriber id :  " + userDto.getSubscriberId());
            throw new DataInsertionFailedException("Subscriber not found.");
        }

        User user = new User();
        user.setSubscriber(subscriberById.get());
        user.setName(userDto.getUsername());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole().getRoleName());
        user.setMobile(userDto.getMobile());
        user.setActive(true);

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsersBySubscriberId(Long subscriberId) {

        return userRepository.findAllBySubscriberId(subscriberId);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDetailsDto updateUserDetails(Long userId, UserDetailsDto userDetailsDto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            log.error("User not found for provided user id : " + userId);
            throw new InvalidRequestException("User not found.");
        }

        User user = optionalUser.get();
        user.setName(userDetailsDto.getName());
        user.setMobile(userDetailsDto.getMobile());

        if (userDetailsDto.isChangePassword()) {
            if (user.getPassword().equals(userDetailsDto.getCurrentPassword().trim())) {
                user.setPassword(userDetailsDto.getNewPassword());
            } else {
                log.error("Current password is wrong...");
                throw new InvalidRequestException("Invalid current password.");
            }
        }

        User updatedUser = userRepository.save(user);
        UserDetailsDto updatedUserDetailsDto = new UserDetailsDto();
        updatedUserDetailsDto.setName(updatedUser.getName());
        updatedUserDetailsDto.setMobile(updatedUser.getMobile());
        updatedUserDetailsDto.setUsername(updatedUser.getUsername());
        updatedUserDetailsDto.setChangePassword(userDetailsDto.isChangePassword());
        updatedUserDetailsDto.setCurrentPassword(updatedUser.getPassword());
        updatedUserDetailsDto.setNewPassword(updatedUser.getPassword());

        return updatedUserDetailsDto;
    }

    @Override
    public User fetchUserById(Long userId) {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            log.error("User not found for provided user id: " + userId);
            throw new InvalidRequestException("User not found.");
        }
        return optionalUser.get();
    }

}
