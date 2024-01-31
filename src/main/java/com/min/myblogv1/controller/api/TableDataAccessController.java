package com.min.myblogv1.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.min.myblogv1.Path;
import com.min.myblogv1.service.DataAccessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TableDataAccessController {

    private final ObjectMapper objectMapper;
    private final S3Client s3;
    private final DataAccessService service;
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @GetMapping("/tables")
    public List<String> getAllTablesName(){
        return service.getTablesName();
    }

}
