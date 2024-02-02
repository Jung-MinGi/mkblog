package com.min.myblogv1.repository.mapper;

import com.min.myblogv1.domain.WriteForm;
import com.min.myblogv1.domain.IncludeDeletedColumnWriteForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataGetMapper {
    List<String> getTablesName();
    void save(WriteForm writeForm);
    void update(WriteForm writeForm);

    WriteForm findTextByTitle(String category,String title);
    WriteForm findTextById(String category,int id);
    WriteForm findTextLatest(String category);

    void deleteById(String category,int id);
    void resetAutoIncrement(String category);
    void setCountToZero();

    void updateId(String category);
}
