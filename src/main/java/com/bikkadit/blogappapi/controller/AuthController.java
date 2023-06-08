package com.bikkadit.blogappapi.controller;

import com.bikkadit.blogappapi.config.SecurityConstants;
import com.bikkadit.blogappapi.exception.ApiException;
import com.bikkadit.blogappapi.payloads.JwtAuthRequest;
import com.bikkadit.blogappapi.payloads.JwtAuthResponse;
import com.bikkadit.blogappapi.payloads.UserDto;
import com.bikkadit.blogappapi.security.JWTTokenHelper;
import com.bikkadit.blogappapi.service.UserServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceI userService;

    /**
     * @author Karun
     * @param request
     * @return
     * @throws Exception
     * @apiNote This api is for doing Login
     */

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {

        log.info("Initializing the login api of Auth Controller : {} ");

        this.authenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

        String token = this.jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse response = new JwtAuthResponse();

        response.setEmail(userDetails.getUsername());
        response.setToken(token);

        log.info("Returning from login api of  Auth Controller : {} ");

        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);

        try {
            this.authenticationManager.authenticate(authenticationToken);

        } catch (BadCredentialsException e) {
            System.out.println(SecurityConstants.INVALID_DETAILS);
            throw new ApiException(SecurityConstants.BAD_CREDENTIALS);
        }

    }

//	Register new user Api

    /**
     * @author Karun
     * @param userDto
     * @return
     * @apiNote This api is for Registering new User
     */
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {

        log.info("Initializing the Register User Api of Auth Controller : {} ");

        UserDto registerNewUser = this.userService.registerNewUser(userDto);

        log.info("Returning the Register User Api of Auth Controller : {} ");

        return new ResponseEntity<UserDto>(registerNewUser, HttpStatus.CREATED);

    }

}
