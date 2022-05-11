package com.example.demo.service;

import com.example.demo.Interest;
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
                .thematicPath("frontend")
                .build());
        lectures.add(Lecture.builder()
                .lectureId(2L)
                .startDate(LocalDateTime.of(2021, Month.JUNE, 1, 10, 0))
                .endDate(LocalDateTime.of(2021, Month.JUNE, 1, 11, 45))
                .thematicPath("backend")
                .build());
        lectures.add(Lecture.builder()
                .lectureId(3L)
                .startDate(LocalDateTime.of(2021, Month.JUNE, 1, 10, 0))
                .endDate(LocalDateTime.of(2021, Month.JUNE, 1, 11, 45))
                .thematicPath("mobile")
                .build());
        lectures.add(Lecture.builder()
                .lectureId(4L)
                .startDate(LocalDateTime.of(2021, Month.JUNE, 1, 12, 0))
                .endDate(LocalDateTime.of(2021, Month.JUNE, 1, 13, 45))
                .thematicPath("frontend")
                .build());
        lectures.add(Lecture.builder()
                .lectureId(5L)
                .startDate(LocalDateTime.of(2021, Month.JUNE, 1, 12, 0))
                .endDate(LocalDateTime.of(2021, Month.JUNE, 1, 13, 45))
                .thematicPath("backend")
                .build());
        lectures.add(Lecture.builder()
                .lectureId(6L)
                .startDate(LocalDateTime.of(2021, Month.JUNE, 1, 12, 0))
                .endDate(LocalDateTime.of(2021, Month.JUNE, 1, 13, 45))
                .thematicPath("mobile")
                .build());
        lectures.add(Lecture.builder()
                .lectureId(7L)
                .startDate(LocalDateTime.of(2021, Month.JUNE, 1, 14, 0))
                .endDate(LocalDateTime.of(2021, Month.JUNE, 1, 15, 45))
                .thematicPath("frontend")
                .build());
        lectures.add(Lecture.builder()
                .lectureId(8L)
                .startDate(LocalDateTime.of(2021, Month.JUNE, 1, 14, 0))
                .endDate(LocalDateTime.of(2021, Month.JUNE, 1, 15, 45))
                .thematicPath("backend")
                .build());
        lectures.add(Lecture.builder()
                .lectureId(9L)
                .startDate(LocalDateTime.of(2021, Month.JUNE, 1, 14, 0))
                .endDate(LocalDateTime.of(2021, Month.JUNE, 1, 15, 45))
                .thematicPath("mobile")
                .build());

    }

    public List<Lecture> fetchLectureList(){
        return lectures;
    }
    public Lecture fetchLecture(Long lectureId) {
        Lecture lecture = lectures.stream()
                .filter(l -> l.getLectureId() == lectureId)
                .reduce((a, b) -> {
                    throw new IllegalStateException("Lecture with that id not exists");
                })
                .get();
        return lecture;
    }

    public List<String> fetchInterests() {
        List<String> listByInterst = new ArrayList<>();
        listByInterst.add("Procentowy udział uczestników w danym wykładzie: ");
        listByInterst.add("Procentowy udział uczestników w danej ścieżce tematycznej: ");
    }
}
