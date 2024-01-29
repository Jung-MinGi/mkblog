package com.min.myblogv1.service;

import com.min.myblogv1.domain.FileProcess;
import com.min.myblogv1.domain.WriteForm;
import com.min.myblogv1.repository.mybatis.DataGetRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DataAccessService {
    private final DataGetRepository repository;
    private final FileProcess fileProcess;
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public List<String> getTablesName() {
        return repository.getTablesName();
    }

    public WriteForm formDataSave(WriteForm writeForm) throws IOException {
        //1.html 파싱후 image src 값 교체
        String content = htmlParse(writeForm.getContent());
        writeForm.setContent(content);
        //2.temp폴더 안에 내용 삭제
        List<String> list = extractedKeyFromImageTag(writeForm.getContent());
        for (String s : list) {
            fileProcess.deleteObject(s);
        }

        //3.db저장
        repository.save(writeForm);

        return writeForm;
    }

    private String htmlParse(String bodyContent){
        Element body = Jsoup.parse(bodyContent).body();
        Elements imgTag = body.getElementsByTag("img");
        for (Element element : imgTag) {
            String src = element.attr("src");
            src=src.replaceFirst("tempImage","Image");
            //여기서 파일 복사
            String key = src.substring(src.lastIndexOf("Image"));
            fileProcess.copyObject(key);
            //
            element.attr("src",src);
        }
        return body.html();
    }
    private List<String> extractedKeyFromImageTag(String bodyContent){
        ArrayList<String> list = new ArrayList<>();
        Element body = Jsoup.parse(bodyContent).body();
        Elements imgTag = body.getElementsByTag("img");
        for (Element element : imgTag) {
            String src = element.attr("src");
            if(!src.contains(bucketName))continue;
            list.add(src.substring(src.lastIndexOf("tempImage")));
        }
        return list;
    }
}
