package com.min.myblogv1.repository.mapper;

import com.min.myblogv1.domain.WriteForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataGetMapper {
    List<String> getTablesName();
    void save(WriteForm writeForm);

    WriteForm findTextByTitle(String category,String title);
}
