package com.foxnival.service;

import com.foxnival.entity.OrderDetails;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Override
    public String createOrder(Long amount) throws RazorpayException {

        RazorpayClient razorpay = new RazorpayClient("rzp_test_evX6onXoZwfzMj", "NgFrOZUZwE5ynvsPrmYKqsw8");

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount",amount * 100);
        orderRequest.put("currency","INR");
        orderRequest.put("receipt", "receipt#1");

        Order order = razorpay.orders.create(orderRequest);
        return order.toString();
    }
}
