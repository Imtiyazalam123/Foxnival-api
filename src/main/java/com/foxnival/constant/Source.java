package com.foxnival.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Source {

    ONLINE("Online"),
    OFFLINE("Offline");


    @JsonValue
    private String source;
}
