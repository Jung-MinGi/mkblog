package com.min.myblogv1.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginFormDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String pw;
}
