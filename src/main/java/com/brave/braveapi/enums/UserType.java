package com.brave.braveapi.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserType {
    ADMIN("ADMIN"),
    COMMON_USER("COMMON_USER");

    private final String descritpion;
}
