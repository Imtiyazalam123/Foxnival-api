package com.foxnival.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.foxnival.constant.Role;
import com.foxnival.view.Views;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long subscriberId;

    private String name;

    private String username;

    private String mobile;

    private String password;

    private Role role;
}
