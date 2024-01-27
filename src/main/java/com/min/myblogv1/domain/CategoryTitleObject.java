package com.min.myblogv1.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryTitleObject {
    private String category;
    private String title;
}
