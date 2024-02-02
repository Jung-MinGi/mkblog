package com.min.myblogv1.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IncludeDeletedColumnWriteForm {
    private int id;
    @NotBlank
    private String category;
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    private String deleted;
}
