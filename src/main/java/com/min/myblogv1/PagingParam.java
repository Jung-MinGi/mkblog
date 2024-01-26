package com.min.myblogv1;

import lombok.Data;

@Data
public class PagingParam {
    private Integer pageNum;
    private Integer pageSize;
    private String tableName;
}
