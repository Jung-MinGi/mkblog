package com.min.myblogv1.service;

import com.min.myblogv1.domain.*;
import com.min.myblogv1.repository.mybatis.DataGetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class DataAccessService {

    private final DataGetRepository repository;
    private final FileProcess fileProcess;
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public WriteForm findTextById(FindTextParam findTextParam){
        return repository.findTextById(findTextParam.getCategory(), findTextParam.getId());
    }
    public WriteForm findTextLatest(String category){
        return repository.findTextLatest(category);
    }
    public List<String> getTablesName() {
        return repository.getTablesName();
    }

    public WriteForm findTextByTitle(FindTextParamDTO findTextParamDTO){
        return repository.findTextByTitle(findTextParamDTO.getCategory(),findTextParamDTO.getTitle());
    }

    public WriteForm formDataSave(WriteForm writeForm) throws IOException {
        String tempContent = writeForm.getContent();

        //1.html 파싱후 image src 값 교체
        String content = htmlParse(writeForm.getContent());
        writeForm.setContent(content);
        //2.temp폴더 안에 내용 삭제
        List<String> list = extractedKeyFromImageTag(tempContent, GlobalConst.FILE_TEMP);
        for (String s : list) {
            fileProcess.deleteObject(s);
        }

        //3.db저장
        repository.save(writeForm);

        return writeForm;
    }
    //수정하는 로직
    public WriteForm update(UpdateParam updateParam){
        String content = htmlParse(updateParam.getContent());
        WriteForm writeForm = new WriteForm();
        writeForm.setId(updateParam.getId());
        writeForm.setCategory(updateParam.getCategory());
        writeForm.setTitle(updateParam.getTitle());
        writeForm.setContent(content);
        //수정하면서 저장된 s3 temp에 저장된 이미지 삭제하기
        //db에 저장된 기존 글 가져옴
        WriteForm textByTitle = repository.findTextById(updateParam.getPrevCategory(), updateParam.getId());
        log.info("textByTitle={}",textByTitle.toString());
        String tmpContent = textByTitle.getContent();
        List<String> list = extractedKeyFromImageTag(tmpContent,GlobalConst.FILE);
        for (String s : list) {
            if(content.contains(s))continue;
            fileProcess.deleteObject(s);
        }

        if(updateParam.getPrevCategory().equals(updateParam.getCategory())){
            //카테코리가 이전과 같다면 그냥 update만
            repository.update(writeForm);
        }else{
            //카테코리가 이전과 다르다면 이전 카테고리가 속한 db에서 삭제도 같이해야됨

        }

        return writeForm;
    }

    private String htmlParse(String bodyContent){
        Element body = Jsoup.parse(bodyContent).body();
        Elements imgTag = body.getElementsByTag("img");
        for (Element element : imgTag) {
            String src = element.attr("src");
            if(!src.contains(bucketName))continue;
            //여기서 파일 복사
            String key =null;
            if(src.contains(GlobalConst.FILE_TEMP)){
                key =  src.substring(src.lastIndexOf(GlobalConst.FILE_TEMP));
            }else{
               continue;
            }
            fileProcess.copyObject(key);

            src=src.replaceFirst(GlobalConst.FILE_TEMP,GlobalConst.FILE);

            element.attr("src",src);
        }
        return body.html();
    }
    private List<String> extractedKeyFromImageTag(String bodyContent,String delim){
        ArrayList<String> list = new ArrayList<>();
        Element body = Jsoup.parse(bodyContent).body();
        Elements imgTag = body.getElementsByTag("img");
        for (Element element : imgTag) {
            String src = element.attr("src");
            if(!src.contains(bucketName))continue;
            list.add(src.substring(src.lastIndexOf(delim)));
        }
        return list;
    }
}
