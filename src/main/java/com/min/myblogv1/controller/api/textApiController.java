package com.min.myblogv1.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class textApiController {
    private final ObjectMapper objectMapper;
    @PostMapping("/temp/upload")
    public ResponseEntity<String> tempImageSave(MultipartFile file) throws IOException {

        log.info("임시파일 로직 ={}",file.getOriginalFilename());
        log.info("임시파일 로직 ={}",file.getName());
        log.info("임시파일 로직 ={}",file.getContentType());
        log.info("임시파일 로직 ={}",file.getInputStream());
        log.info("임시파일 로직 ={}",file.getBytes());
        log.info("임시파일 로직 ={}",file.getResource());
        return new ResponseEntity<>(file.toString(), HttpStatus.OK);
    }
}
