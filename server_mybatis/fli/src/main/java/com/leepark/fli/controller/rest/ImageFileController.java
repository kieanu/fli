package com.leepark.fli.controller.rest;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
@CrossOrigin("*")
public class ImageFileController {
	
	private static final Logger logger = LoggerFactory.getLogger(ImageFileController.class);

    private static final String UPLOAD_DIR = "/static/images/";

    @GetMapping("/images/{url}")
    public ResponseEntity<InputStreamResource> downloadImage(@PathVariable("url") String url) throws Exception {
        String filePath = UPLOAD_DIR + url;
        logger.info("message" + filePath);
        File file = new File(filePath);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Paths.get(file.toString())));

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
