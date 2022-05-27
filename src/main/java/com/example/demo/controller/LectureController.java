package com.example.demo.controller;

import com.example.demo.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LectureController {

    @Autowired
    private LectureService lectureService;

    @GetMapping("/lectures")
    public ResponseEntity<?> fetchLectureList(){
        return lectureService.fetchLectureList();
    }

    @GetMapping("/interests")
    public ResponseEntity<?> fetchInterests(){
        return lectureService.fetchInterests();
    }
}
