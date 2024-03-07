package com.karuntiple.blog_app_api.payloads;

import lombok.Data;

@Data
public class JwtAuthResponse {

    private String email;
    private String token;

}
