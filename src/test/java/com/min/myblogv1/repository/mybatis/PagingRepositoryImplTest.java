package com.min.myblogv1.repository.mybatis;

import com.github.pagehelper.PageHelper;
import com.min.myblogv1.PagingParam;
import com.min.myblogv1.domain.WriteForm;
import com.min.myblogv1.repository.mapper.PagingMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@MybatisTest
@ActiveProfiles("test")
class PagingRepositoryImplTest {
     PagingRepository repository;
    @Autowired
     PagingMapper mapper;

    @BeforeEach
    void before() {
        repository = new PagingRepositoryImpl(mapper);
    }

    @Test
    void test() {
        PagingParam param = new PagingParam();
        param.setPageNum(1);
        param.setPageSize(2);
        param.setTableName("spring");
        List<WriteForm> paging = repository.getPaging(param);
        Assertions.assertThat(paging.size()).isEqualTo(4);
    }
}