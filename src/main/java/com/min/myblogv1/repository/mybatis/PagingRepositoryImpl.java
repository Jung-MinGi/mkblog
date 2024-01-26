package com.min.myblogv1.repository.mybatis;

import com.github.pagehelper.PageHelper;
import com.min.myblogv1.PagingParam;
import com.min.myblogv1.domain.WriteForm;
import com.min.myblogv1.repository.mapper.PagingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PagingRepositoryImpl implements PagingRepository {
    private final PagingMapper mapper;

    @Override
    public List<WriteForm> getPaging(PagingParam param) {
        log.info("PagingRepositoryImpl= {}", param.toString());
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        return mapper.getPaging(param.getTableName());
    }
}
