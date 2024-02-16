package com.min.myblogv1.controller.api;

import com.min.myblogv1.Path;
import com.min.myblogv1.domain.*;
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


    @PostMapping("/temp")
    public ResponseEntity<Path> tempImageSave(MultipartFile file) throws IOException {
        return new ResponseEntity<>(fileProcess.fileUpload("tempImage", file), HttpStatus.OK);
    }

    @GetMapping("/{category}/{id}")
    public ResponseEntity<Object> getText(@PathVariable String category,@PathVariable int id
            ,@SessionAttribute(name = GlobalConst.LOGIN_USER,required = false) String user
            ,@SessionAttribute(name = GlobalConst.LOGIN_USER_PW,required = false)String pw){
        if(user==null){
            log.info("binding error");
            return new ResponseEntity<>("권한이 없습니다.",HttpStatus.FORBIDDEN);
        }
        UserDTO dto = service.findUser(new LoginFormDTO(user,pw));
        if(dto==null||dto.getAuthority()==null){
            return new ResponseEntity<>("권한이 없습니다.",HttpStatus.FORBIDDEN);

        }
        FindTextParam findTextParam = new FindTextParam();
        findTextParam.setCategory(category);
        findTextParam.setId(id);
        return ResponseEntity.ok(findTextParam);
//         return new ResponseEntity<>(findTextParam,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Object> upload(@Validated @RequestBody WriteForm formData, BindingResult bindingResult) throws IOException {
        log.info("WriteForm={}", formData);
        if (bindingResult.hasErrors()) {
            log.info("formData={}", formData);
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        service.formDataSave(formData);
        return ResponseEntity.ok(formData);
//        return new ResponseEntity<>(formData, HttpStatus.OK);
    }

    /**
     * @param updateParam
     * @param bindingResult 가 먼저 작동해서 앞에 객체에 바인딩후 @Validated가 검증순서
     * @return
     * @throws IOException
     */
    @PutMapping
    public ResponseEntity<Object> update(@Validated @RequestBody UpdateParam updateParam, BindingResult bindingResult) throws IOException {
        log.info("WriteForm={}", updateParam);
        if (bindingResult.hasErrors()) {
            log.info("formData={}", updateParam);
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.update(updateParam));
    }

    @DeleteMapping("/{category}/{id}")
    public ResponseEntity<String> delete(@SessionAttribute(name = GlobalConst.LOGIN_USER,required = false) String user
            , @ModelAttribute FindTextParam findTextParam
            ,@SessionAttribute(name = GlobalConst.LOGIN_USER_PW,required = false)String pw) {

        if(user==null){
            log.info("binding error");
            return new ResponseEntity<>("권한이 없습니다.",HttpStatus.FORBIDDEN);
        }
        UserDTO dto = service.findUser(new LoginFormDTO(user,pw));
        if(dto==null||dto.getAuthority()==null){
            return new ResponseEntity<>("권한이 없습니다.",HttpStatus.FORBIDDEN);

        }
        service.deleteById(findTextParam);
        log.info("delete ={}", findTextParam.toString());
        return ResponseEntity.ok("삭제 했습니다");
//        return new ResponseEntity<>("삭제 했습니다", HttpStatus.OK);
    }
}
