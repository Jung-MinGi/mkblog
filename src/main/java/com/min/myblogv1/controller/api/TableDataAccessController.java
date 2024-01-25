package com.min.myblogv1.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.min.myblogv1.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Utilities;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TableDataAccessController {

    private final ObjectMapper objectMapper;
    private final S3Client s3;
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;




    @PostMapping("/temp/upload")
    public ResponseEntity<Path> tempImageSave(MultipartFile file) throws IOException {
        System.out.println("file.getOriginalFilename() = " + file.getOriginalFilename());
        Path path = null;
        String key = "image/"+getServerFileName(file.getOriginalFilename());
        System.out.println("key = " + key);
        PutObjectRequest objectRequest = getPutObjectRequest(key);
        System.out.println("objectRequest = " + objectRequest.bucket());
        RequestBody rb = getFileRequestBody(file);
        System.out.println("rb = " + rb.toString());
        s3.putObject(objectRequest, rb);
        String url = getUrl(key);
        System.out.println("url = " + url);
        log.info("url={}", url);
        path = new Path();
        path.setUrl(url);
        System.out.println("path.getUrl() = " + path.getUrl());
        return new ResponseEntity<>(path, HttpStatus.OK);
    }
    private static String getServerFileName(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        String substring = originalFilename.substring(pos + 1);
        String string = UUID.randomUUID().toString();
        String n = string + "." + substring;
        System.out.println("n = " + n);
        return n;
    }
    private PutObjectRequest getPutObjectRequest(String key) {
        return PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
    }

    private RequestBody getFileRequestBody(MultipartFile file) throws IOException {
        return RequestBody.fromInputStream(file.getInputStream(), file.getSize());
    }

    private String getUrl(String key) {
        S3Utilities s3Utilities = s3.utilities();
        GetUrlRequest request = GetUrlRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        return s3Utilities.getUrl(request).toString();
    }
}
