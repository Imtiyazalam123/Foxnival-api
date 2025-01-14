package com.foxnival.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Role {

    OWNER("owner"),
    MANAGER("manager"),
    RECEPTIONIST("receptionist"),
    TELECALLER("telecaller"),
    HR("hr"),
    SALES_REPRESENTATIVE("sales_representative");


    @JsonValue
    private String roleName;
}
