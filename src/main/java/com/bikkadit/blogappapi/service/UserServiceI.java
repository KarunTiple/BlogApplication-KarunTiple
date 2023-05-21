package com.bikkadit.blogappapi.service;

import com.bikkadit.blogappapi.payloads.UserDto;

import java.util.List;

public interface UserServiceI {

    UserDto registerNewUser(UserDto userDto);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Long userId);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    void deleteUser(Long userId);
}
