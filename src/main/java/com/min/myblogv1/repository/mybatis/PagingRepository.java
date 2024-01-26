package com.min.myblogv1.repository.mybatis;

import com.min.myblogv1.PagingParam;
import com.min.myblogv1.domain.WriteForm;

import java.util.List;

public interface PagingRepository {
    List<WriteForm> getPaging(PagingParam param);
}
