package com.min.myblogv1.service;

import com.min.myblogv1.PagingParam;
import com.min.myblogv1.domain.WriteForm;
import com.min.myblogv1.repository.mybatis.PagingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PagingService {
    private final PagingRepository repository;
    public List<WriteForm> getPaging(Integer pageNum, Integer pageSize, String tableName){
        PagingParam pagingParam = new PagingParam();
        pagingParam.setPageNum(pageNum);
        pagingParam.setPageSize(pageSize);
        pagingParam.setTableName(tableName);
        log.info("pageservice= {} {} {}", pageNum, pageSize, tableName);
        log.info("pageservice= {}", pagingParam.toString());
        return repository.getPaging(pagingParam);
    }
}
