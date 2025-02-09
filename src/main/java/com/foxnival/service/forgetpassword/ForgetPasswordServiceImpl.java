package com.foxnival.service.forgetpassword;

import com.foxnival.entity.User;
import com.foxnival.exception.InvalidRequestException;
import com.foxnival.repository.UserRepository;
import com.foxnival.service.message.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class ForgetPasswordServiceImpl implements ForgetPasswordService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    private final Map<String, String> otpStorage = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Override
    public boolean sendVerificationCode(String username) {
        boolean isOtpSent = false;
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()) {

            String otp = String.format("%06d", new Random().nextInt(999999));
            otpStorage.put(username, otp);

            isOtpSent = emailService.sendOtp(username, otp);

            scheduler.schedule(() -> otpStorage.remove(username), 3, TimeUnit.MINUTES);
        } else {
            throw new InvalidRequestException("Email/Username not found.");
        }

        return isOtpSent;
    }

    @Override
    public boolean verifyOtp(String username, String otp) {
        return otp.equals(otpStorage.get(username.trim()));
    }
}
