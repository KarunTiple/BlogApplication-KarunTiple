package com.karuntiple.blog_app_api.service;

import java.util.List;

import com.karuntiple.blog_app_api.payloads.UserDto;

public interface UserServiceI {

    UserDto registerNewUser(UserDto userDto);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Long userId);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    void deleteUser(Long userId);
}
