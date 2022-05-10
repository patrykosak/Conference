package com.example.demo.service;

import com.example.demo.Lecture;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
public class LectureService implements CommandLineRunner {

    private List<Lecture> lectures = new ArrayList<>();

    @Override
    public void run(String... args) throws Exception {
        lectures.add(Lecture.builder()
                .lectureId(1L)
                .startDate(LocalDateTime.of(2021, Month.JUNE, 1, 10, 0))
                .endDate(LocalDateTime.of(2021, Month.JUNE, 1, 11, 45))
                .build());
        lectures.add(Lecture.builder()
                .lectureId(2L)
                .startDate(LocalDateTime.of(2021, Month.JUNE, 1, 12, 0))
                .endDate(LocalDateTime.of(2021, Month.JUNE, 1, 13, 45))
                .build());
        lectures.add(Lecture.builder()
                .lectureId(3L)
                .startDate(LocalDateTime.of(2021, Month.JUNE, 1, 14, 0))
                .endDate(LocalDateTime.of(2021, Month.JUNE, 1, 15, 45))
                .build());

    }

    public List<Lecture> fetchLectureList(){
        return lectures;
    }
}
