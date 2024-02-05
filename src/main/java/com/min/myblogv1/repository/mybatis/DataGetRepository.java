package com.min.myblogv1.repository.mybatis;

import com.min.myblogv1.domain.LoginFormDTO;
import com.min.myblogv1.domain.UserDTO;
import com.min.myblogv1.domain.WriteForm;
import com.min.myblogv1.domain.IncludeDeletedColumnWriteForm;

import java.util.List;

public interface DataGetRepository {
    List<String> getTablesName();
    void save(WriteForm writeForm);
    void update(WriteForm writeForm);
    WriteForm findTextByTitle(String category,String title);

    WriteForm findTextById(String category,int id);

    WriteForm findTextLatest(String category);
    void deleteById(String category,int id);

    UserDTO findByUsername(LoginFormDTO loginFormDTO);

}
