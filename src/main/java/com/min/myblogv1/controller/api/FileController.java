package com.min.myblogv1.controller.api;

import com.min.myblogv1.Path;
import com.min.myblogv1.domain.FileProcess;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {
    private final S3Client s3;
    private final FileProcess fileProcess;
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @PostMapping("/temp/upload")
    public ResponseEntity<Path> tempImageSave(MultipartFile file) throws IOException {
//        System.out.println("file.getOriginalFilename() = " + file.getOriginalFilename());
//        Path path = null;
//        String key = "tempImage/" + fileProcess.getServerFileName(file.getOriginalFilename());
//        System.out.println("key = " + key);
//        PutObjectRequest objectRequest = fileProcess.getPutObjectRequest(key);
//        System.out.println("objectRequest = " + objectRequest.bucket());
//        RequestBody rb = fileProcess.getFileRequestBody(file);
//        System.out.println("rb = " + rb.toString());
//        s3.putObject(objectRequest, rb);
//        String url = fileProcess.getUrl(key);
//        System.out.println("url = " + url);
//        log.info("url={}", url);
//        path = new Path();
//        path.setUrl(url);
//        System.out.println("path.getUrl() = " + path.getUrl());
        return new ResponseEntity<>(fileProcess.fileUpload("tempImage",file), HttpStatus.OK);
    }



}
