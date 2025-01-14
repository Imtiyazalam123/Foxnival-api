package com.foxnival.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscribeDetail {

    private Subscriber subscriber;

    private User user;

    private PaymentDetails paymentDetails;

    private boolean isMailSend;
}
