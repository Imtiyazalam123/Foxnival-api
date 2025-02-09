package com.foxnival.service.forgetpassword;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public interface ForgetPasswordService {

    boolean sendVerificationCode(String username);

    boolean verifyOtp(String username, String otp);

    boolean resetPassword(String username, String newPassword);
}
