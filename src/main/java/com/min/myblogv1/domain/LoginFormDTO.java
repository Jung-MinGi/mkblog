package com.min.myblogv1.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginFormDTO {
    @NotBlank
    private  String username;
    @NotBlank
    private  String pw;


}
