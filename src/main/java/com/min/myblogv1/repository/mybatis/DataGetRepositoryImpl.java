package com.min.myblogv1.repository.mybatis;

import com.min.myblogv1.domain.WriteForm;
import com.min.myblogv1.domain.IncludeDeletedColumnWriteForm;
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
    public void update(WriteForm writeForm) {
        mapper.update(writeForm);
    }

    @Override
    public WriteForm findTextByTitle(String category, String title) {
        return mapper.findTextByTitle(category,title);
    }

    @Override
    public WriteForm findTextById(String category, int id) {
        return mapper.findTextById(category,id);
    }

    @Override
    public WriteForm findTextLatest(String category) {
        return mapper.findTextLatest(category);
    }

    @Override
    public void deleteById(String category, int id) {
        mapper.deleteById(category,id);
    }

    @Override
    public void resetAutoIncrement(String category) {
        mapper.resetAutoIncrement(category);
    }

    @Override
    public void setCountToZero() {
        mapper.setCountToZero();
    }

    @Override
    public void updateId(String category) {
        mapper.updateId(category);
    }
}
