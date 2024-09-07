package com.foxnival.dto;

import com.foxnival.constant.PaymentStatus;
import com.foxnival.constant.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscribeDetailDto {

    private String name;

    private Role role;

    private String username;

    private String password;

    private String organizationName;

    private String planForYear;

    private String orderId;

    private PaymentStatus paymentStatus;

    private String paymentId;

    private String paymentSignature;
}
