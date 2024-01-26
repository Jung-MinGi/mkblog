package com.min.myblogv1.repository.mybatis;

import com.min.myblogv1.PagingParam;
import com.min.myblogv1.domain.WriteForm;
import com.min.myblogv1.repository.mapper.PagingMapper;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
class PagingRepositoryImplTest {

    private PagingRepository repository;
    @Autowired
    private PagingMapper mapper;

    @BeforeEach
    void before() {
        repository = new PagingRepositoryImpl(mapper);
    }

    @Test
    void test(){
        PagingParam param = new PagingParam();
        param.setPageNum(1);
        param.setPageSize(2);
        param.setTableName("spring");
        List<WriteForm> spring = repository.getPaging(param);
        Assertions.assertThat(spring.size()).isEqualTo(2);
    }
}