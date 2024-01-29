package com.min.myblogv1.repository.mybatis;

import com.min.myblogv1.domain.WriteForm;
import com.min.myblogv1.repository.mapper.DataGetMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@MybatisTest
@ActiveProfiles("test")
class DataGetRepositoryImplTest {
    @Autowired
    private DataGetMapper mapper;
    DataGetRepository repository;
    @BeforeEach
    void before(){
        repository = new DataGetRepositoryImpl(mapper);
    }

    @Test
    void test(){
        List<String> tablesName = repository.getTablesName();
        assertThat(tablesName.size()).isEqualTo(1);

        WriteForm writeForm = new WriteForm();
        writeForm.setCategory("spring");
        writeForm.setTitle("title1");
        writeForm.setContent("content1");

        repository.save(writeForm);
//        assertThat(save.getTitle()).isEqualTo("title1");
    }
}