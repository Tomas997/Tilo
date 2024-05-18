package com.io25.tiloproject.model;

import lombok.Getter;

public enum Role {
    ROLE_USER("/user/orders"), ROLE_COACH("/coach"), ROLE_ADMIN("/admin");

    @Getter
    private final String homePage;

    Role(String homePage){
        this.homePage = homePage;
    }
}
