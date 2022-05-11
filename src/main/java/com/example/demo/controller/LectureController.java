package com.example.demo.controller;

import com.example.demo.Interest;
import com.example.demo.Lecture;
import com.example.demo.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LectureController {

    @Autowired
    private LectureService lectureService;

    @GetMapping("/lectures")
    public List<Lecture> fetchLectureList(){
        return lectureService.fetchLectureList();
    }

    @GetMapping("/interests")
    public List<String> fetchInterests(){
        return lectureService.fetchInterests();
    }
}
