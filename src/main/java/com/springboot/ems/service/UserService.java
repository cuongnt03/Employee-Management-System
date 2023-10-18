package com.springboot.ems.service;

import com.springboot.ems.entity.User;
import com.springboot.ems.model.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

public interface UserService extends UserDetailsService {
    User save(UserDto userDto);
    User findUserByEmail(String email);
}
