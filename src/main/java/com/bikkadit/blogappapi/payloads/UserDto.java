package com.bikkadit.blogappapi.payloads;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {

    private Long id;

    @NotEmpty
    @Size(min = 4, message = "UserName must be min of 4 characters")
    private String name;

    @NotEmpty
    @Email(message = "Email address is not valid!!!")
    private String email;

    @Size(max = 15,min = 6,message = "Password must contain max of 15 Char and min of 6 Char")
    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9]*",
            message = "Password should be according to pattern ...." +
                    " Characters should be a to z. " +
                    " Characters should be A to Z. " +
                    " Number should be from 0 to 9. " )
    private String password;

    @NotEmpty
    private String about;

    private Set<RoleDto> roles = new HashSet<>();
}
