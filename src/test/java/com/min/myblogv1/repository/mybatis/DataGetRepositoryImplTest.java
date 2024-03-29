package com.min.myblogv1.repository.mybatis;

import com.min.myblogv1.domain.LoginFormDTO;
import com.min.myblogv1.domain.UserDTO;
import com.min.myblogv1.domain.WriteForm;
import com.min.myblogv1.repository.mapper.DataGetMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
@Slf4j
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
        repository.deleteById(writeForm.getCategory(), textLatest1.getId());
        WriteForm textLatest = repository.findTextLatest(writeForm.getCategory());
        assertThat(textLatest.getTitle()).isNotEqualTo("title1");

    }

    @Test
    @DisplayName("회원 체크 테스트")
    public void memberTest(){

        assertThat(repository.findByUsername(new LoginFormDTO("test1","test!")).getAuthority()).isEqualTo("admin");
        UserDTO test = repository.findByUsername(new LoginFormDTO("test","qqq"));
        assertThatThrownBy(()->test.getAuthority())
                .isInstanceOf(NullPointerException.class);
    }
}