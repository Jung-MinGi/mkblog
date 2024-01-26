package com.min.myblogv1.repository.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DataGetRepository {
    List<String> getTablesName();
    }
