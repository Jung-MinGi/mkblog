package com.min.myblogv1.service;

import com.min.myblogv1.repository.mybatis.DataGetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DataAccessService {
    private final DataGetRepository repository;

    public List<String> getTablesName(){
        return repository.getTablesName();
    }
}
