package com.karuntiple.blog_app_api.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.karuntiple.blog_app_api.config.AppConstants;
import com.karuntiple.blog_app_api.entities.Role;
import com.karuntiple.blog_app_api.repository.RoleRepository;



@Configuration
public class PasswordEncryptor implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepo;

    @Override
    public void run(String... args) throws Exception {

//        System.out.println(this.passwordEncoder.encode("ktiple"));

        try {

            Role role = new Role();
            role.setId(AppConstants.ADMIN_USER);
            role.setName("ROLE_ADMIN");

            Role role1 = new Role();
            role1.setId(AppConstants.NORMAL_USER);
            role1.setName("ROLE_NORMAL");

            List<Role> roles = List.of(role, role1);

            List<Role> result = this.roleRepo.saveAll(roles);

            result.forEach(r -> {
                System.out.println(r.getName());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

