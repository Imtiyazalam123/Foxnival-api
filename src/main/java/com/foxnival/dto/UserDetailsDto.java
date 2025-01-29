package com.foxnival.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {

    private String name;

    private String username;

    private String mobile;

    private boolean changePassword;

    private String currentPassword;

    private String newPassword;
}
