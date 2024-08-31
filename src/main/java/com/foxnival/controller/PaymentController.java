package com.foxnival.controller;

import com.foxnival.service.PaymentService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@CrossOrigin("*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(path = "/order")
    public String createOrder(@RequestParam(name = "amount") Long amount) throws RazorpayException {

        System.out.println("create order");
        return paymentService.createOrder(amount);
    }
}
