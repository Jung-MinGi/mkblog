package com.min.myblogv1.repository.mybatis;

import com.min.myblogv1.domain.WriteForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DataGetRepository {
    List<String> getTablesName();
    void save(WriteForm writeForm);
    WriteForm findTextByTitle(String category,String title);

}
