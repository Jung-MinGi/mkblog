package com.min.myblogv1.service;

import com.min.myblogv1.domain.FileProcess;
import com.min.myblogv1.domain.WriteForm;
import com.min.myblogv1.repository.mybatis.DataGetRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DataAccessService {
    private final DataGetRepository repository;
    private final FileProcess fileProcess;

    public List<String> getTablesName() {
        return repository.getTablesName();
    }

    public WriteForm formDataSave(WriteForm writeForm) throws IOException {
        HashSet<String> set = fileProcess.imgTagFindSrc(writeForm.getContent());

        return writeForm;
    }
}
