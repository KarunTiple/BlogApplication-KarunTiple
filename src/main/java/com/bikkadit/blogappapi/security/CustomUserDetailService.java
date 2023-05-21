package com.bikkadit.blogappapi.security;

import com.bikkadit.blogappapi.entities.User;
import com.bikkadit.blogappapi.exception.UserNameNotFoundException;
import com.bikkadit.blogappapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



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
