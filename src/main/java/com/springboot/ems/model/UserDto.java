package com.springboot.ems.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isActive;

    public UserDto(String firstName, String lastName, String email, String password, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
    }

    public UserDto() {
    }
}
