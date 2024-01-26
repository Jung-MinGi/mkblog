package com.min.myblogv1.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataGetMapper {
    List<String> getTablesName();
}
