package com.min.myblogv1.domain;

import com.min.myblogv1.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Utilities;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileProcess {
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;
    private final S3Client s3;

    public Path fileUpload(String folderName, MultipartFile file) throws IOException {
        Path path = new Path();
        String key = folderName + "/" + getServerFileName(file.getOriginalFilename());
        log.info("fileUploadkey={}", key);
        PutObjectRequest objectRequest = getPutObjectRequest(key);
        RequestBody rb = getFileRequestBody(file);
        s3.putObject(objectRequest, rb);
        String url = getUrl(key);
        path.setUrl(url);
//

//
//        s3.close();
        return path;
    }

    public void copyObject(String destinationKey) {
        CopyObjectRequest copyObjectRequest =CopyObjectRequest
                .builder()
                .sourceKey(destinationKey)
                .sourceBucket(bucketName)
                .destinationBucket(bucketName)
                .destinationKey(destinationKey)
                .build();
        s3.copyObject(copyObjectRequest);
        log.info("copy path={}",destinationKey);
    }

    public String getServerFileName(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        String substring = originalFilename.substring(pos + 1);
        String string = UUID.randomUUID().toString();
        String n = string + "." + substring;
        System.out.println("n = " + n);
        return n;
    }

    public PutObjectRequest getPutObjectRequest(String key) {
        return PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
    }

    public RequestBody getFileRequestBody(MultipartFile file) throws IOException {
        return RequestBody.fromInputStream(file.getInputStream(), file.getSize());
    }

    public String getUrl(String key) {
        S3Utilities s3Utilities = s3.utilities();
        GetUrlRequest request = GetUrlRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        return s3Utilities.getUrl(request).toString();
    }

    public void deleteObject(String key) {
        ArrayList<ObjectIdentifier> toDelete = new ArrayList<>();
        toDelete.add(ObjectIdentifier.builder()
                .key(key)
                .build());
        DeleteObjectsRequest deleteObjectRequest = DeleteObjectsRequest
                .builder()
                .bucket(bucketName)
                .delete(Delete.builder().objects(toDelete).build())
                .build();
        s3.deleteObjects(deleteObjectRequest);
//        s3.close();
    }

    //image태그의 src값에서 객체 키 추출
    public HashSet<String> imgTagFindSrc(String bodyContent) throws IOException {
        Element body = Jsoup.parse(bodyContent).body();
        Elements imgTag = body.getElementsByTag("img");
        ArrayList<String> keys = new ArrayList<>();
        for (Element element : imgTag) {
            String src = element.attr("src");

            //todo 여기서 파일 이동일어남
            try {
                keys.add(src.substring(src.lastIndexOf("tempImage")));
                log.info("list.get(0)={}", keys.get(0));
            } catch (StringIndexOutOfBoundsException e) {
                log.info("작성자가 올린 이미지가 아닙니다");
            }
        }
        HashSet<String> set = new HashSet<>();
        set.addAll(keys);
        return set;
    }

    public void s3Move(String key) {
        String encodedUrl = URLEncoder.encode(bucketName + "/" + key, StandardCharsets.UTF_8);
        CopyObjectRequest copyReq = CopyObjectRequest.builder()
                .copySourceIfMatch(encodedUrl)
                .destinationBucket(bucketName)
                .destinationKey(key)
                .build();
        s3.copyObject(copyReq);
//        CopyObjectResponse copyObjectResponse = s3.copyObject(copyReq);
        s3.close();

    }

}
