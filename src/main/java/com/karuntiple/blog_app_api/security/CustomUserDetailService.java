package com.karuntiple.blog_app_api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.karuntiple.blog_app_api.entities.User;
import com.karuntiple.blog_app_api.exception.UserNameNotFoundException;
import com.karuntiple.blog_app_api.repository.UserRepository;



@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//		loading user from database by username

        User user = this.userRepo.findByEmail(username)
                .orElseThrow(() -> new UserNameNotFoundException(" User ", " email ", username));

        return user;
    }

}
