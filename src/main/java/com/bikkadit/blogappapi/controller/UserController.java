package com.bikkadit.blogappapi.controller;

import com.bikkadit.blogappapi.config.AppConstants;
import com.bikkadit.blogappapi.payloads.ApiResponse;
import com.bikkadit.blogappapi.payloads.UserDto;
import com.bikkadit.blogappapi.service.UserServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceI userService;


//	POST - create user

    /**
     * @author Karun
     * @param userDto
     * @return
     * @apiNote This api is for Creating the User
     */

    @PostMapping("/user")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

        log.info("Entering the UserController to Create User : {} ");

        UserDto createUserDto = this.userService.createUser(userDto);

        log.info("Returning from UserController after Creating User : {} ");

        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }


//	PUT- update user

    /**
     * @author Karun
     * @param userDto
     * @param userId
     * @return
     * @apiNote This api is for Updating the User
     */
    @PutMapping("/user/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Long userId) {

        log.info("Entering the UserController to Update User with User ID : {} ",userId);

        UserDto updatedUser = this.userService.updateUser(userDto, userId);

        log.info("Returning from UserController after Updating User with User ID : {} ",userId);

        return ResponseEntity.ok(updatedUser);
    }

//	PUT- delete user

    /**
     * @author Karun
     * @param userId
     * @return
     * @apiNote This api is for Deleting the User
     */

//  ADMIN
//	Delete- delete user
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {

        log.info("Entering the UserController to Delete User with User ID : {} ",userId);

        this.userService.deleteUser(userId);

        log.info("Returning from UserController after Deleting User with User ID : {} ",userId);


        return new ResponseEntity<>(new ApiResponse(AppConstants.USER_DELETED, true), HttpStatus.OK);
    }

//	GET - get user

    /**
     * @author Karun
     * @return
     * @apiNote This api is for Getting All the User
     */
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {

        log.info("Entering the UserController to Get All User : {} ");

        return ResponseEntity.ok(this.userService.getAllUsers());

    }

//	GET - get single user

    /**
     * @author Karun
     * @param userId
     * @return
     * @apiNote This api is for Getting the Single User
     */

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getSingleUsers(@PathVariable Long userId) {

        log.info("Entering the UserController to Get Single User : {} ");

        return ResponseEntity.ok(this.userService.getUserById(userId));

    }

}
