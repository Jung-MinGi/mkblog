package com.min.myblogv1.repository.mybatis;

import com.min.myblogv1.domain.WriteForm;
import com.min.myblogv1.repository.mapper.DataGetMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class DataGetRepositoryImpl implements DataGetRepository {
    private final DataGetMapper mapper;
    @Override
    public List<String> getTablesName() {
        return mapper.getTablesName();
    }

    @Override
    public void save(WriteForm writeForm) {
         mapper.save(writeForm);
    }

    @Override
    public WriteForm findTextByTitle(String category, String title) {
        return mapper.findTextByTitle(category,title);
    }
}
