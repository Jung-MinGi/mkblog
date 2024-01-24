package com.min.myblogv1.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.min.myblogv1.Path;
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
    public Path tempImageSave(MultipartFile file) throws IOException {
        log.info("임시파일 로직 ={}",file.getOriginalFilename());
        log.info("임시파일 로직 ={}",file.getName());
        log.info("임시파일 로직 ={}",file.getContentType());
        log.info("임시파일 로직 ={}",file.getResource());
        Path path = new Path();
        path.setUrl("정민기");
        return path;
    }
}
