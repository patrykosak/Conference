package com.example.demo.service;

import com.example.demo.Lecture;
import com.example.demo.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureService implements CommandLineRunner {

    private int MAX_NUMBER_OF_USERS = 5;
    private final List<Lecture> lectures = new ArrayList<>();

    private AppUserRepository appUserRepository;

    public LectureService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public void run(String... args){
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

    public ResponseEntity<?> fetchLectureList(){
        return ResponseEntity.ok(lectures);
    }

    public LocalDateTime getLectureStartDate(Long lectureId){
        Lecture lecture = lectures.stream()
                .filter(l -> l.getLectureId().equals(lectureId))
                .findFirst()
                .get();
        return lecture.getStartDate();
    }

    public List<Long> fetchLectureListAtThisHour(LocalDateTime date){
        return lectures.stream()
                .filter(l -> l.getStartDate().equals(date))
                .map(Lecture::getLectureId)
                .collect(Collectors.toList());
    }

    public Lecture fetchLecture(Long lectureId) {
        return lectures.stream()
                .filter(l -> l.getLectureId().equals(lectureId))
                .findFirst()
                .get();
    }

    public String fetchLecturesWithThematicPath(String thematicPath) {
        List<Lecture> lecture = lectures.stream()
                .filter(l -> l.getThematicPath().equals(thematicPath))
                .collect(Collectors.toList());
        double amount = 0;
        for(Lecture l:lecture){
            amount += appUserRepository.findAllByLecturesIdContaining(l.getLectureId()).size();
        }
        return "Procentowy udział uczestników w ścieżce tematycznej: " + thematicPath + " " + amount/(lecture.size()*MAX_NUMBER_OF_USERS)*100 + "%";
    }

    public String fetchLecturesWithStartDate(LocalDateTime date) {
        List<Lecture> lecture = lectures.stream()
                .filter(l -> l.getStartDate().equals(date))
                .collect(Collectors.toList());
        double amount = 0;
        for(Lecture l:lecture){
            amount += appUserRepository.findAllByLecturesIdContaining(l.getLectureId()).size();
        }
        return "Procentowy udział uczestników w wykładzie o : " + date + " " + amount/(lecture.size()*MAX_NUMBER_OF_USERS)*100 + "%";
    }

    public ResponseEntity<?> fetchInterests() {
        List<String> listByInterest = new ArrayList<>();
        listByInterest.add(fetchLecturesWithStartDate(LocalDateTime.of(2021, Month.JUNE, 1, 10, 0)));
        listByInterest.add(fetchLecturesWithStartDate(LocalDateTime.of(2021, Month.JUNE, 1, 12, 0)));
        listByInterest.add(fetchLecturesWithStartDate(LocalDateTime.of(2021, Month.JUNE, 1, 14, 0)));
        listByInterest.add(fetchLecturesWithThematicPath("frontend"));
        listByInterest.add(fetchLecturesWithThematicPath("backend"));
        listByInterest.add(fetchLecturesWithThematicPath("mobile"));
        return ResponseEntity.ok(listByInterest);
    }
}
