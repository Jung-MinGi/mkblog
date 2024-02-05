package com.min.myblogv1.domain;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String pw;
    private String authority;
}
