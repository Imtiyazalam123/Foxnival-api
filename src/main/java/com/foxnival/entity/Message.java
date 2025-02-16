package com.foxnival.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private Integer senderId;
    private Integer receiverId;
    private String text;
    private String timestamp;
}
