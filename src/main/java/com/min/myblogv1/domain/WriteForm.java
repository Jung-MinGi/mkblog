package com.min.myblogv1.domain;

import lombok.Data;

@Data
public class WriteForm {
    private int id;
    private String category;
    private String title;
    private String content;
}
