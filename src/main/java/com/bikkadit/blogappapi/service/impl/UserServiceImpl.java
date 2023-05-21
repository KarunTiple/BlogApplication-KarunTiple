package com.bikkadit.blogappapi.service.impl;

import com.bikkadit.blogappapi.entities.Role;
import com.bikkadit.blogappapi.entities.User;
import com.bikkadit.blogappapi.exception.ResourceNotFoundException;
import com.bikkadit.blogappapi.config.AppConstants;
import com.bikkadit.blogappapi.payloads.UserDto;
import com.bikkadit.blogappapi.repository.RoleRepository;
import com.bikkadit.blogappapi.repository.UserRepository;
import com.bikkadit.blogappapi.service.UserServiceI;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserServiceI {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepo;



    @Override
    public UserDto createUser(UserDto userDto) {


        log.info("Entering the UserService for creating the User : {}");

        User user = this.modelMapper.map(userDto, User.class);

        User savedUser = this.userRepo.save(user);

        log.info("Returning from UserService after creating the User : {}");

        return this.modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {

        log.info("Entering the UserService to update the User with User ID : {} ",userId);

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());

        User updateUser = this.userRepo.save(user);

        log.info("Returning from UserService after Updating the User with User ID : {} ",userId);

        return this.modelMapper.map(updateUser, UserDto.class);

    }

    @Override
    public UserDto getUserById(Long userId) {

        log.info("Entering the UserService to Get the User with User ID : {} ",userId);

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        log.info("Returning from UserService after Getting the User with User ID : {} ",userId);

        return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {

        log.info("Entering the UserService to Get All User : {}");

        List<User> users = this.userRepo.findAll();

        List<UserDto> userDtos = users.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

        log.info("Returning from UserService after Getting All User : {}");

        return userDtos;
    }

    @Override
    public void deleteUser(Long userId) {

        log.info("Entering the UserService to Delete the User with User ID : {} ",userId);

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        log.info("Returning from UserService after Deleting the User with User ID : {} ",userId);

        this.userRepo.delete(user);

    }

    @Override
    public UserDto registerNewUser(UserDto userDto) {

        User user = this.modelMapper.map(userDto, User.class);

        // encoded the Password
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        // roles
        Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

        user.getRoles().add(role);

        User newUser = this.userRepo.save(user);

        return this.modelMapper.map(newUser, UserDto.class);
    }

}
