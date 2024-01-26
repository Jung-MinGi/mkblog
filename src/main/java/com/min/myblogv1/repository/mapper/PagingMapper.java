package com.min.myblogv1.repository.mapper;

import com.github.pagehelper.Page;
import com.min.myblogv1.domain.WriteForm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PagingMapper {
    Page<WriteForm> getPaging(String tableName);
}
