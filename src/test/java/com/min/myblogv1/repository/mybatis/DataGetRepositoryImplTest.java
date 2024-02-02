package com.min.myblogv1.repository.mybatis;

import com.min.myblogv1.domain.WriteForm;
import com.min.myblogv1.domain.IncludeDeletedColumnWriteForm;
import com.min.myblogv1.repository.mapper.DataGetMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

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


        WriteForm writeForm = new WriteForm();
        writeForm.setCategory("spring");
        writeForm.setTitle("title1");
        writeForm.setContent("content1");
        repository.save(writeForm);
        WriteForm result = repository.findTextByTitle(writeForm.getCategory(), writeForm.getTitle());
        assertThat(result.getTitle()).isEqualTo("title1");

        //update
        writeForm.setId(result.getId());
        writeForm.setContent("update1");
        repository.update(writeForm);
        WriteForm updateResult = repository.findTextByTitle(writeForm.getCategory(), writeForm.getTitle());
        assertThat(updateResult.getContent()).isEqualTo("update1");

        //delete
        WriteForm textLatest1 = repository.findTextLatest(writeForm.getCategory());
        System.out.println("textLatest1 = " + textLatest1);
        repository.deleteById(writeForm.getCategory(), textLatest1.getId());
        WriteForm textLatest = repository.findTextLatest(writeForm.getCategory());
        System.out.println("textLatest = " + textLatest);
        assertThat(textLatest.getTitle()).isNotEqualTo("title1");

    }
}