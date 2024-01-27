package com.min.myblogv1.domain;

import com.min.myblogv1.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Utilities;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileProcess {
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;
    private final S3Client s3;

    public Path fileUpload(String folderName, MultipartFile file) throws IOException {
        Path path = new Path();
        String key = folderName + "/" + getServerFileName(file.getOriginalFilename());
        PutObjectRequest objectRequest = getPutObjectRequest(key);
        RequestBody rb = getFileRequestBody(file);
        s3.putObject(objectRequest, rb);
        String url = getUrl(key);
        path.setUrl(url);
        return path;
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
        s3.close();
    }
}
