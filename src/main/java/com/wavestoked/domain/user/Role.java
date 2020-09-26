package com.wavestoked.domain.user;

// inside the domain repo,

// user -> package/dir
    // User -> Entity class
    // Role -> enum class
    // UserRepository -> extended from jpa repository


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST", "Guest"),
    USER("ROLE_USER", "User");

    private final String key;
    private final String title;
}
