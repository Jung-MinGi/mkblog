package com.min.myblogv1.controller.api;

import com.github.pagehelper.PageInfo;
import com.min.myblogv1.domain.WriteForm;
import com.min.myblogv1.service.PagingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/paging")
@RestController
public class PagingController {
    private final PagingService service;

    @GetMapping("page")
    public ResponseEntity<PageInfo<WriteForm>> getpage(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "5") Integer pageSize,
                               @RequestParam String tableName) {
        log.info("pageController= {} {} {}", pageNum, pageSize, tableName);

        PageInfo<WriteForm> p = new PageInfo<>(service.getPaging(pageNum, pageSize,tableName));
        return new ResponseEntity<>(p, HttpStatus.OK);
    }
}
