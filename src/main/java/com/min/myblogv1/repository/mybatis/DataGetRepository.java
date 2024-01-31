package com.min.myblogv1.repository.mybatis;

import com.min.myblogv1.domain.WriteForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DataGetRepository {
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
