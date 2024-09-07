package com.foxnival.service.message;

public interface EmailService {

    public boolean sendEmail(String to, String name, String password);
}
