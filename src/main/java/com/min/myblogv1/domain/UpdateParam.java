package com.min.myblogv1.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UpdateParam {

    private int id;
    @NotBlank
    private String prevCategory;
    @NotBlank
    private String category;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
