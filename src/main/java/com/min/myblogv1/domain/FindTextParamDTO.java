package com.min.myblogv1.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FindTextParamDTO {
    private String category;
    private String title;
}
