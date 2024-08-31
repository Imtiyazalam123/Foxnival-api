package com.foxnival.service;

import com.foxnival.entity.OrderDetails;
import com.razorpay.Order;
import com.razorpay.RazorpayException;

public interface PaymentService {

    public String createOrder(Long amount) throws RazorpayException;
}
