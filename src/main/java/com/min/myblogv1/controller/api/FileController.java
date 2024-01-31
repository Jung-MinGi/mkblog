package com.min.myblogv1.controller.api;

import com.min.myblogv1.Path;
import com.min.myblogv1.domain.FileProcess;
import com.min.myblogv1.domain.FindTextParam;
import com.min.myblogv1.domain.UpdateParam;
import com.min.myblogv1.domain.WriteForm;
import com.min.myblogv1.service.DataAccessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
    private final DataAccessService service;
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;


    @PostMapping("/temp/upload")
    public ResponseEntity<Path> tempImageSave(MultipartFile file) throws IOException {
        return new ResponseEntity<>(fileProcess.fileUpload("tempImage", file), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<Object> upload(@Validated @RequestBody WriteForm formData, BindingResult bindingResult) throws IOException {
        log.info("WriteForm={}", formData);
        if (bindingResult.hasErrors()) {
            log.info("formData={}", formData);
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        service.formDataSave(formData);
        return new ResponseEntity<>(formData, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> upload(@Validated @RequestBody UpdateParam updateParam, BindingResult bindingResult) throws IOException {
        log.info("WriteForm={}", updateParam);
        if (bindingResult.hasErrors()) {
            log.info("formData={}", updateParam);
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        service.update(updateParam);

        return new ResponseEntity<>(updateParam, HttpStatus.OK);
    }

    @PostMapping("/delete/{category}/{id}")
    public void delete(@ModelAttribute FindTextParam findTextParam){
        service.deleteById(findTextParam);
        log.info("delete ={}",findTextParam.toString());
    }
}
