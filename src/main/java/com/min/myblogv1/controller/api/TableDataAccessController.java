package com.min.myblogv1.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.min.myblogv1.Path;
import com.min.myblogv1.domain.GlobalConst;
import com.min.myblogv1.domain.LoginFormDTO;
import com.min.myblogv1.domain.UserDTO;
import com.min.myblogv1.service.DataAccessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public List<String> getAllTablesName() {
        return service.getTablesName();
    }

    @GetMapping("/board")
    public ResponseEntity<String> boardPage(@SessionAttribute(name = GlobalConst.LOGIN_USER, required = false) String user
            , @SessionAttribute(name = GlobalConst.LOGIN_USER_PW, required = false) String pw) {
        if (user == null) {
            return new ResponseEntity<>("권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        UserDTO dto = service.findUser(new LoginFormDTO(user, pw));
        if (dto == null || dto.getAuthority() == null) {
            return new ResponseEntity<>("권한이 없습니다.", HttpStatus.FORBIDDEN);

        }
        return ResponseEntity.ok("");
    }
}
