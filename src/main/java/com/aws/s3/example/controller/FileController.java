package com.aws.s3.example.controller;

import com.aws.s3.example.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveFile(@RequestParam("multipartFile") MultipartFile multipartFile) {

        URL downloadUrl = service.createFile(multipartFile);
        return new ResponseEntity<>(downloadUrl.toString(), HttpStatus.CREATED);
    }
}
